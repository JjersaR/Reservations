package com.sistem.reserve.services.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistem.reserve.controller.handleEx.ObjectNoContentException;
import com.sistem.reserve.controller.handleEx.ObjectNoFoundException;
import com.sistem.reserve.services.dto.*;
import com.sistem.reserve.services.entity.EAvailability;
import com.sistem.reserve.services.entity.Services;
import com.sistem.reserve.services.repository.IServiceRepository;
import com.sistem.reserve.services.services.IServicesService;

@Service
public class ServicesServiceImpl implements IServicesService {

  @Autowired
  private IServiceRepository repository;

  @Override
  public List<Services> findAll() {
    if (repository.findAll().isEmpty())
      throw new ObjectNoContentException("No services");
    return repository.findAll();
  }

  @Override
  public Optional<Services> findById(Long id) {
    if (repository.findById(id).isEmpty())
      throw new ObjectNoFoundException("Service not found");
    return repository.findById(id);
  }

  @Override
  public List<Services> findByName(String name) {
    if (repository.findByName(name).isEmpty())
      throw new ObjectNoFoundException("Service not found");
    return repository.findByName("%" + name + "%");
  }

  @Override
  public List<IServiceByCategory> findByCategory(String category) {
    if (repository.findByCategory(category).isEmpty())
      throw new ObjectNoFoundException("the category does not exist");
    return repository.findByCategory("%" + category + "%");
  }

  public boolean contains(EAvailability name) {
    for (EAvailability var : EAvailability.values()) {
      if (var == name) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<IAvailableServices> findByAvailable(EAvailability available) {
    if (!contains(available))
      throw new ObjectNoFoundException("This category does not exist");
    return repository.findByAvailable(available);
  }

  @Override
  public List<IServiceByLocations> findByLocation(String location) {
    if (repository.findByLocation(location).isEmpty())
      throw new ObjectNoFoundException("Location not found");
    return repository.findByLocation("%" + location + "%");
  }

  @Override
  public List<IServiceByDuration> findByDuration(float duration) {
    if (repository.findByDuration(duration).isEmpty())
      throw new ObjectNoContentException("There are no services with this duration");
    return repository.findByDuration(duration);
  }

  @Override
  public List<IServiceByPrice> findByPriceLessThan(BigDecimal price) {
    if (repository.findByPriceLessThan(price).isEmpty())
      throw new ObjectNoFoundException("There are no prices lower than the entered price");
    return repository.findByPriceLessThan(price);
  }

  @Override
  public void save(Services service) {
    repository.save(service);
  }

  @Override
  public void update(UpdateService service) {
    if (service.getId() == null)
      throw new ObjectNoContentException("The id is required");
    repository.update(service);
  }

  @Override
  public void deleteById(Long id) {
    var serviceOp = repository.findById(id);
    if (!serviceOp.isPresent())
      throw new ObjectNoFoundException("This user does not exist");
    repository.deleteById(id);
  }
}
