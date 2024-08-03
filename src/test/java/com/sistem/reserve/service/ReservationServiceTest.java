package com.sistem.reserve.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sistem.reserve.controller.handleEx.*;
import com.sistem.reserve.reservations.dto.*;
import com.sistem.reserve.reservations.entity.*;
import com.sistem.reserve.reservations.repository.IReservationsRepository;
import com.sistem.reserve.reservations.service.impl.ReservationServiceImpl;
import com.sistem.reserve.services.entity.Services;
import com.sistem.reserve.services.repository.IServiceRepository;
import com.sistem.reserve.users.entity.Users;
import com.sistem.reserve.users.repository.IUsersRepository;

/**
 * ReservationServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

  @Mock
  private IReservationsRepository reservationRepository;

  @Mock
  private IUsersRepository userRepository;

  @Mock
  private IServiceRepository serviceRepository;

  @InjectMocks
  private ReservationServiceImpl service;

  @Test
  void TestFindAllIsEmpty() {
    lenient().when(reservationRepository.findAll()).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.findAll());
    assertEquals("Without reservation", exception.getMessage());
    verify(reservationRepository, times(1)).findAll();
  }

  @Test
  void TestFindAllSuccess() {
    var list = List.of(mock(Reservations.class), mock(Reservations.class));

    lenient().when(reservationRepository.findAll()).thenReturn(list);
    var result = service.findAll();

    assertEquals(2, result.size());
    verify(reservationRepository, times(2)).findAll();
  }

  @Test
  void TestFindByIdIsEmpty() {
    lenient().when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findById(1L));

    assertEquals("This id does not exist", exception.getMessage());
    verify(reservationRepository, times(1)).findById(1L);
  }

  @Test
  void TestFindByIdSuccess() {
    var reservationId = new Reservations();
    reservationId.setId(1L);

    lenient().when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.of(reservationId));

    var result = service.findById(1L).get();

    assertEquals(1L, result.getId());
    verify(reservationRepository, times(2)).findById(1L);
  }

  @Test
  void TestFindByUserIdIsEmpty() {
    lenient().when(reservationRepository.findByUserId(1L)).thenReturn(List.of());
    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findByUserId(1L));

    assertEquals("This user has not made any reservations", exception.getMessage());
    verify(reservationRepository, times(1)).findByUserId(1L);
  }

  @Test
  void TestFindByUserIdSuccess() {
    var reservation1 = mock(IReservationsByUser.class);
    var reservation2 = mock(IReservationsByUser.class);

    lenient().when(reservation1.getUserName()).thenReturn("John");
    lenient().when(reservation2.getUserName()).thenReturn("Jane");

    lenient().when(reservationRepository.findByUserId(1L)).thenReturn(List.of(reservation1, reservation2));

    var result = service.findByUserId(1L);

    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getUserName());
    assertEquals("Jane", result.get(1).getUserName());
    verify(reservationRepository, times(2)).findByUserId(1L);
  }

  @Test
  void TestFindReservationsByUserNameIsEmpty() {
    lenient().when(reservationRepository.findReservationsByUserName("John")).thenReturn(List.of());
    lenient().when(reservationRepository.findReservationsByUserName("%John%")).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class,
        () -> service.findReservationsByUserName("John"));

    assertEquals("This user has not made any reservations", exception.getMessage());
    verify(reservationRepository, times(1)).findReservationsByUserName("John");
  }

  @Test
  void TestFindReservationsByUserNameSuccess() {
    var reservation1 = mock(IReservationsByUserName.class);
    var reservation2 = mock(IReservationsByUserName.class);

    lenient().when(reservationRepository.findReservationsByUserName("John"))
        .thenReturn(List.of(reservation1, reservation2));
    lenient().when(reservationRepository.findReservationsByUserName("%John%"))
        .thenReturn(List.of(reservation1, reservation2));

    var result = service.findReservationsByUserName("John");

    assertEquals(2, result.size());
    verify(reservationRepository, times(1)).findReservationsByUserName("John");
  }

  @Test
  void TestFindByServiceNameIdNotPresent() {
    var serviceName = "Test Service";
    lenient().when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class,
        () -> service.findByServiceName(1L, serviceName));

    assertEquals("This user was not found", exception.getMessage());
    verify(reservationRepository, times(1)).findById(1L);
  }

  @Test
  void TestFindByServiceNameAndNameIsEmpty() {
    lenient().when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.of(new Reservations()));
    lenient().when(reservationRepository.findByServiceName(1L, "%Test Service%")).thenReturn(List.of());
    lenient().when(reservationRepository.findByServiceName(1L, "Test Service")).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class,
        () -> service.findByServiceName(1L, "Test Service"));

    assertEquals("There are no reservations with this user", exception.getMessage());
    verify(reservationRepository, times(1)).findById(1L);
    verify(reservationRepository, times(1)).findByServiceName(1L, "%Test Service%");
  }

  @Test
  void TestFindByServiceNameSuccess() {
    var list = List.of(mock(IReservationsByServices.class), mock(IReservationsByServices.class),
        mock(IReservationsByServices.class));

    lenient().when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.of(new Reservations()));
    lenient().when(reservationRepository.findByServiceName(1L, "%Test Service%")).thenReturn(list);
    lenient().when(reservationRepository.findByServiceName(1L, "Test Service")).thenReturn(list);

    var result = service.findByServiceName(1L, "Test Service");

    assertEquals(3, result.size());
    verify(reservationRepository, times(1)).findById(1L);
    verify(reservationRepository, times(2)).findByServiceName(1L, "%Test Service%");
  }

  @Test
  void TestFindReservationsByStatusIsEmpty() {
    lenient().when(reservationRepository.findReservationsByStatus(reserveStatus.NO_SHOW)).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class,
        () -> service.findReservationsByStatus(reserveStatus.NO_SHOW));

    assertEquals("No reservations for this status", exception.getMessage());
    verify(reservationRepository, times(1)).findReservationsByStatus(reserveStatus.NO_SHOW);
  }

  @Test
  void TestFindReservationsByStatusSuccess() {
    var list = List.of(mock(IReservationsByStatus.class), mock(IReservationsByStatus.class));

    lenient().when(reservationRepository.findReservationsByStatus(reserveStatus.NO_SHOW)).thenReturn(list);

    var result = service.findReservationsByStatus(reserveStatus.NO_SHOW);

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(reservationRepository, times(2)).findReservationsByStatus(reserveStatus.NO_SHOW);
  }

  @Test
  void TestSaveUserDontExist() {
    var reservation = new Reservations();
    var userNull = mock(Users.class);
    var serviceTest = mock(Services.class);

    reservation.setUser(userNull);
    reservation.setService(serviceTest);

    lenient().when(userNull.getId()).thenReturn(1L);
    lenient().when(serviceTest.getId()).thenReturn(1L);
    lenient().when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.save(reservation));

    assertEquals("This user dont exist", exception.getMessage());
    verify(reservationRepository, times(0)).save(any());
  }

  @Test
  void TestSaveServiceDontExist() {
    var reservation = new Reservations();
    var userTest = new Users();

    userTest.setId(1L);
    reservation.setUser(userTest);
    reservation.setService(new Services());

    lenient().when(serviceRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());
    lenient().when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(userTest));

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.save(reservation));

    assertEquals("This service dont exist", exception.getMessage());
    verify(reservationRepository, times(0)).save(any());
  }

  @Test
  void TestSaveSuccess() {
    var reservation = new Reservations();
    var userTest = mock(Users.class);
    var serviceTest = mock(Services.class);

    lenient().when(userTest.getId()).thenReturn(1L);
    lenient().when(serviceTest.getId()).thenReturn(1L);

    lenient().when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(userTest));
    lenient().when(serviceRepository.findById(1L)).thenReturn(java.util.Optional.of(serviceTest));

    reservation.setUser(userTest);
    reservation.setService(serviceTest);

    service.save(reservation);

    verify(reservationRepository, times(1)).save(any());
  }

  @Test
  void TestUpdateIdIsNull() {
    var reservation = new UpdateReservation();
    reservation.setId(null);

    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.update(reservation));

    assertEquals("User id is required", exception.getMessage());
    verify(reservationRepository, times(0)).save(any());
  }

  @Test
  void TestUpdateSuccess() {
    var reservation = new UpdateReservation();
    reservation.setId(1L);

    service.update(reservation);

    verify(reservationRepository, times(1)).update(any());
  }

  @Test
  void TestDeleteByIdIdNull() {
    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.deleteById(null));

    assertEquals("The id is required", exception.getMessage());
    verify(reservationRepository, times(0)).deleteById(any());
  }

  @Test
  void TestDeleteByIdNotFound() {
    lenient().when(reservationRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.deleteById(1L));

    assertEquals("This reservation dont exist", exception.getMessage());
    verify(reservationRepository, never()).deleteById(1L);
  }

  @Test
  void TestDeleteByIdSuccess() {
    lenient().when(reservationRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Reservations()));

    service.deleteById(1L);

    verify(reservationRepository, times(1)).deleteById(1L);
  }
}
