package com.sistem.reserve.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistem.reserve.roles.entity.ERole;
import com.sistem.reserve.roles.entity.Roles;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {

  Roles findByName(ERole rol);
}
