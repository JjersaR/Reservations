package com.sistem.reserve.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistem.reserve.reservations.entity.Reservations;
import com.sistem.reserve.roles.entity.Roles;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

  // COLUMNS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "DATE", updatable = false)
  private LocalDate registrationDate;

  @Column(length = 45)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(length = 45)
  private String paternalLastName;

  @Column(length = 45)
  private String maternalLastName;

  @Column(unique = true)
  private String email;

  @Column(length = 10)
  private String phone;

  // SECURITY COLUMNS
  @Column(name = "is_enabled", columnDefinition = "BIT")
  private boolean isEnabled = true;

  @Column(name = "account_no_expired", columnDefinition = "BIT")
  private boolean accountNoExpired = true;

  @Column(name = "account_no_locked", columnDefinition = "BIT")
  private boolean accountNoLocked = true;

  @Column(name = "credential_no_expired", columnDefinition = "BIT")
  private boolean credentialNoExpired = true;

  // RELATIONS
  // RESERVATIONS
  @OneToMany(targetEntity = Reservations.class, mappedBy = "user", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Reservations> reservations;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "User_ID"), inverseJoinColumns = @JoinColumn(name = "Rol_ID"))
  private Set<Roles> roles = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPaternalLastName() {
    return paternalLastName;
  }

  public void setPaternalLastName(String paternalLastName) {
    this.paternalLastName = paternalLastName;
  }

  public String getMaternalLastName() {
    return maternalLastName;
  }

  public void setMaternalLastName(String maternalLastName) {
    this.maternalLastName = maternalLastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public boolean isAccountNoExpired() {
    return accountNoExpired;
  }

  public void setAccountNoExpired(boolean accountNoExpired) {
    this.accountNoExpired = accountNoExpired;
  }

  public boolean isAccountNoLocked() {
    return accountNoLocked;
  }

  public void setAccountNoLocked(boolean accountNoLocked) {
    this.accountNoLocked = accountNoLocked;
  }

  public boolean isCredentialNoExpired() {
    return credentialNoExpired;
  }

  public void setCredentialNoExpired(boolean credentialNoExpired) {
    this.credentialNoExpired = credentialNoExpired;
  }

  public List<Reservations> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservations> reservations) {
    this.reservations = reservations;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }
}
