package com.vmi.module.service;

import com.vmi.module.model.UserModel;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract interface AuthenticateService
{
  public abstract Map<String, Object> userlogin(HttpSession paramHttpSession, UserModel paramUserModel)
    throws NoSuchAlgorithmException, UnsupportedEncodingException;
  
  public abstract Map<String, Object> changePassword(UserModel paramUserModel, HttpSession paramHttpSession)
    throws NoSuchAlgorithmException, UnsupportedEncodingException;
  
  public abstract Map<String, Object> userLogout(HttpServletRequest paramHttpServletRequest);
}
