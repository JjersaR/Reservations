package com.sistem.reserve.services.dto;

import java.math.BigDecimal;

public interface IServiceByDuration {

  String getName();

  BigDecimal getPrice();

  String getCategory();

  float getDuration();

  String getAvailable();

  String getLocation();

  String getDescription();
}
