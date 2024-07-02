package com.sistem.reserve.controller.handleEx;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReservationsExceptionHandler {

  @ExceptionHandler(ObjectNoFoundException.class)
  public ResponseEntity<Object> handlerObjectNoFoundException(ObjectNoFoundException objectNoFoundException) {
    var exception = new ReservationsException(objectNoFoundException.getMessage(), objectNoFoundException.getCause(),
        HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ObjectNoContentException.class)
  public ResponseEntity<Object> handlerObjectNoContentException(ObjectNoContentException noContent) {
    var exception = new ReservationsException(noContent.getMessage(), noContent.getCause(), HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(exception, HttpStatus.NO_CONTENT);
  }
}
