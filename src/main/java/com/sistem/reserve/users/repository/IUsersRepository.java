package com.sistem.reserve.users.repository;

import com.sistem.reserve.users.dto.*;
import com.sistem.reserve.users.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
  // Obtener un Usuario por ID
  Optional<Users> findById(Long id);

  // Obtener las Reservas de un Usuario
  @Query(value = "SELECT DISTINCT u.id id, CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) Name, r.number_people numPersons, s.name ServiceName, r.startDate startDate, r.endDate endDate, r.status status FROM Reservations r INNER JOIN Services s ON r.service_id = s.id INNER JOIN Users u ON u.id = :id", nativeQuery = true)
  List<UserReservationsList> findReservationsByUserById(Long id);

  // Obtener Servicios Reservados por un Usuario
  @Query(value = "SELECT CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) Name, s.name ServiceName, s.price price, s.category category, s.duration duration, s.location location, r.status status FROM Users u INNER JOIN Reservations r ON u.id = r.user_id INNER JOIN Services s ON s.id = r.service_id WHERE u.id = :id", nativeQuery = true)
  List<ReservedServicesByUserList> findServicesReservedByUserId(Long id);

  // Obtener Usuarios con Reservas Pendientes
  @Query(value = "SELECT DISTINCT u.id, CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) Name, u.phone, u.email FROM Users u INNER JOIN Reservations r ON u.id = r.user_id WHERE r.status = 'PENDING'", nativeQuery = true)
  List<ReservationsPendingList> findUsersWithReservesPending();

  // Obtener Usuarios Registrados en un Rango de Fechas
  @Query(value = "SELECT CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) Name, u.registrationDate, u.email, u.phone FROM Users u WHERE u.registrationDate BETWEEN :startDate AND :endDate", nativeQuery = true)
  List<UsersByRangeDatesList> findUsersByRegistrationDateBetween(String startDate, String endDate);

  // encontrar por nombre
  @Query(value = "select u.id id, u.email email, CONCAT(u.name,' ', u.paternalLastName, ' ', u.maternalLastName) Name, u.phone phone, r.startDate startDate, r.endDate endDate, r.status status, s.name as nameService from Users u inner join Reservations r on r.user_id = u.id inner join Services s on s.id = r.service_id  where u.name like :name", nativeQuery = true)
  List<IUsersByName> findAllByName(@Param("name") String name);

  Users findByName(String name);

  // actualizar
  @Transactional
  @Modifying
  @Query("UPDATE Users u SET u.name = COALESCE(:#{#user.name}, u.name), u.paternalLastName = COALESCE(:#{#user.paternalLastName}, u.paternalLastName), u.maternalLastName = COALESCE(:#{#user.maternalLastName}, u.maternalLastName), u.email = COALESCE(:#{#user.email}, u.email), u.phone = COALESCE(:#{#user.phone}, u.phone), u.password = COALESCE(:#{#user.password}, u.password) WHERE u.id = :#{#user.id}")
  void update(@Param("user") UpdateUser user);
}
