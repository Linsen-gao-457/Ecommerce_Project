package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtRequestTest {

    @Test
    void testJwtRequestGettersAndSetters() {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("john");
        jwtRequest.setPassword("1234");

        assertEquals("john", jwtRequest.getUsername(), "Username should be 'john'");
        assertEquals("1234", jwtRequest.getPassword(), "Password should be '1234'");
    }

    @Test
    void testJwtRequestConstructor() {
        JwtRequest jwtRequest = new JwtRequest("alice", "abcd");

        assertEquals("alice", jwtRequest.getUsername(), "Username should be 'alice'");
        assertEquals("abcd", jwtRequest.getPassword(), "Password should be 'abcd'");
    }

    @Test
    void testJwtRequestBuilder() {
        JwtRequest jwtRequest = JwtRequest.builder()
                .username("builder_user")
                .password("builder_pass")
                .build();

        assertEquals("builder_user", jwtRequest.getUsername(), "Username should be 'builder_user'");
        assertEquals("builder_pass", jwtRequest.getPassword(), "Password should be 'builder_pass'");
    }
}
