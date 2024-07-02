package com.sistem.reserve.permits.entity;

import jakarta.persistence.*;

@Entity
public class Permits {
  // PK
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permitID")
  private Long id;

  @Column(unique = true, nullable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private EPermits name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EPermits getName() {
    return name;
  }

  public void setName(EPermits name) {
    this.name = name;
  }
}
