package com.sistem.reserve.reservations.dto;

import com.sistem.reserve.reservations.entity.reserveStatus;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public class AllReservations implements Serializable {

  private Long id;

  @Value("#{target.user.name}")
  private String userName;

  private int numPersons;

  @Value("#{target.service.name}")
  private String serviceName;

  private LocalDate startDate;

  private LocalDate endDate;

  private reserveStatus status;

  private String notes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getNumPersons() {
    return numPersons;
  }

  public void setNumPersons(int numPersons) {
    this.numPersons = numPersons;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
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

  public reserveStatus getStatus() {
    return status;
  }

  public void setStatus(reserveStatus status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
