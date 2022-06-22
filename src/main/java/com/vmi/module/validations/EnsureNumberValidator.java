package com.vmi.module.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnsureNumberValidator
  implements ConstraintValidator<EnsureNumber, Object>
{
  private EnsureNumber ensureNumber;
  
  public void initialize(EnsureNumber constraintAnnotation)
  {
    this.ensureNumber = constraintAnnotation;
  }
  
  public boolean isValid(Object value, ConstraintValidatorContext arg1)
  {
    if ((value == null) || (value.equals(""))) {
      return true;
    }
    String regex = this.ensureNumber.decimal() ? "^[0-9]*$" : "^[0-9]*$";
    String data = String.valueOf(value);
    return data.matches(regex);
  }
}
