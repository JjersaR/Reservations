package com.sistem.reserve.services.dto;

import java.math.BigDecimal;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.*;

public class UpdateService {

  @JsonIgnore
  private Long id;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-zA-Z ]{0,29}$", message = "Enter a name with a minimum of 5 letters")
  private String name;

  @Nullable
  private BigDecimal price;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-zA-Z ]{0,29}$", message = "Enter a category with a minimum of 5 letters")
  private String category;

  @Nullable
  private float duration;

  @Nullable
  @Pattern(regexp = "^(AVAILABLE|RESERVED|MAINTENANCE|NOT_AVAILABLE)$", message = "Only this data is accepted: AVAILABLE|RESERVED|MAINTENANCE|NOT_AVAILABLE")
  private String available;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-zA-Z ]{0,29}$", message = "Enter a location with a minimum of 5 letters")
  private String location;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-zA-Z ]+$", message = "This field only accepts letters")
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

  public String getAvailable() {
    return available;
  }

  public void setAvailable(String available) {
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
