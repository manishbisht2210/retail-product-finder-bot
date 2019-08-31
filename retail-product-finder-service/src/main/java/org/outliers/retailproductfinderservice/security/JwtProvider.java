/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.outliers.retailproductfinderservice.service.TenantService;
import org.outliers.retailproductfinderservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

  @Value("${retail.product.finder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${retail.product.finder.app.jwtIssuer}")
  private String issuer;

  private TenantService tenantService;

  @Autowired
  public JwtProvider(TenantService tenantService) {
    this.tenantService = tenantService;
  }


  public String generateJwtToken(Authentication authentication, String tenantName) {
    UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
    Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
    claims.put("scopes", userPrincipal.getAuthorities());
    claims.put("tenantName", tenantName);
    claims.put("tenantId",
        tenantService.findByTenantName(tenantName).get().getTenantId());
    return Jwts.builder()
        .setClaims(claims)
        .setIssuer(issuer)
        .setIssuedAt(new Date())
        .setExpiration(
            new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 10000))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature -> Message: {} ", e);
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token -> Message: {}", e);
    } catch (ExpiredJwtException e) {
      logger.error("Expired JWT token -> Message: {}", e);
    } catch (UnsupportedJwtException e) {
      logger.error("Unsupported JWT token -> Message: {}", e);
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty -> Message: {}", e);
    }

    return false;
  }
}