package com.sistem.reserve.reservations.dto;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReservationSave {

  @NotNull
  private Long userId;

  @NotNull
  private int numPersons;

  @NotNull
  private Long serviceId;

  @NotNull
  @FutureOrPresent
  private LocalDate startDate;

  @NotNull
  @Future
  private LocalDate endDate;

  @NotBlank
  @Pattern(regexp = "^(PENDING|CONFIRMED|CANCELLED|COMPLETED|IN_PROGRESS|NO_SHOW|REPROGRAMMABLE)$")
  private String status;

  @Nullable
  private String notes;

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
