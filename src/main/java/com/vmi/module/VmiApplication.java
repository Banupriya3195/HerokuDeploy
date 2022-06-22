package com.vmi.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.vmi.module"})
public class VmiApplication
  extends SpringBootServletInitializer
{
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
  {
    return application.sources(new Class[] { VmiApplication.class });
  }
  
  public static void main(String[] args)
    throws Exception
  {
    SpringApplication.run(VmiApplication.class, args);
  }
}
