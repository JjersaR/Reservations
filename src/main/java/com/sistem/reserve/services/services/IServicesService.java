package com.sistem.reserve.services.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.sistem.reserve.services.dto.IAvailableServices;
import com.sistem.reserve.services.dto.IServiceByCategory;
import com.sistem.reserve.services.dto.IServiceByDuration;
import com.sistem.reserve.services.dto.IServiceByLocations;
import com.sistem.reserve.services.dto.IServiceByPrice;
import com.sistem.reserve.services.dto.UpdateService;
import com.sistem.reserve.services.entity.EAvailability;
import com.sistem.reserve.services.entity.Services;

public interface IServicesService {

  List<Services> findAll();

  Optional<Services> findById(Long id);

  List<Services> findByName(String name);

  List<IServiceByCategory> findByCategory(String category);

  List<IAvailableServices> findByAvailable(EAvailability available);

  List<IServiceByLocations> findByLocation(String location);

  List<IServiceByDuration> findByDuration(float duration);

  List<IServiceByPrice> findByPriceLessThan(BigDecimal price);

  void save(Services service);

  void update(UpdateService service);

  void deleteById(Long id);
}
