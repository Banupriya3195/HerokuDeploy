package com.vmi.module.controller;

import com.vmi.module.model.Users;
import com.vmi.module.repo.UsersRepo;
import com.vmi.module.util.AuthorizationException;
import com.vmi.module.util.AuthorizationTokenUtil;
import com.vmi.module.util.Error;
import com.vmi.module.validations.Helper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractController
{
  @Value("${authorization.token.expiry}")
  private int authorizationSessionExpiry;
  @Resource
  private UsersRepo userRepo;
  @Autowired
  private AuthorizationTokenUtil authorizationTokenUtil;
  
  public void authorizeUser(HttpServletRequest request)
  {
    try
    {
      String[] accessTokenArray = this.authorizationTokenUtil.extractUserFromAccessToken(request);
      Users userLogin = this.userRepo.findByEmpCode(accessTokenArray[0]);
      if (userLogin != null)
      {
        if ((StringUtils.isNotBlank(userLogin.getAuthorizationToken())) && (userLogin.getAuthorizationToken().equals(accessTokenArray[1])))
        {
          userLogin.setLastLogin(Helper.getSystemDate());
          this.userRepo.save(userLogin);
        }
        else
        {
          List<Error> errors = new ArrayList();
          errors.add(new Error("UNAUTHORIZED", "Session Expired"));
          throw new AuthorizationException(errors);
        }
      }
      else
      {
        List<Error> errors = new ArrayList();
        errors.add(new Error("INVALID CREDENTIALS", "Username or Password doest not match"));
        throw new AuthorizationException(errors);
      }
    }
    catch (Exception exception)
    {
      throw new AuthorizationException(((AuthorizationException)exception).getErrors());
    }
  }
  
  public Users authorizeAndRetrieveUser(HttpServletRequest request)
  {
    authorizeUser(request);
    
    Users userLogin = this.userRepo.findByEmpCode(this.authorizationTokenUtil.extractUserFromAccessToken(request)[0]);
    return userLogin;
  }
}
