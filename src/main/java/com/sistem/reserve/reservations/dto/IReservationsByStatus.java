package com.sistem.reserve.reservations.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IReservationsByStatus {

  @JsonIgnore
  Long getId();

  String getUserName();

  int getNumPersons();

  String getServiceName();

  LocalDate getStartDate();

  LocalDate getEndDate();

  String getNotes();
}
