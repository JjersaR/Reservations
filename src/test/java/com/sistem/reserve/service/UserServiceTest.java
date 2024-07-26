package com.sistem.reserve.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sistem.reserve.controller.handleEx.ObjectNoContentException;
import com.sistem.reserve.controller.handleEx.ObjectNoFoundException;
import com.sistem.reserve.roles.entity.ERole;
import com.sistem.reserve.roles.entity.Roles;
import com.sistem.reserve.roles.repository.IRolesRepository;
import com.sistem.reserve.users.dto.*;
import com.sistem.reserve.users.entity.Users;
import com.sistem.reserve.users.repository.IUsersRepository;
import com.sistem.reserve.users.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * UserServiceTest
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class UserServiceTest {

  @Mock
  private IUsersRepository usersRepository;

  @Mock
  private IRolesRepository rolesRepository;

  @InjectMocks
  private UserServiceImpl userService;

  List<Users> users;

  @BeforeEach
  void setUp() {
    var user1 = Users.builder().id(1L).name("user1").build();
    var user2 = Users.builder().id(2L).name("user2").build();
    users = List.of(user1, user2);

    lenient().when(usersRepository.findAll()).thenReturn(users);
  }

  @Test
  void TestFindAllIsEmpty() {
    // UserServiceTest#TestFindAllIsEmpty
    lenient().when(usersRepository.findAll()).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class, () -> userService.findAll());
    assertEquals("There are no users", exception.getMessage());
  }

  @Test
  void TestFindAllSuccess() {
    var result = userService.findAll();

    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
    assertEquals("user1", result.get(0).getName());
    assertEquals("user2", result.get(1).getName());
  }

  @Test
  void TestFindByIdIsEmpty() {
    lenient().when(usersRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> userService.findById(1L));
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void TestFindByIdSuccess() {
    lenient().when(usersRepository.findById(1L)).thenReturn(Optional.of(users.get(0)));
    var result = userService.findById(1L).get();

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("user1", result.getName());
  }

  @Test
  void TestFindReservationsByUserIdIsEmpty() {
    List<UserReservationsList> listEmpty = List.of();

    lenient().when(usersRepository.findReservationsByUserById(1L)).thenReturn(listEmpty);

    Exception exception = assertThrows(ObjectNoContentException.class, () -> userService.findReservationsByUserId(1L));
    assertEquals("This user has no reservations", exception.getMessage());
  }

  @Test
  void TestFindReservationsByUserIdSuccess() {
    UserReservationsList list1 = mock(UserReservationsList.class);
    UserReservationsList list2 = mock(UserReservationsList.class);
    lenient().when(list1.getId()).thenReturn(1L);
    lenient().when(list2.getId()).thenReturn(2L);

    List<UserReservationsList> list = List.of(list1, list2);

    lenient().when(usersRepository.findReservationsByUserById(1L)).thenReturn(list);

    var result = userService.findReservationsByUserId(1L);

    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
  }

  @Test
  void TestFindServicesReservedByUserIdIsEmpty() {
    List<ReservedServicesByUserList> listEmpty = List.of();

    lenient().when(usersRepository.findServicesReservedByUserId(1L)).thenReturn(listEmpty);

    Exception exception = assertThrows(ObjectNoContentException.class,
        () -> userService.findServicesReservedByUserId(1L));
    assertEquals("There are no services reserved by this user", exception.getMessage());
  }

  @Test
  void TestFindServicesReservedByUserIdSuccess() {
    ReservedServicesByUserList list1 = mock(ReservedServicesByUserList.class);
    ReservedServicesByUserList list2 = mock(ReservedServicesByUserList.class);
    List<ReservedServicesByUserList> list = List.of(list1, list2);

    lenient().when(list1.getName()).thenReturn("service1");
    lenient().when(list2.getName()).thenReturn("service2");
    lenient().when(usersRepository.findServicesReservedByUserId(1L)).thenReturn(list);

    var result = userService.findServicesReservedByUserId(1L);

    assertEquals(2, result.size());
    assertEquals("service1", result.get(0).getName());
    assertEquals("service2", result.get(1).getName());
  }

  @Test
  void TestFindUsersWithReservesPendingIsEmpty() {
    List<ReservationsPendingList> list = List.of();

    lenient().when(usersRepository.findUsersWithReservesPending()).thenReturn(list);

    Exception exception = assertThrows(ObjectNoContentException.class,
        () -> userService.findUsersWithReservesPending());
    assertEquals("There are no users with pending reservations", exception.getMessage());
  }

  @Test
  void TestFindUsersWithReservesPendingIsSuccess() {
    ReservationsPendingList list1 = mock(ReservationsPendingList.class);
    ReservationsPendingList list2 = mock(ReservationsPendingList.class);
    List<ReservationsPendingList> list = List.of(list1, list2);

    lenient().when(list1.getId()).thenReturn(1L);
    lenient().when(list2.getId()).thenReturn(2L);
    lenient().when(usersRepository.findUsersWithReservesPending()).thenReturn(list);

    var result = userService.findUsersWithReservesPending();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
  }

  @Test
  void TestFindUsersByRegistrationDateBetweenIsEmpty() {
    List<UsersByRangeDatesList> list = List.of();

    lenient().when(usersRepository.findUsersByRegistrationDateBetween("2022-01-01", "2022-01-31")).thenReturn(list);

    Exception exception = assertThrows(ObjectNoFoundException.class,
        () -> userService.findUsersByRegistrationDateBetween("2022-01-01", "2022-01-31"));
    assertEquals("There are no users between these dates", exception.getMessage());
  }

  @Test
  void TestFindUsersByRegistrationDateBetweenSuccess() {
    UsersByRangeDatesList list1 = mock(UsersByRangeDatesList.class);
    UsersByRangeDatesList list2 = mock(UsersByRangeDatesList.class);
    List<UsersByRangeDatesList> list = List.of(list1, list2);

    lenient().when(list1.getName()).thenReturn("user1");
    lenient().when(list2.getName()).thenReturn("user2");
    lenient().when(usersRepository.findUsersByRegistrationDateBetween("2022-01-01", "2022-01-31")).thenReturn(list);

    var result = userService.findUsersByRegistrationDateBetween("2022-01-01", "2022-01-31");

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("user1", result.get(0).getName());
    assertEquals("user2", result.get(1).getName());
  }

  @Test
  void TestFindAllByNameIsEmpty() {
    List<IUsersByName> list = List.of();

    lenient().when(usersRepository.findAllByName("user")).thenReturn(list);

    Exception exception = assertThrows(ObjectNoContentException.class, () -> userService.findAllByName("user"));
    assertEquals("There are no users with the name user", exception.getMessage());
  }

  @Test
  void TestFindAllByNameSuccess() {
    List<IUsersByName> list = List.of(mock(IUsersByName.class), mock(IUsersByName.class));

    lenient().when(usersRepository.findAllByName("user")).thenReturn(list);
    lenient().when(usersRepository.findAllByName("%user%")).thenReturn(list);

    var result = userService.findAllByName("user");

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(list, result);
  }

  @Test
  void TestSaveSuccess() {
    var userSaved = new Users();
    var clientRole = new Roles();

    userSaved.setPassword("contraseñaSegura");
    userSaved.setRoles(new HashSet<>());

    clientRole.setName(ERole.CLIENT);

    lenient().when(rolesRepository.findByName(ERole.CLIENT)).thenReturn(clientRole);

    userService.save(userSaved);

    assertEquals(1, userSaved.getRoles().size());
    assertTrue(userSaved.getRoles().contains(clientRole));
    assertEquals(LocalDate.now(), userSaved.getRegistrationDate());

    verify(usersRepository).save(userSaved);
  }

  @Test
  void TestUpdateWithoutId() {
    UpdateUser updateUser = mock(UpdateUser.class);
    lenient().when(updateUser.getId()).thenReturn(null);

    Exception exception = assertThrows(ObjectNoContentException.class, () -> userService.update(updateUser));
    assertEquals("User id is required", exception.getMessage());
  }

  @Test
  void TestUpdateSuccess() {
    UpdateUser updateUser = mock(UpdateUser.class);

    userService.update(updateUser);

    verify(usersRepository).update(updateUser);
  }

  @Test
  void TestDeleteByIdNotFound() {
    lenient().when(usersRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> userService.deleteById(1L));
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void TestDeleteByIdSuccess() {
    lenient().when(usersRepository.findById(1L)).thenReturn(Optional.of(users.get(0)));

    userService.deleteById(1L);

    verify(usersRepository).findById(1L);
    verify(usersRepository).deleteById(1L);
  }
}
