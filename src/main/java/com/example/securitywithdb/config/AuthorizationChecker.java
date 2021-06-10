package com.example.securitywithdb.config;

import com.example.securitywithdb.domain.role.RoleRepository;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationChecker {
    private final RoleRepository roleRepository;

    public boolean check(HttpServletRequest request, Authentication authentication) {

        for (var authority : authentication.getAuthorities()) {
            var role = roleRepository.findByName(authority.getAuthority());
            if (role == null) {
                return false;
            }
            if(Arrays.stream(role.getUrls().split(","))
                .anyMatch(url -> (url.equals(request.getRequestURI())) ||
                    (request.getRequestURI().startsWith(url.substring(0, url.length() - 3)) && url.endsWith("/**")))) {
                return true;
            }
        }
        return false;
    }
}
