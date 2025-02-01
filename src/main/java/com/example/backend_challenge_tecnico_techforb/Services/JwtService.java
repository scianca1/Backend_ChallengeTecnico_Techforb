package com.example.backend_challenge_tecnico_techforb.Services;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String SECRET_KEY="QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";
    private final long tokenValidityInMilliseconds = 28800 * 1000; // valido por 8hs.
    public String getToken(UserDetails u) {

        return getToken(new HashMap<>(),u);
    }
    //    private String getToken(HashMap<String,Object> extraClaims,UserDetails u){
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(u.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+tokenValidityInMilliseconds))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    private String getToken(HashMap<String, Object> extraClaims, UserDetails u) {
        // Agregar roles a las claims
        String authorities = u.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Convertir a String
                .collect(Collectors.joining(","));   // Concatenar con comas

        extraClaims.put("authorities", authorities); // Incluir en las claims

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(u.getUsername()) // Usuario como subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInMilliseconds)) // Expiración
                .signWith(getKey(), SignatureAlgorithm.HS256) // Firma
                .compact(); // Generar el token
    }

    private Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username= getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
