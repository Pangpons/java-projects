package com.example.spring_security_test.webToken;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtService {
    private static final String SECRET = "EE07A941421C7CA54C664567464E10414F1603719ECDF7541BE54297CFD6B71295DD6982E3A014F1C93F7ABE01573688565F288AFAACDBFD640EC82B5218C063";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

    public String generateToKen(UserDetails userDetails) {
        Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith()
    }

    private SecretKey generateKey() {
        byte[] decodeKey = Base64.getDecoder().decode(SECRET);
        return Key.
    }
}
