package com.vmi.module.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService
{
  public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
  
  public String createToken(String userId)
  {
    try
    {
      Algorithm algorithm = Algorithm.HMAC256("s4T2zOIWHMM1sxq");
      
      return JWT.create().withClaim("userId", userId.toString()).withClaim("createdAt", new Date()).sign(algorithm);
    }
    catch (UnsupportedEncodingException exception)
    {
      exception.printStackTrace();
    }
    catch (JWTCreationException exception)
    {
      exception.printStackTrace();
    }
    return null;
  }
  
  public String getUserIdFromToken(String token)
  {
    try
    {
      Algorithm algorithm = Algorithm.HMAC256("s4T2zOIWHMM1sxq");
      
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT jwt = verifier.verify(token);
      return jwt.getClaim("userId").asString();
    }
    catch (UnsupportedEncodingException exception)
    {
      exception.printStackTrace();
      return null;
    }
    catch (JWTVerificationException exception)
    {
      exception.printStackTrace();
    }
    return null;
  }
  
  public boolean isTokenValid(HttpServletRequest request)
  {
    String token = request.getHeader("Authorization");
    String userId = getUserIdFromToken(token);
    return userId != null;
  }
}
