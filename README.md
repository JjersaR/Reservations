# Reservation System

This is a reservation system that contains the following roles:
-   ADMIN
-  CLIENT
- PROVIDER
-  RECEPTION

The **ADMIN** can list all the users that exist in the database.
The **CLIENT** can view his reservations, the services he has booked, update his data and delete his user name.
The **PROVIDER** can make the CRUD on the service table so that it can manage the services it provides.
The **RECEPTION** can see the users by their id or name, can see the users that were added between certain dates, can do CRUD on the users and can manage all the reservations.


 The Reservations service interface has this:
```java
public interface IReservationService {

  List<Reservations> findAll();

  Optional<Reservations> findById(Long id);

  List<IReservationsByUser> findByUserId(Long id);

  List<IReservationsByUserName> findReservationsByUserName(String name);

  List<IReservationsByServices> findByServiceName(Long id, String name);

  List<IReservationsByStatus> findReservationsByStatus(reserveStatus status);

  void save(Reservations reservation);

  void update(UpdateReservation reservation);

  void deleteById(Long id);
}
```
 The Services service interface has this:
 ```java
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
```

The Users service interface has this:
```java
public interface IUserService {
  // list all
  List<Users> findAll();

  // Get user by id
  Optional<Users> findById(Long id);

  // Get reservations by id
  List<UserReservationsList> findReservationsByUserId(Long id);

  // Get Services Reserved by a User
  List<ReservedServicesByUserList> findServicesReservedByUserId(Long id);

  // Get Users with Pending Reservations
  List<ReservationsPendingList> findUsersWithReservesPending();

  // Get Registered Users in a Date Range
  List<UsersByRangeDatesList> findUsersByRegistrationDateBetween(String startDate, String endDate);

  // find by name
  List<IUsersByName> findAllByName(String name);

  // save
  void save(Users user);

  // update
  void update(UpdateUser user);

  // delete
  void deleteById(Long id);
}
```
