package com.hsob.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsob.user.model.user.UserRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTFilterAuthenticate extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    public static final int TOKEN_EXPIRATION = 600_000;
    public static final String TOKEN_PASSWORD = "f3c33665-62d2-4724-95b6-d2804819f0a2";

    public JWTFilterAuthenticate(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
       try {
           UserRequest userRequest = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
           return authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(userRequest.getDocument(), userRequest.getAuthpass(), new ArrayList<>()));
       } catch (IOException e){
           throw new RuntimeException("Authenticate failure", e);
       }
    }

//    geração do token atraves do username e password informado no contrutor da classe
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDataDetails userDataDetails = (UserDataDetails) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userDataDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
