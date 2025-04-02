package com.ecommerce.sportscenter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sportscenter.model.JwtRequest;
import com.ecommerce.sportscenter.model.JwtResponse;
import com.ecommerce.sportscenter.security.JwtHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager  authenticationManager;

    public AuthController(JwtHelper jwtHelper, UserDetailsService userDetailsService, AuthenticationManager authenticationManager){
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){//from RequestBody fectch json and convert it to jwtRequest
        try{
            this.authenticate(request.getUsername(), request.getPassword()); // verify username and password
        }
        catch(BadCredentialsException ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //if don't handle BadCredentialsException, will return 500 internal error
        }
        UserDetails userDetail = userDetailsService.loadUserByUsername(request.username);
        String jwtAuthenticationToken = jwtHelper.generateToken(userDetail);
        JwtResponse jwtResponse = JwtResponse.builder()
                                    .username(userDetail.getUsername())
                                    .token(jwtAuthenticationToken)
                                    .build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    } 

    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUserDetails(@RequestHeader("Authorization") String tokenHeader){// from reuqest header fetch token header
        String token = this.getTokenFromHeader(tokenHeader); //fecth actual token
        if(token!=null){
            String username = this.jwtHelper.getUserName(token); // get username from token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // get userDetail by username from userDetailService
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private String getTokenFromHeader(String tokenHeader){
        if(tokenHeader != null && tokenHeader.startsWith("Bearer")){
            return tokenHeader.substring(7);
        }
        return null;
    }

    // verify username and password by AuthenticaitonMangaer(managed by Spring)
    private void authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
    }
}
