package com.sistem.reserve.controller;

import static com.sistem.reserve.users.mapper.IUserMapper.INSTANCE;

import com.sistem.reserve.users.dto.*;
import com.sistem.reserve.users.entity.Users;
import com.sistem.reserve.users.service.IUserService;

import jakarta.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/reservation/user")
public class UsersController {

  @Autowired
  private IUserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<List<ListAll>> findAllUsers() {
    return ResponseEntity.ok(INSTANCE.toAllUsersDTO(userService.findAll()));
  }

  @PreAuthorize("hasRole('RECEPTION')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ListAll> findById(@PathVariable Long id) {
    var userOp = userService.findById(id);
    if (!userOp.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    Users user = userOp.get();
    return ResponseEntity.ok(INSTANCE.toUserDTO(user));
  }

  @PreAuthorize("hasRole('CLIENT')")
  @GetMapping("/{id}/reservations")
  public ResponseEntity<List<UserReservationsList>> findReservationById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findReservationsByUserId(id));
  }

  @PreAuthorize("hasRole('CLIENT')")
  @GetMapping("/{id}/reserved-services")
  public ResponseEntity<List<ReservedServicesByUserList>> findReservedServicesById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findServicesReservedByUserId(id));
  }

  @PreAuthorize("hasAnyRole('RECEPTION','ADMIN')")
  @GetMapping("/reserves-pending")
  public ResponseEntity<List<ReservationsPendingList>> findReservesPending() {
    return ResponseEntity.ok(userService.findUsersWithReservesPending());
  }

  @PreAuthorize("hasRole('RECEPTION')")
  @GetMapping("/dates")
  public ResponseEntity<List<UsersByRangeDatesList>> findUsersDateBetween(@RequestParam LocalDate startDate,
      @RequestParam LocalDate endDate) {
    var startDateString = startDate.toString();
    var endDateString = endDate.toString();
    return ResponseEntity.ok(userService.findUsersByRegistrationDateBetween(startDateString, endDateString));
  }

  @PreAuthorize("hasAnyRole('CLIENT', 'RECEPTION')")
  @GetMapping("/search")
  public ResponseEntity<List<IUsersByName>> findByName(@RequestParam String name) {
    return ResponseEntity.ok(userService.findAllByName(name));
  }

  @PreAuthorize("hasRole('RECEPTION')")
  @PostMapping("/save")
  public ResponseEntity<String> saveUser(@RequestBody @Valid UserSave userSave) throws URISyntaxException {
    userService.save(INSTANCE.toUserSave(userSave));
    return ResponseEntity.created(new URI("/reservation/user/save")).build();
  }

  @PreAuthorize("hasAnyRole('CLIENT', 'RECEPTION')")
  @PutMapping("/update")
  public ResponseEntity<String> updateUser(@RequestParam Long id, @RequestBody @Valid UpdateUser updateUser) {
    updateUser.setId(id);
    userService.update(updateUser);
    return ResponseEntity.ok("User " + id + " Updated");
  }

  @PreAuthorize("hasAnyRole('CLIENT', 'RECEPTION')")
  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteUserById(@RequestParam Long id) {
    userService.deleteById(id);
    return ResponseEntity.ok("User " + id + " Deleted");
  }
}
