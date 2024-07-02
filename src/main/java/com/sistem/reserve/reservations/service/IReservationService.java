package com.sistem.reserve.reservations.service;

import java.util.List;
import java.util.Optional;

import com.sistem.reserve.reservations.dto.*;
import com.sistem.reserve.reservations.entity.*;

public interface IReservationService {

  List<Reservations> findAll();

  Optional<Reservations> findById(Long id);

  List<IReservationsByUser> findByUserId(Long id);

  List<IReservationsByUserName> findReservationsByUserName(String name);

  List<IReservationsByServices> findByServiceName(Long id, String name);

  List<IReservationsByStatus> findReservationsByStatus(reserveStatus status);

  void save(Reservations reservation);

  void update(UpdateReservation reservation);

  void deleteById(Long id);
}
