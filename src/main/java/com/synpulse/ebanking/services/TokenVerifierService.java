package com.synpulse.ebanking.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenVerifierService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String getUser(String token) throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        return jwt.getSubject();
    }
}
