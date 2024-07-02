package com.sistem.reserve.users.dto;

import java.time.LocalDate;

import com.sistem.reserve.reservations.entity.reserveStatus;

public interface IUsersByName {

  int getId();

  String getEmail();

  String getName();

  String getPhone();

  LocalDate getStartDate();

  LocalDate getEndDate();

  reserveStatus getStatus();

  String getNameService();
}
