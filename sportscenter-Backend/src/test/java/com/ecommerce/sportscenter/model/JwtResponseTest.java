package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {

    @Test
    void testJwtResponseGettersAndSetters() {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUsername("john");
        jwtResponse.setToken("token123");

        assertEquals("john", jwtResponse.getUsername(), "Username should be 'john'");
        assertEquals("token123", jwtResponse.getToken(), "Token should be 'token123'");
    }

    @Test
    void testJwtResponseConstructor() {
        JwtResponse jwtResponse = new JwtResponse("alice", "token456");

        assertEquals("alice", jwtResponse.getUsername(), "Username should be 'alice'");
        assertEquals("token456", jwtResponse.getToken(), "Token should be 'token456'");
    }

    @Test
    void testJwtResponseBuilder() {
        JwtResponse jwtResponse = JwtResponse.builder()
                .username("builder_user")
                .token("builder_token")
                .build();

        assertEquals("builder_user", jwtResponse.getUsername(), "Username should be 'builder_user'");
        assertEquals("builder_token", jwtResponse.getToken(), "Token should be 'builder_token'");
    }
}
