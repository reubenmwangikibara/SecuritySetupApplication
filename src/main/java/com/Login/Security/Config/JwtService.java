package com.Login.Security.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //generated from herehttps://generate-random.org/encryption-key-generator?count=1&bytes=32&cipher=aes-256-cbc&string=&password=
    private static final String SECRET_KEY="zQVBroEyT9HQpRyatLUFO9okL/zLoeHBKecV3bodEYKgw4WgjdJQ0OKRvrw/tktj";
    public String extractUsername(String token) {
        //getSubject is the email or the username
        return extractClaim(token,Claims::getSubject);
    }
    //extracting a single claim using a generic method
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    //generates token without extra claims from the user details only
    public String nameGenerateToken( UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }


    //generates token with extract claim
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails

    ) {
        return Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //  method that will validate a token
    // we need the user details to validate if the details are for that user
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }
    //create the method expired

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //create method extract expiration

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    // extracting claim
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    //create the method

    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
