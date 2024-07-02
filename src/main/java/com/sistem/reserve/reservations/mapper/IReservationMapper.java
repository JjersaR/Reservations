package com.sistem.reserve.reservations.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.sistem.reserve.reservations.dto.AllReservations;
import com.sistem.reserve.reservations.dto.ReservationSave;
import com.sistem.reserve.reservations.entity.Reservations;
import com.sistem.reserve.reservations.entity.reserveStatus;
import com.sistem.reserve.services.entity.Services;
import com.sistem.reserve.users.entity.Users;

@Mapper
public interface IReservationMapper {

  IReservationMapper INSTANCE = Mappers.getMapper(IReservationMapper.class);

  Services mapService(String value);

  Users mapUser(String value);

  @Mapping(target = "serviceName", source = "service.name")
  @Mapping(target = "userName", source = "user.name")
  AllReservations toReservationDto(Reservations reservations);

  List<AllReservations> toReservationListDto(List<Reservations> reservations);

  @Named("stringToReserveStatus")
  default reserveStatus toEnum(String status) {
    return reserveStatus.valueOf(status.toUpperCase());
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user.id", source = "userId")
  @Mapping(target = "service.id", source = "serviceId")
  Reservations toEntity(ReservationSave reservationSave);
}
