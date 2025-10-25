package com.example.spring_security;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtGenerateSecretKeyTest {

    @Test
    public void genSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String secretKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(secretKey);
    }
}
