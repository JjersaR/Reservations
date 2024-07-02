package com.sistem.reserve.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sistem.reserve.services.entity.EAvailability;

public class AllService implements Serializable {

  private Long id;

  private String name;

  private BigDecimal price;

  private String category;

  private float duration;

  private EAvailability available;

  private String location;

  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public float getDuration() {
    return duration;
  }

  public void setDuration(float duration) {
    this.duration = duration;
  }

  public EAvailability getAvailable() {
    return available;
  }

  public void setAvailable(EAvailability available) {
    this.available = available;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
