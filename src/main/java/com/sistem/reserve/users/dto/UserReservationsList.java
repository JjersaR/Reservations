package com.sistem.reserve.users.dto;

import java.time.LocalDate;

public interface UserReservationsList {
  Long getId();

  String getName();

  int getNumPersons();

  String getServiceName();

  LocalDate getStartDate();

  LocalDate getEndDate();

  String getStatus();
}
