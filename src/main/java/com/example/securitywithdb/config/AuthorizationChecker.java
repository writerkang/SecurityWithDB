package com.example.securitywithdb.config;

import com.example.securitywithdb.domain.role.Role;
import com.example.securitywithdb.domain.role.RoleRepository;
import com.example.securitywithdb.domain.user.User;
import com.example.securitywithdb.domain.user.UserRepository;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthorizationChecker {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public boolean check(HttpServletRequest request, Authentication authentication) {
        User user = userRepository.findByName(authentication.getName());
        if (user == null) {
            return false;
        }

//        if(Arrays.stream(user.getAuthorizationUrls().split(","))
//            .anyMatch(url -> url.equals(request.getRequestURI()))) {
//            return true;
//        }
        for (String roleName : user.getRoles().split(",")) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                return false;
            }
            if(Arrays.stream(role.getUrlsWithAuth().split(","))
                .anyMatch(url -> url.equals(request.getRequestURI()))) {
                return true;
            }
        }
        return false;
    }
}
