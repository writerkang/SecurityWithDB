package com.example.securitywithdb.service;

import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    public String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();

        return base32.encodeToString(bytes);
    }
}
