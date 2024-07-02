package com.sistem.reserve.reservations.service.impl;

import com.sistem.reserve.controller.handleEx.ObjectNoContentException;
import com.sistem.reserve.controller.handleEx.ObjectNoFoundException;
import com.sistem.reserve.reservations.dto.*;
import com.sistem.reserve.reservations.entity.*;
import com.sistem.reserve.reservations.repository.IReservationsRepository;
import com.sistem.reserve.users.repository.IUsersRepository;
import com.sistem.reserve.reservations.service.IReservationService;
import com.sistem.reserve.services.repository.IServiceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements IReservationService {
  @Autowired
  private IReservationsRepository repository;

  @Autowired
  private IUsersRepository userRepository;

  @Autowired
  private IServiceRepository serviceRepository;

  @Override
  public List<Reservations> findAll() {
    if (repository.findAll().isEmpty())
      throw new ObjectNoContentException("Without reservation");
    return repository.findAll();
  }

  @Override
  public Optional<Reservations> findById(Long id) {
    if (repository.findById(id).isEmpty())
      throw new ObjectNoFoundException("This id does not exist");
    return repository.findById(id);
  }

  @Override
  public List<IReservationsByUser> findByUserId(Long id) {
    if (repository.findByUserId(id).isEmpty())
      throw new ObjectNoFoundException("This user has not made any reservations");
    return repository.findByUserId(id);
  }

  @Override
  public List<IReservationsByUserName> findReservationsByUserName(String name) {
    if (repository.findReservationsByUserName(name).isEmpty())
      throw new ObjectNoContentException("This user has not made any reservations");
    return repository.findReservationsByUserName("%" + name + "%");
  }

  @Override
  public List<IReservationsByServices> findByServiceName(Long id, String name) {
    if (!repository.findById(id).isPresent()) {
      throw new ObjectNoFoundException("This user was not found");
    }
    if (repository.findByServiceName(id, "%" + name + "%").isEmpty()) {
      throw new ObjectNoContentException("There are no reservations with this user");
    }
    return repository.findByServiceName(id, "%" + name + "%");
  }

  @Override
  public List<IReservationsByStatus> findReservationsByStatus(reserveStatus status) {
    if (repository.findReservationsByStatus(status).isEmpty())
      throw new ObjectNoContentException("No reservations for this status");
    return repository.findReservationsByStatus(status);
  }

  @Override
  public void save(Reservations reservation) {
    var user = userRepository.findById(reservation.getUser().getId())
        .orElseThrow(() -> new ObjectNoFoundException("This user dont exist"));
    var service = serviceRepository.findById(reservation.getService().getId())
        .orElseThrow(() -> new ObjectNoFoundException("This service dont exist"));

    Reservations reservationSave = new Reservations();
    reservationSave.setUser(user);
    reservationSave.setService(service);
    reservationSave.setNumPersons(reservation.getNumPersons());
    reservationSave.setStartDate(reservation.getStartDate());
    reservationSave.setEndDate(reservation.getEndDate());
    reservationSave.setStatus(reservation.getStatus());
    reservationSave.setNotes(reservation.getNotes());

    repository.save(reservationSave);
  }

  @Override
  public void update(UpdateReservation reservation) {
    if (reservation.getId() == null) {
      throw new ObjectNoContentException("User id is required");
    }
    repository.update(reservation);
  }

  @Override
  public void deleteById(Long id) {
    if (id == null) {
      throw new ObjectNoContentException("The id is required");
    }
    repository.findById(id).orElseThrow(() -> new ObjectNoFoundException("This reservation dont exist"));
    repository.deleteById(id);
  }
}
