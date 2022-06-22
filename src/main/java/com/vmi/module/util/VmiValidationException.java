package com.vmi.module.util;

import com.vmi.module.validations.ErrorDetail;
import java.util.ArrayList;

public class VmiValidationException
  extends RuntimeException
{
  private static final long serialVersionUID = -7042645303986355155L;
  
  public VmiValidationException(ArrayList<ErrorDetail> errorDetailList)
  {
    super("error");
  }
  
  public VmiValidationException(String msg, Throwable cause)
  {
    super(msg, cause);
  }
}
