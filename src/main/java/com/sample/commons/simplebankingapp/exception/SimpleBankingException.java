package com.sample.commons.simplebankingapp.exception;

public class SimpleBankingException extends RuntimeException   {

  public SimpleBankingException() {
    super();
  }

  public SimpleBankingException(String message) {
    super(message);
  }

  public SimpleBankingException(String message, Throwable cause) {
    super(message, cause);
  }

  public SimpleBankingException(Throwable cause) {
    super(cause);
  }

  protected SimpleBankingException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
