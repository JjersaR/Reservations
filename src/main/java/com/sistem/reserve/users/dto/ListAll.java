package com.sistem.reserve.users.dto;

import java.io.Serializable;

public class ListAll implements Serializable {

  private Long id;

  private String name;

  private String paternalLastName;

  private String maternalLastName;

  private String email;

  private String phone;

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

}
