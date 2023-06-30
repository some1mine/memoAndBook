package com.example.memoandbook.config.jwt;

import com.example.memoandbook.domain.common.UserVo;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationProvider {
  private final String secretKey = "secretKey";
  private final long tokenValidTime = 1000L * 60 * 60 * 24;
  public String createToken(String email, Long id) {
    Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(email)).setId(Aes256Util.encrypt(id.toString()));
    Date now = new Date();

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenValidTime))
        .signWith(SignatureAlgorithm.HS256, secretKey).compact();
  }
  public boolean validateToken(String jwtToken) {
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
    return !claimsJws.getBody().getExpiration().before(new Date());
  }

  public UserVo getUserVo(String token) {
    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(claims.getId()))),
        Aes256Util.decrypt(claims.getSubject()));
  }
}
