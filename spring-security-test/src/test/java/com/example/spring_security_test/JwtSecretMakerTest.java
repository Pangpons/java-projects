package com.example.spring_security_test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMaker {

    @Test
    public void genarateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String endcodeKey = DatatypeConverter.printHexBinary(key.getEncoded());
    }
}
