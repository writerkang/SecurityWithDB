package com.example.securitywithdb.service;

import com.example.securitywithdb.command.RegisterUserCommand;
import com.example.securitywithdb.domain.role.Role;
import com.example.securitywithdb.domain.user.User;
import com.example.securitywithdb.domain.user.UserRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(RegisterUserCommand command) {
        var user = new User(command.getName(),
            passwordEncoder.encode(command.getPassword()),
            String.join(",", command.getRoles()),"sdfsdffs");

        repository.save(user);
    }
}
