package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.model.JwtRequest;
import com.ecommerce.sportscenter.model.JwtResponse;
import com.ecommerce.sportscenter.security.JwtHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        String username = "test";
        String password = "password";
        JwtRequest request = new JwtRequest(username, password);

        UserDetails userDetails = new User(username, password, Collections.emptyList());
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtHelper.generateToken(userDetails)).thenReturn("testToken");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("testToken"))
                .andExpect(jsonPath("$.username").value("test"));
    }

    @Test
    void testLoginFail_InvalidCredentials() throws Exception {
        doThrow(new BadCredentialsException("Invalid username or password")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"wrong\", \"password\":\"wrong\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetUserDetailsSuccess() throws Exception {
        String token = "Bearer validtoken";
        String username = "user";
        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(jwtHelper.getUserName("validtoken")).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        mockMvc.perform(get("/api/auth/user")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void testGetUserDetailsFail_NoToken() throws Exception {
        mockMvc.perform(get("/api/auth/user"))
                .andExpect(status().isBadRequest());
    }
}
