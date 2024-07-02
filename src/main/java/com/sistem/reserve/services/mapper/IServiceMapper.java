package com.sistem.reserve.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sistem.reserve.services.dto.AllService;
import com.sistem.reserve.services.dto.ServiceSave;
import com.sistem.reserve.services.entity.Services;

@Mapper
public interface IServiceMapper {

  IServiceMapper INSTANCE = Mappers.getMapper(IServiceMapper.class);

  List<AllService> toServiceListDto(List<Services> services);

  AllService toServiceDto(Services services);

  @Mapping(target = "id", ignore = true)
  Services saveToDto(ServiceSave serviceSave);
}
