package com.sistem.reserve.services.dto;

import java.math.BigDecimal;

public interface IServiceByPrice {

  String getName();

  BigDecimal getPrice();

  String getCategory();

  int getDuration();

  String getAvailable();

  String getLocation();

  String getDescription();
}
