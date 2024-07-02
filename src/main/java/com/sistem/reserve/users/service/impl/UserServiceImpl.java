package com.sistem.reserve.users.service.impl;

import com.sistem.reserve.controller.handleEx.ObjectNoContentException;
import com.sistem.reserve.controller.handleEx.ObjectNoFoundException;
import com.sistem.reserve.roles.entity.ERole;
import com.sistem.reserve.roles.repository.IRolesRepository;
import com.sistem.reserve.users.dto.*;
import com.sistem.reserve.users.entity.Users;
import com.sistem.reserve.users.repository.IUsersRepository;
import com.sistem.reserve.users.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUsersRepository repository;

  @Autowired
  private IRolesRepository rolesRepository;

  @Override
  public List<Users> findAll() {
    if (repository.findAll().isEmpty())
      throw new ObjectNoContentException("There are no users");
    return repository.findAll();
  }

  @Override
  public Optional<Users> findById(Long id) {
    if (repository.findById(id).isEmpty())
      throw new ObjectNoFoundException("User not found");
    return repository.findById(id);
  }

  @Override
  public List<UserReservationsList> findReservationsByUserId(Long id) {
    if (repository.findReservationsByUserById(id).isEmpty())
      throw new ObjectNoContentException("This user has no reservations");
    return repository.findReservationsByUserById(id);
  }

  @Override
  public List<ReservedServicesByUserList> findServicesReservedByUserId(Long id) {
    if (repository.findServicesReservedByUserId(id).isEmpty())
      throw new ObjectNoContentException("There are no services reserved by this user");
    return repository.findServicesReservedByUserId(id);
  }

  @Override
  public List<ReservationsPendingList> findUsersWithReservesPending() {
    if (repository.findUsersWithReservesPending().isEmpty())
      throw new ObjectNoContentException("There are no users with pending reservations");
    return repository.findUsersWithReservesPending();
  }

  @Override
  public List<UsersByRangeDatesList> findUsersByRegistrationDateBetween(String startDate, String endDate) {
    if (repository.findUsersByRegistrationDateBetween(startDate, endDate).isEmpty())
      throw new ObjectNoFoundException("There are no users between these dates");
    return repository.findUsersByRegistrationDateBetween(startDate, endDate);
  }

  @Override
  public List<IUsersByName> findAllByName(String name) {
    if (repository.findAllByName(name).isEmpty())
      throw new ObjectNoContentException("There are no users with the name " + name);
    return repository.findAllByName("%" + name + "%");
  }

  @Override
  public void save(Users user) {
    // By default the role is CLIENT
    user.getRoles().add(rolesRepository.findByName(ERole.CLIENT));
    // Generate today's date
    user.setRegistrationDate(LocalDate.now());
    // encrypt password
    var password = user.getPassword();
    user.setPassword(new BCryptPasswordEncoder().encode(password));
    repository.save(user);
  }

  @Override
  public void update(UpdateUser upUser) {
    if (upUser.getId() == null) {
      throw new ObjectNoContentException("User id is required");
    }
    repository.update(upUser);
  }

  @Override
  public void deleteById(Long id) {
    var userOP = repository.findById(id);
    if (!userOP.isPresent()) {
      throw new ObjectNoFoundException("User not found");
    }
    repository.deleteById(id);
  }
}
