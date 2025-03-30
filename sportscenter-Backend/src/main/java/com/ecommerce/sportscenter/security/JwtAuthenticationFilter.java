package com.ecommerce.sportscenter.security;

import java.io.IOException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtHelper jwtHelper;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService){
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userName = null;
        String token = null;
        String requestHeader = request.getHeader("Authorization");

        // parse the request header and get username from token
        if(requestHeader!=null && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);
            try{
                // this step do the verificaiton of token
                userName = jwtHelper.getUserName(token);
            }
            catch(IllegalArgumentException | ExpiredJwtException | MalformedJwtException e){
                log.info("Jwt token processing error");
                e.printStackTrace();
            }
        }
        else{
            log.warn("JWT token doesn't start with Bearer String");
        }

        // use username to generate authenticaiton and put it in the context holder
        // context holder will have the authentication until this request is done
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // making sure each if branch do at least one token validation
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            Boolean validateToken = jwtHelper.validateToken(token, userDetails);
            if(validateToken){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // add web infomation like ip session into token, for possible audit purpose
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            else{
                log.warn("Token validation fail");
            }
        }
        filterChain.doFilter(request, response);
    }

}
