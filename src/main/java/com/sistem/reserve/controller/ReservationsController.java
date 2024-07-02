package com.sistem.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sistem.reserve.reservations.mapper.IReservationMapper.INSTANCE;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.sistem.reserve.reservations.dto.AllReservations;
import com.sistem.reserve.reservations.dto.IReservationsByUserName;
import com.sistem.reserve.reservations.dto.ReservationSave;
import com.sistem.reserve.reservations.dto.UpdateReservation;
import com.sistem.reserve.reservations.dto.IReservationsByServices;
import com.sistem.reserve.reservations.dto.IReservationsByStatus;
import com.sistem.reserve.reservations.service.IReservationService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAnyRole('RECEPTION', 'CLIENT')")
@RequestMapping("/reservation")
public class ReservationsController {

  @Autowired
  private IReservationService reservationService;

  @GetMapping("/all")
  public ResponseEntity<List<AllReservations>> findAll() {
    return ResponseEntity.ok(INSTANCE.toReservationListDto(reservationService.findAll()));
  }

  @GetMapping("/full")
  public ResponseEntity<List<IReservationsByUserName>> findByName(@RequestParam String name) {
    return ResponseEntity.ok(reservationService.findReservationsByUserName(name));
  }

  @GetMapping("/service-name")
  public ResponseEntity<List<IReservationsByServices>> findByServiceName(@RequestParam Long id,
      @RequestParam String service) {
    return ResponseEntity.ok(reservationService.findByServiceName(id, service));
  }

  @GetMapping("/status")
  public ResponseEntity<List<IReservationsByStatus>> findByStatus(@RequestParam String name) {
    return ResponseEntity.ok(reservationService.findReservationsByStatus(INSTANCE.toEnum(name.toUpperCase())));
  }

  @PostMapping("/save")
  public ResponseEntity<String> saveReservation(@RequestBody @Valid ReservationSave reservationSave)
      throws URISyntaxException {
    reservationService.save(INSTANCE.toEntity(reservationSave));
    return ResponseEntity.created(new URI("/reservation/save")).build();
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateReservation(@RequestParam Long id,
      @RequestBody @Valid UpdateReservation updateReservation) {
    updateReservation.setId(id);
    reservationService.update(updateReservation);
    return ResponseEntity.ok("Reservation " + id + " Updated");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteReservation(@RequestParam Long id) {
    reservationService.deleteById(id);
    return ResponseEntity.ok("Reservation " + id + " Deleted");
  }
}
