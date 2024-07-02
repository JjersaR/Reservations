package com.sistem.reserve.controller.handleEx;

public class ObjectNoFoundException extends RuntimeException {

  public ObjectNoFoundException(String message) {
    super(message);
  }

  public ObjectNoFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
