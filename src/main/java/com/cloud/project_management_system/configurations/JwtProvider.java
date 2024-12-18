package com.cloud.project_management_system.configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtProvider {
  private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;
  private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
      JwtConstant.SECRET_KEY.getBytes());

  public String generateToken(UserDetails userDetails){
   return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
        .subject(userDetails.getUsername())
        .signWith(SECRET_KEY).compact();
  }


  public String extractEmail(String jwt){
    return extractClaims(jwt, Claims::getSubject);
  }

  private <T> T extractClaims(String jwt, Function<Claims, T> claimFunction){
    return claimFunction.apply(Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(jwt).getPayload());
  }

}
