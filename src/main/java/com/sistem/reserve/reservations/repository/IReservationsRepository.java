package com.sistem.reserve.reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistem.reserve.reservations.dto.*;
import com.sistem.reserve.reservations.entity.Reservations;
import com.sistem.reserve.reservations.entity.reserveStatus;

@Repository
public interface IReservationsRepository extends JpaRepository<Reservations, Long> {

  Optional<Reservations> findById(Long id);

  List<IReservationsByUser> findByUserId(Long id);

  @Query(value = "SELECT r.startDate, r.endDate, r.number_people numberPeople, r.status, r.notes, s.name serviceName, s.duration, s.available, s.location, CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) userName FROM Reservations r INNER JOIN Users u ON r.user_id = u.id INNER JOIN Services s ON s.id = r.service_id WHERE CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) LIKE :name;", nativeQuery = true)
  List<IReservationsByUserName> findReservationsByUserName(String name);

  @Query(value = "SELECT CONCAT(u.name, ' ', u.paternalLastName, ' ', u.maternalLastName) userName, r.number_people numPersons, s.name serviceName, r.startDate, r.endDate, r.status FROM Reservations r INNER JOIN Services s ON r.service_id = s.id INNER JOIN Users u ON r.user_id = u.id WHERE s.name LIKE :name AND u.id = :id", nativeQuery = true)
  List<IReservationsByServices> findByServiceName(Long id, String name);

  List<IReservationsByStatus> findReservationsByStatus(reserveStatus status);

  @Transactional
  @Modifying
  @Query("UPDATE Reservations r SET r.user.id = COALESCE(:#{#reservation.userId}, r.user.id), r.numPersons = COALESCE(:#{#reservation.numPersons}, r.numPersons), r.service.id = COALESCE(:#{#reservation.serviceId}, r.service.id), r.startDate = COALESCE(:#{#reservation.startDate}, r.startDate), r.endDate = COALESCE(:#{#reservation.endDate}, r.endDate), r.status = COALESCE(:#{#reservation.status}, r.status), r.notes = COALESCE(:#{#reservation.notes}, r.notes) WHERE r.id = :#{#reservation.id}")
  void update(UpdateReservation reservation);
}
