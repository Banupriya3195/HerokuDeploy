package com.vmi.module.service;

import com.vmi.module.auditing.AuditUser;
import com.vmi.module.jwt.TokenService;
import com.vmi.module.model.UserModel;
import com.vmi.module.model.Users;
import com.vmi.module.repo.UsersRepo;
import com.vmi.module.util.AuthenticationException;
import com.vmi.module.util.AuthorizationToken;
import com.vmi.module.util.AuthorizationTokenUtil;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;
import com.vmi.module.validations.Helper;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.misc.BASE64Encoder;
import java.util.Base64;

@Transactional
@Service("AuthenticateService")
public class AuthenticateServiceImpl
  implements AuthenticateService
{
  @Autowired
  private UsersRepo userRepo;
  @Value("${authorization.token.expiry}")
  private String authorizationTokenExpiry;
  @Autowired
  private AuthorizationTokenUtil authorizationTokenUtil;
  @Autowired
  private TokenService tokenService;
  
  public Map<String, Object> userlogin(HttpSession session, UserModel userModel)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    Map<String, Object> mapResponse = new HashMap();
    Users user = this.userRepo.findByEmpCode(userModel.getEmpCode());
    if (user == null)
    {
      List<Error> errors = new ArrayList();
      errors.add(new Error("UNAUTHORIZED", "Authentication Failed due to invalid USER Code"));
      throw new AuthenticationException(errors);
    }
    if ((user != null) && (user.getPassword().equals(encryptMessage(userModel.getEmpPassword()))))
    {
      AuditUser.setUserName(userModel.getEmpCode());
      user.setLastLogin(Helper.getSystemDate());
      if (user.getAuthorizationToken() == null)
      {
        user.setAuthorizationToken(UUID.randomUUID().toString().replace("-", ""));
        user.setAuthorization(retrieveAuthorizationToken(user));
      }
      this.userRepo.save(user);
      mapResponse.put("user", user);
    }
    else
    {
      List<Error> errorDetail = new ArrayList();
      errorDetail.add(new Error("UNAUTHORIZED", "Authentication Failed due to invalid Login credentials"));
      throw new AuthenticationException(errorDetail);
    }
    return mapResponse;
  }
  
  private AuthorizationToken retrieveAuthorizationToken(Users userLogin)
  {
    AuthorizationToken authorizationToken = new AuthorizationToken();
    
    authorizationToken.setAccess_token(this.tokenService.createToken(generateEncryptedAuthorizationToken(userLogin.getEmpCode() + "_" + userLogin.getAuthorizationToken())));
    
    return authorizationToken;
  }
  
  private String generateEncryptedAuthorizationToken(String authorizationToken)
  {
    String encryptedAuthorizationToken = null;
    try
    {
      encryptedAuthorizationToken = this.authorizationTokenUtil.retrieveEncryptedAccessToken(authorizationToken);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return encryptedAuthorizationToken;
  }
  
  public String encryptMessage(String input)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    MessageDigest md = null;
    md = MessageDigest.getInstance("SHA");
    md.update(input.getBytes("UTF-8"));
    byte[] encpwd = md.digest();
    Base64.Encoder encoder = Base64.getEncoder();
    return encoder.encodeToString(encpwd);
  }
  
  public Map<String, Object> changePassword(UserModel userModel, HttpSession paramHttpSession)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    Map<String, Object> responseHashMap = new HashMap();
    Users user = this.userRepo.findByEmpCode(userModel.getEmpCode());
    if ((user != null) && (user.getPassword().equals(encryptMessage(userModel.getEmpPassword()))))
    {
      user.setPassword(encryptMessage(userModel.getNewPassword()));
      this.userRepo.save(user);
      responseHashMap.put("user", user);
    }
    else
    {
      List<Error> errorDetail = new ArrayList();
      errorDetail.add(new Error("LU001", "No Data found invalid user"));
      throw new ValidationException(errorDetail);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> userLogout(HttpServletRequest request)
  {
    Map<String, Object> responseHashMap = new HashMap();
    
    String[] accessTokenArray = StringUtils.split(this.authorizationTokenUtil.retrieveDecryptedAccessToken(request.getHeader("access_token")), "_");
    Users user = this.userRepo.findByEmpCode(accessTokenArray[0]);
    user.setAuthorizationToken(null);
    
    this.userRepo.save(user);
    responseHashMap.put("message", "user logout Sucessfully");
    return responseHashMap;
  }
}
