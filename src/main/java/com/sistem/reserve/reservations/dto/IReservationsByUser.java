package com.sistem.reserve.reservations.dto;

import java.time.LocalDate;

public interface IReservationsByUser {

  String getUserName();

  int getNumPersons();

  String getServiceName();

  LocalDate getStartDate();

  LocalDate getEndDate();

  String getStatus();

  String getNotes();
}
