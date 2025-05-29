package com.Login.Security.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtServive;
//autogenerate whe you create the method
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //Authorization is the header that has jwt token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader==null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;

        }
        //extracting the token from authorization header
        jwt = authHeader.substring(7);
      //todo exract the useremail from jwt token
        userEmail= jwtServive.exctractUsername(jwt);

    }
}
