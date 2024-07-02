package com.sistem.reserve.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UpdateUser {
  @JsonIgnore
  private Long id;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String name;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String paternalLastName;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String maternalLastName;

  @Email
  @Nullable
  private String email;

  @Pattern(regexp = "^(55|56)\\d{8}$")
  @Nullable
  private String phone;

  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un número y un símbolo.")
  @Nullable
  private String password;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
