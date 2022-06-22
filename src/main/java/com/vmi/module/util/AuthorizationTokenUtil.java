package com.vmi.module.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vmi.module.jwt.TokenService;

@Component
public class AuthorizationTokenUtil
{
  private static final String DEFAULT_DIGEST = "SHA-1";
  private static final String DEFAULT_IMPL = "DESede";
  @Value("${authorization.encryption.key}")
  private String authorizationEncryptionKey;
  @Value("${authorization.secret.key.algorithm}")
  private String authorizationSecretKeyAlgorithm;
  @Value("${authorization.message.digest.algorithm}")
  private String authorizationMessageDigestAlgorithm;
  @Value("${authorization.cipher.algorithm}")
  private String authorizationCipherAlgorithm;
  @Autowired
  private TokenService tokenService;
  
  public String retrieveEncryptedAccessToken(String accessToken)
    throws Exception
  {
    Cipher cipher = Cipher.getInstance(this.authorizationCipherAlgorithm);
    byte[] plainTextByte = accessToken.getBytes();
    cipher.init(1, retrieveCryptoSecretKey());
    byte[] encryptedByte = cipher.doFinal(plainTextByte);
    return Base64.getEncoder().encodeToString(encryptedByte);
  }
  
  public String retrieveDecryptedAccessToken(String encryptedAccessToken)
  {
    String decryptedAccessToken = null;
    try
    {
      Cipher cipher = Cipher.getInstance(this.authorizationCipherAlgorithm);
      Base64.Decoder decoder = Base64.getDecoder();
      byte[] encryptedTextByte = decoder.decode(this.tokenService.getUserIdFromToken(encryptedAccessToken));
      cipher.init(2, retrieveCryptoSecretKey());
      decryptedAccessToken = new String(cipher.doFinal(encryptedTextByte));
    }
    catch (Exception exception)
    {
      List<Error> errors = new ArrayList();
      errors.add(new Error("UNAUTHORIZED", "Invalid Access Token"));
      throw new AuthorizationException(errors);
    }
    return decryptedAccessToken;
  }
  
  private SecretKey retrieveCryptoSecretKey()
    throws NoSuchAlgorithmException
  {
    MessageDigest md = MessageDigest.getInstance((String)ObjectUtils.defaultIfNull(this.authorizationMessageDigestAlgorithm, "SHA-1"));
    byte[] digestOfPassword = md.digest(this.authorizationEncryptionKey.getBytes(StandardCharsets.UTF_8));
    byte[] keyBytes = Arrays.copyOf(digestOfPassword, 16);
    return new SecretKeySpec(keyBytes, (String)ObjectUtils.defaultIfNull(this.authorizationSecretKeyAlgorithm, "DESede"));
  }
  
  public String[] extractUserFromAccessToken(HttpServletRequest request)
  {
    return StringUtils.split(retrieveDecryptedAccessToken(request.getHeader("access_token")), "_");
  }
}
