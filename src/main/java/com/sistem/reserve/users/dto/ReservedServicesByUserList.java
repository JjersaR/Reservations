package com.sistem.reserve.users.dto;

import java.math.BigDecimal;

public interface ReservedServicesByUserList {
  String getName();

  String getServiceName();

  BigDecimal getPrice();

  String getCategory();

  Float getDuration();

  String getLocation();

  String getStatus();
}
