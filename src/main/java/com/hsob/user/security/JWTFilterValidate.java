package com.hsob.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTFilterValidate extends BasicAuthenticationFilter {
    public static final String HEADER_ATRIBUTE = "Authorization";
    public static final String ATRIBUTE_PREFIX = "Bearer ";

    public JWTFilterValidate(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String atribut = request.getHeader(HEADER_ATRIBUTE);
        if (atribut == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!atribut.startsWith(ATRIBUTE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = atribut.replace(ATRIBUTE_PREFIX, "");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = JWT.require(Algorithm.HMAC512(JWTFilterAuthenticate.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();

        if (user == null) return  null;
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
