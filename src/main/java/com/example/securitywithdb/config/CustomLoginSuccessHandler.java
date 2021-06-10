package com.example.securitywithdb.config;

import com.example.securitywithdb.domain.role.RoleRepository;
import com.example.securitywithdb.domain.user.UserRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {

        System.out.println("Call it!!");

        var user = userRepository.findByName(authentication.getName());

        var urls = Arrays.stream(user.getRoles().split(","))
            .map(role -> roleRepository.findByName(role).getUrls())
            .collect(Collectors.joining(","));

//        user.setAuthorizationUrls(urls);

        response.sendRedirect("/");
    }
}
