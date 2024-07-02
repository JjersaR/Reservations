package com.sistem.reserve.services.dto;

import java.math.BigDecimal;

import com.sistem.reserve.services.entity.EAvailability;

public interface IAvailableServices {
  String getName();

  BigDecimal getPrice();

  float getDuration();

  EAvailability getAvailable();

  String getLocation();

  String getDescription();
}
