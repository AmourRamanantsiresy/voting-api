package org.ambohipotsy.votingapp.utils;

public class ValidatorUtilities {
  public static boolean isStringValid(String toValidate) {
    return toValidate != null && !toValidate.isBlank() && !toValidate.isEmpty();
  }
}
