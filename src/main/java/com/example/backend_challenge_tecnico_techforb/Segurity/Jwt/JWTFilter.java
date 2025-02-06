package com.example.backend_challenge_tecnico_techforb.Segurity.Jwt;

import com.example.backend_challenge_tecnico_techforb.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
//@WebFilter("/**")
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String token= getTokenFromRequest(request);
        final String token=getTokenFromCookies(request);
        System.out.println("TOKEN= "+token);

        final String username;
        if(token==null){
            filterChain.doFilter(request,response);
            return;
        }
        username= jwtService.getUsernameFromToken(token);
        System.out.println("USERNAME= "+username);
        System.out.println("SEGURITY CONTEXT HOLDER= "+SecurityContextHolder.getContext().getAuthentication());
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromCookies(HttpServletRequest request){
        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if("jwtToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String header= request.getHeader(HttpHeaders.AUTHORIZATION);
        if ( StringUtils.hasText(header) && header.startsWith("Bearer ") ) {
            return header.substring(7);
        }
        return null;
    }
}
