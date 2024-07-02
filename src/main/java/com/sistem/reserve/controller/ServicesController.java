package com.sistem.reserve.controller;

import static com.sistem.reserve.services.mapper.IServiceMapper.INSTANCE;

import com.sistem.reserve.services.dto.AllService;
import com.sistem.reserve.services.dto.IAvailableServices;
import com.sistem.reserve.services.dto.IServiceByCategory;
import com.sistem.reserve.services.dto.IServiceByLocations;
import com.sistem.reserve.services.dto.IServiceByPrice;
import com.sistem.reserve.services.dto.ServiceSave;
import com.sistem.reserve.services.dto.UpdateService;
import com.sistem.reserve.services.entity.EAvailability;
import com.sistem.reserve.services.services.IServicesService;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/reservation/services")
public class ServicesController {

  @Autowired
  private IServicesService servicesService;

  @GetMapping("/all")
  public ResponseEntity<List<AllService>> findAllServices() {
    return ResponseEntity.ok(INSTANCE.toServiceListDto(servicesService.findAll()));
  }

  @GetMapping("/name")
  public ResponseEntity<List<AllService>> findServiceByName(@RequestParam String name) {
    return ResponseEntity.ok(INSTANCE.toServiceListDto(servicesService.findByName(name)));
  }

  @GetMapping("/category")
  public ResponseEntity<List<IServiceByCategory>> findServiceByCategory(@RequestParam String name) {
    return ResponseEntity.ok(servicesService.findByCategory(name));
  }

  @GetMapping("/available")
  public ResponseEntity<List<IAvailableServices>> findServiceAvailable(@RequestParam String name) {
    var nameAvability = EAvailability.valueOf(name.toUpperCase());
    return ResponseEntity.ok(servicesService.findByAvailable(nameAvability));
  }

  @GetMapping("/location")
  public ResponseEntity<List<IServiceByLocations>> findServiceByLocation(@RequestParam String name) {
    return ResponseEntity.ok(servicesService.findByLocation(name));
  }

  @GetMapping("/duration")
  public ResponseEntity<?> findByLocation(@RequestParam float days) {
    return ResponseEntity.ok(servicesService.findByDuration(days));
  }

  @GetMapping("/lower")
  public ResponseEntity<List<IServiceByPrice>> findLowerPrice(@RequestParam BigDecimal price) {
    return ResponseEntity.ok(servicesService.findByPriceLessThan(price));
  }

  @PreAuthorize("hasRole('PROVIDER')")
  @PostMapping("/save")
  public ResponseEntity<String> saveService(@RequestBody @Valid ServiceSave serviceSave) throws URISyntaxException {
    servicesService.save(INSTANCE.saveToDto(serviceSave));
    return ResponseEntity.created(new URI("/reservation/services/save")).build();
  }

  @PreAuthorize("hasRole('PROVIDER')")
  @PutMapping("/update")
  public ResponseEntity<String> updateService(@RequestParam Long id, @RequestBody @Valid UpdateService updateService) {
    updateService.setId(id);
    servicesService.update(updateService);
    return ResponseEntity.ok("Service " + id + " Updated");
  }

  @PreAuthorize("hasRole('PROVIDER')")
  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteService(@RequestParam Long id) {
    servicesService.deleteById(id);
    return ResponseEntity.ok("Service " + id + " Deleted");
  }
}
