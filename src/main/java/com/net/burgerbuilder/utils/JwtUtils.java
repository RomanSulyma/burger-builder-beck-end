package com.net.burgerbuilder.utils;

import com.net.burgerbuilder.configuration.MyUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Utils component for validating and generating JWT tokens
 */
@RequiredArgsConstructor
@Component
public class JwtUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${token.expiration.time.seconds}")
  private Integer TOKEN_EXPIRATION_TIME;

  @Value("${jwt.secret}")
  private String JWT_SECRET;

  /**
   * Generates JWT token
   *
   * @param authentication - Authentication principal
   * @return - JWT token
   */
  public String generateJwtToken(final Authentication authentication) {

    final MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();
    final Date expirationTime =
            java.sql.Timestamp.valueOf(LocalDateTime.now().plusSeconds(TOKEN_EXPIRATION_TIME));

    System.out.println(expirationTime);

    return Jwts.builder()
              .setSubject(userPrincipal.getUsername())
              .setIssuedAt(new Date())
              .setExpiration(expirationTime)
              .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
              .compact();
  }

  /**
   * Get username from token
   *
   * @param token - JWT token
   * @return - username
   */
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * Validates JWT token
   *
   * @param token - JWT token
   * @return - true/false
   */
  public boolean validateJwtToken(String token) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      LOGGER.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
