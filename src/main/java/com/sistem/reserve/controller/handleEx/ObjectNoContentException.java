package com.sistem.reserve.controller.handleEx;

public class ObjectNoContentException extends RuntimeException {
  public ObjectNoContentException(String message) {
    super(message);
  }

  public ObjectNoContentException(String message, Throwable cause) {
    super(message, cause);
  }
}
