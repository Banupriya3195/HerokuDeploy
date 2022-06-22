package com.vmi.module.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler
{
  @ExceptionHandler({VmiValidationException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ResponseEntity<Error> customErrorMessageException(VmiValidationException e)
  {
    return new ResponseEntity("s", HttpStatus.FORBIDDEN);
  }
}
