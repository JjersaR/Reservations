package com.sistem.reserve.users.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sistem.reserve.users.dto.ListAll;
import com.sistem.reserve.users.dto.UpdateUser;
import com.sistem.reserve.users.dto.UserSave;
import com.sistem.reserve.users.entity.Users;

@Mapper
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  // list all users
  @Mapping(target = "registrationDate", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "reservations", ignore = true)
  List<ListAll> toAllUsersDTO(List<Users> users);

  // find by id
  ListAll toUserDTO(Users user);

  // update user
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "registrationDate", ignore = true)
  @Mapping(target = "reservations", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "accountNoExpired", ignore = true)
  @Mapping(target = "accountNoLocked", ignore = true)
  @Mapping(target = "credentialNoExpired", ignore = true)
  Users toUpdateUser(UpdateUser user);

  // save user
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "registrationDate", ignore = true)
  @Mapping(target = "reservations", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "accountNoExpired", ignore = true)
  @Mapping(target = "accountNoLocked", ignore = true)
  @Mapping(target = "credentialNoExpired", ignore = true)
  Users toUserSave(UserSave userSave);
}
