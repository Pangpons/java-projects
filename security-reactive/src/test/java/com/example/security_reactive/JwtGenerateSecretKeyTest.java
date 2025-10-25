package com.example.security_reactive;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtGenerateSecretKeyTest {

    @Test
    public void generateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String secretKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(secretKey);
    }
}
