package com.vmi.module.auditing;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl
  implements AuditorAware<String>
{
  public String getCurrentAuditor()
  {
    return getUserValue();
  }
  
  public String getUserValue()
  {
    return AuditUser.getUserName();
  }
}
