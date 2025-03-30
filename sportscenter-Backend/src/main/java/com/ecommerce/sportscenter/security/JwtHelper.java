package com.ecommerce.sportscenter.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {
    private final long JWT_TOKEN_VALIDITY = 60 * 60 *5;

    private String secret = "f27dacd186810e78c0fd8ba65ecf3f1524ff087c5e86773d5172d424b3fd201f";

    public Date getExpiredDate(String token){ return getClaimFromToken(token, Claims::getExpiration);}

    public String getUserName(String token){ return getClaimFromToken(token, Claims::getSubject); }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUserName(token);
        // == will compare the address of Sting not value.
        return userName.equals(userDetails.getUsername())&& !isTokenExpired(token);  
    }

    private Boolean isTokenExpired(String token){
        // final means this value will only be assigned once and cannot be reassigned.
        final Date expriration = getExpiredDate(token);
        return expriration.before(new Date());
    }

    private String generateToken(Map<String, Object> claims, String subject) {
    //Convert the secret string key into a Key object
    Key hmacKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(hmacKey, SignatureAlgorithm.HS512)
                .compact();
    };

    private Claims getAllClaimsFromToken(String token) {
        Key hmacKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    };

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimSolver){
        Claims claims = getAllClaimsFromToken(token);
        return claimSolver.apply(claims);   
    }

}
