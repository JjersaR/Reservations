package com.sistem.reserve.users.service;

import com.sistem.reserve.users.dto.*;
import com.sistem.reserve.users.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  // listar todos
  List<Users> findAll();

  // Obtener un Usuario por ID
  Optional<Users> findById(Long id);

  // Obtener las Reservas de un Usuario
  List<UserReservationsList> findReservationsByUserId(Long id);

  // Obtener Servicios Reservados por un Usuario
  List<ReservedServicesByUserList> findServicesReservedByUserId(Long id);

  // Obtener Usuarios con Reservas Pendientes
  List<ReservationsPendingList> findUsersWithReservesPending();

  // Obtener Usuarios Registrados en un Rango de Fechas
  List<UsersByRangeDatesList> findUsersByRegistrationDateBetween(String startDate, String endDate);

  // encontrar por nombre
  List<IUsersByName> findAllByName(String name);

  // guardar
  void save(Users user);

  // actualizar
  void update(UpdateUser user);

  // eliminar
  void deleteById(Long id);
}
