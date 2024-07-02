package com.sistem.reserve.reservations.dto;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class UpdateReservation {

  @JsonIgnore
  private Long id;

  @Nullable
  private Long userId;

  @Nullable
  private int numPersons;

  @Nullable
  private Long serviceId;

  @PastOrPresent
  @Nullable
  private LocalDate startDate;

  @Future
  @Nullable
  private LocalDate endDate;

  @Nullable
  @Pattern(regexp = "^(PENDING|CONFIRMED|CANCELLED|COMPLETED|IN_PROGRESS|NO_SHOW|REPROGRAMMABLE)$")
  private String status;

  @Nullable
  private String notes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public int getNumPersons() {
    return numPersons;
  }

  public void setNumPersons(int numPersons) {
    this.numPersons = numPersons;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
