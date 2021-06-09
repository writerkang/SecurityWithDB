package com.example.securitywithdb.command;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserCommand {
    private String name;
    private String password;
    private List<String> roles;
}
