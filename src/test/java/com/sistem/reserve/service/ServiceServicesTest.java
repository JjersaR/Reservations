package com.sistem.reserve.service;

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
import com.sistem.reserve.services.dto.*;
import com.sistem.reserve.services.entity.EAvailability;
import com.sistem.reserve.services.entity.Services;
import com.sistem.reserve.services.repository.IServiceRepository;
import com.sistem.reserve.services.services.impl.ServicesServiceImpl;

/**
 * ServiceServicesTest
 */
@ExtendWith(MockitoExtension.class)
public class ServiceServicesTest {
  @Mock
  private IServiceRepository repository;

  @InjectMocks
  private ServicesServiceImpl service;

  List<Services> services;

  @BeforeEach
  void setUp() {
    var service1 = Services.builder().id(1L).name("Test1").build();
    var service2 = Services.builder().id(2L).name("Test2").build();
    services = List.of(service1, service2);

    lenient().when(repository.findAll()).thenReturn(services);
  }

  @Test
  void TestFindAllIsEmpty() {
    lenient().when(repository.findAll()).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.findAll());
    assertEquals("No services", exception.getMessage());
  }

  @Test
  void TestFindAllSuccess() { // ServiceServicesTest#TestFindAllSuccess
    var result = service.findAll();

    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
    assertEquals("Test1", result.get(0).getName());
    assertEquals("Test2", result.get(1).getName());
  }

  @Test
  void TestFindByIdIsEmpty() {
    lenient().when(repository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findById(1L));
    assertEquals("Service not found", exception.getMessage());
  }

  @Test
  void TestFindByIdSuccess() {
    lenient().when(repository.findById(1L)).thenReturn(Optional.of(services.get(0)));

    var result = service.findById(1L).get();

    assertEquals(1L, result.getId());
    assertEquals("Test1", result.getName());
  }

  @Test
  void TestFindByNameIsEmpty() {
    lenient().when(repository.findByName("Test1")).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findByName("Test1"));
    assertEquals("Service not found", exception.getMessage());
  }

  @Test
  void TestFindByNameSuccess() {
    lenient().when(repository.findByName("Test1")).thenReturn(services);
    lenient().when(repository.findByName("%Test1%")).thenReturn(services);

    var result = service.findByName("Test1");

    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
  }

  @Test
  void TestFindByCategoryIsEmpty() {
    lenient().when(repository.findByCategory("Test1")).thenReturn(List.of());
    lenient().when(repository.findByCategory("%Test1%")).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findByCategory("Test1"));
    assertEquals("the category does not exist", exception.getMessage());
  }

  @Test
  void TestFindByCategorySuccess() {
    IServiceByCategory category1 = mock(IServiceByCategory.class);
    IServiceByCategory category2 = mock(IServiceByCategory.class);

    List<IServiceByCategory> list = List.of(category1, category2);
    lenient().when(category1.getName()).thenReturn("Test1");
    lenient().when(category2.getName()).thenReturn("Test2");

    lenient().when(repository.findByCategory("Test1")).thenReturn(list);
    lenient().when(repository.findByCategory("%Test1%")).thenReturn(list);

    var result = service.findByCategory("Test1");

    assertEquals(2, result.size());
    assertEquals("Test1", result.get(0).getName());
    assertEquals("Test2", result.get(1).getName());
  }

  @Test
  void TestContains() {
    assertTrue(service.contains(EAvailability.AVAILABLE));
    assertTrue(service.contains(EAvailability.RESERVED));
    assertTrue(service.contains(EAvailability.MAINTENANCE));
    assertTrue(service.contains(EAvailability.NOT_AVAILABLE));
    assertFalse(service.contains(null));
  }

  @Test
  void TestFindByAvailableNotFound() {
    var availability = EAvailability.AVAILABLE;
    when(repository.findByAvailable(availability))
        .thenThrow(new ObjectNoFoundException("This category does not exist"));

    Exception exception = assertThrows(ObjectNoFoundException.class,
        () -> service.findByAvailable(availability));
    assertEquals("This category does not exist", exception.getMessage());
  }

  @Test
  void TestFindByAvailableSuccess() {
    var availableList = List.of(mock(IAvailableServices.class), mock(IAvailableServices.class));
    when(repository.findByAvailable(EAvailability.AVAILABLE)).thenReturn(availableList);

    var result = service.findByAvailable(EAvailability.AVAILABLE);

    assertEquals(2, result.size());
  }

  @Test
  void TestFindByLocationIsEmpty() {
    lenient().when(repository.findByLocation("Test1")).thenReturn(List.of());
    lenient().when(repository.findByLocation("%Test1%")).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.findByLocation("Test1"));
    assertEquals("Location not found", exception.getMessage());
  }

  @Test
  void TestFindByLocationSuccess() {
    var list = List.of(mock(IServiceByLocations.class), mock(IServiceByLocations.class));

    lenient().when(repository.findByLocation("Test1")).thenReturn(list);
    lenient().when(repository.findByLocation("%Test1%")).thenReturn(list);

    var result = service.findByLocation("Test1");

    assertEquals(2, result.size());
  }

  @Test
  void TestFindByDurationIsEmpty() {
    lenient().when(repository.findByDuration(1f)).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.findByDuration(1f));
    assertEquals("There are no services with this duration", exception.getMessage());
  }

  @Test
  void TestFindByDurationSuccess() {
    var list = List.of(mock(IServiceByDuration.class), mock(IServiceByDuration.class));
    lenient().when(repository.findByDuration(1f)).thenReturn(list);

    var result = service.findByDuration(1f);

    assertEquals(2, result.size());
  }

  @Test
  void TestFindByPriceLessThanIsEmpty() {
    lenient().when(repository.findByPriceLessThan(java.math.BigDecimal.ONE)).thenReturn(List.of());

    Exception exception = assertThrows(ObjectNoFoundException.class,
        () -> service.findByPriceLessThan(java.math.BigDecimal.ONE));
    assertEquals("There are no prices lower than the entered price", exception.getMessage());
  }

  @Test
  void TestFindByPriceLessThanSuccess() {
    var list = List.of(mock(IServiceByPrice.class), mock(IServiceByPrice.class));
    lenient().when(repository.findByPriceLessThan(java.math.BigDecimal.ONE)).thenReturn(list);

    var result = service.findByPriceLessThan(java.math.BigDecimal.ONE);
    assertEquals(2, result.size());
  }

  @Test
  void TestSaveSuccess() {
    var serviceSaved = new Services();

    serviceSaved.setId(1L);
    serviceSaved.setName("Test Name");
    serviceSaved.setCategory("Test Category");
    serviceSaved.setDuration(1f);
    serviceSaved.setLocation("Test Location");
    serviceSaved.setPrice(java.math.BigDecimal.TEN);

    service.save(serviceSaved);

    assertEquals(1L, serviceSaved.getId());
    assertEquals("Test Name", serviceSaved.getName());
    assertEquals("Test Category", serviceSaved.getCategory());
    assertEquals(1f, serviceSaved.getDuration());
    assertEquals("Test Location", serviceSaved.getLocation());
    assertEquals(java.math.BigDecimal.TEN, serviceSaved.getPrice());

    verify(repository).save(serviceSaved);
  }

  @Test
  void TestUpdateIdNull() {
    var updateUser = mock(UpdateService.class);

    lenient().when(updateUser.getId()).thenReturn(null);

    Exception exception = assertThrows(ObjectNoContentException.class, () -> service.update(updateUser));
    assertEquals("The id is required", exception.getMessage());
  }

  @Test
  void TestUpdateSuccess() {
    var serviceUpdated = mock(UpdateService.class);

    service.update(serviceUpdated);

    verify(repository).update(serviceUpdated);
  }

  @Test
  void TestDeleteByIdNotFound() {
    lenient().when(repository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ObjectNoFoundException.class, () -> service.deleteById(1L));
    assertEquals("This user does not exist", exception.getMessage());

    verify(repository, never()).deleteById(1L);
  }

  @Test
  void TestDeleteByIdSuccess() {
    var serviceOp = mock(Services.class);
    lenient().when(repository.findById(1L)).thenReturn(Optional.of(serviceOp));

    service.deleteById(1L);

    verify(repository).deleteById(1L);
  }
}
