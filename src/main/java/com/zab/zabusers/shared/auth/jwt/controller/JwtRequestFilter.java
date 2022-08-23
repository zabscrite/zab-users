package com.zab.zabusers.shared.auth.jwt.controller;

import com.zab.zabusers.shared.auth.jwt.domain.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${jwt.secret_key}")
    private String jwtSecretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                Jwt jwt = extractJwt(request.getHeader(HttpHeaders.AUTHORIZATION));
                UserDetails userDetails = extractUserDetails(jwt);
                UsernamePasswordAuthenticationToken token = createAuthentication(request, userDetails);
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        } catch (AuthenticationCredentialsNotFoundException e) {
            logger.error("Unable to get JWT on request");
        } catch (ExpiredJwtException e) {
            logger.error("JWT has expired");
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
        } finally {
            chain.doFilter(request, response);
        }
    }

    private Jwt extractJwt(String authHeader) {
        if (authHeader == null) {
            throw new AuthenticationCredentialsNotFoundException("No JWT set on request");
        }

        if (!authHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("JWT does not begin with Bearer");
        }

        String jwt = authHeader.substring(7);
        return Jwts.parser().setSigningKey(jwtSecretKey).parse(jwt);
    }

    private UserDetails extractUserDetails(Jwt jwt) {
        String username = ((Claims) jwt.getBody()).getSubject();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return userDetails;
    }

    private UsernamePasswordAuthenticationToken createAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }
}