package com.sistem.reserve.services.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistem.reserve.services.dto.*;
import com.sistem.reserve.services.entity.EAvailability;
import com.sistem.reserve.services.entity.Services;

@Repository
public interface IServiceRepository extends JpaRepository<Services, Long> {

  List<Services> findAll();

  // Service by id
  Optional<Services> findById(Long id);

  @Query(value = "SELECT * FROM Services s WHERE s.name LIKE :name", nativeQuery = true)
  List<Services> findByName(String name);

  @Query(value = "SELECT * FROM Services s WHERE s.name = ':name'", nativeQuery = true)
  Services findByServiceName(String name);

  // Service by category
  @Query(value = "SELECT * FROM Services s WHERE s.category LIKE :category", nativeQuery = true)
  List<IServiceByCategory> findByCategory(String category);

  // Service by available
  List<IAvailableServices> findByAvailable(EAvailability available);

  // Service by location
  @Query(value = "SELECT * FROM Services s WHERE s.location LIKE :location", nativeQuery = true)
  List<IServiceByLocations> findByLocation(String location);

  // Service by duration
  List<IServiceByDuration> findByDuration(float duration);

  // Cheap Service than a price
  List<IServiceByPrice> findByPriceLessThan(BigDecimal price);

  @Transactional
  @Modifying
  @Query("UPDATE Services s SET s.name = COALESCE(:#{#uS.name},s.name), s.price = COALESCE(:#{#uS.price}, s.price), s.category = COALESCE(:#{#uS.category}, s.category), s.duration = COALESCE(:#{#uS.duration}, s.duration), s.available = COALESCE(:#{#uS.available}, s.available), s.location = COALESCE(:#{#uS.location}, s.location), s.description = COALESCE(:#{#uS.description}, s.description) WHERE s.id =:#{#uS.id}")
  void update(UpdateService uS);
}
