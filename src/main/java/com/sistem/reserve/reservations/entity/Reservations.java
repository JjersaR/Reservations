package com.sistem.reserve.reservations.entity;

import com.sistem.reserve.services.entity.Services;
import com.sistem.reserve.users.entity.Users;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservations")
public class Reservations {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(targetEntity = Users.class)
  private Users user;

  @Column(name = "number_people", nullable = true)
  private int numPersons;

  @ManyToOne(targetEntity = Services.class)
  @JoinColumn(name = "service_id")
  private Services service;

  @Column(columnDefinition = "DATE", nullable = false)
  private LocalDate startDate;

  @Column(columnDefinition = "DATE", nullable = false)
  private LocalDate endDate;

  @Column(length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private reserveStatus status;

  @Column(columnDefinition = "TEXT")
  private String notes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public int getNumPersons() {
    return numPersons;
  }

  public void setNumPersons(int numPersons) {
    this.numPersons = numPersons;
  }

  public Services getService() {
    return service;
  }

  public void setService(Services service) {
    this.service = service;
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
