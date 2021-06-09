package com.example.securitywithdb.domain.user;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
//    private String authorizationUrls;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles")
    private String roles;

    private String secretKey;

    public User(String name, String password, String roles, String secretKey) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.secretKey = secretKey;
    }

//    public void setAuthorizationUrls(String authorizationUrls) {
//        this.authorizationUrls = authorizationUrls;
//    }
}
