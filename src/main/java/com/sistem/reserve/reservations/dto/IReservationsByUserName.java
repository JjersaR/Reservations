package com.sistem.reserve.reservations.dto;

import java.time.LocalDate;

public interface IReservationsByUserName {

  LocalDate getStartDate();

  LocalDate getEndDate();

  int getNumberPeople();

  String getStatus();

  String getNotes();

  String getServiceName();

  float getDuration();

  String getAvailable();

  String getLocation();

  String getUserName();
}
