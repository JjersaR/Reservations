-- User password JjersaR5649
INSERT INTO Reservation.Users (account_no_expired, account_no_locked, credential_no_expired, email, is_enabled, maternalLastName, name, password, paternalLastName, phone, registrationDate)
VALUES (1, 1, 1, 'rikisj5649@gmail.com', 1, 'Jeronimo', 'Ricardo', '$2a$10$1wo8J65mXaNzlfbWYvcl5OOc33eYC6mj8Zk8/Y438L79XToOTqDQC', 'Sanchez', '5511779498', '2024-06-24'),
(1, 1, 1, 'paola@gmail.com', 1, 'Guzman', 'Paola', '$2a$10$1wo8J65mXaNzlfbWYvcl5OOc33eYC6mj8Zk8/Y438L79XToOTqDQC', 'Macias', '5511779499', '2024-06-24');

INSERT INTO Reservation.Roles (name)
VALUES('ADMIN'), ('CLIENT'), ('PROVIDER'), ('RECEPTION');

INSERT INTO Reservation.Permits (name)
VALUES('CREATE'), ('READ'), ('UPDATE'), ('DELETE'), ('BUY');

INSERT INTO Reservation.roles_permits (Rol_ID, Permit_ID)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), 
(2, 2), (2, 5), (2, 4), 
(3, 1), (3, 2), (3, 3), (3, 4), 
(4, 2), (4, 3), (4, 1);

INSERT INTO Reservation.users_roles (User_ID, Rol_ID)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), 
(2, 2), (2, 4), (2, 3);

INSERT INTO Reservation.Services (available, category, description, duration, location, name, price)
VALUES ('RESERVED', 'Entertainment', 'Perfect place to vacation and away from the city', 7, 'Puebla', 'Cabins', 1500), 
('AVAILABLE', 'Wellness', 'Relaxing spa treatments and wellness programs', 3, 'Cancún', 'Spa Resort', 3000), 
('MAINTENANCE', 'Adventure', 'High-adrenaline activities in nature', 1, 'Hidalgo', 'Adventure Park', 1200), 
('AVAILABLE', 'Cultural', 'Explore the rich history and culture of the region', 2, 'Oaxaca', 'Historical Tour', 1000), 
('AVAILABLE', 'Luxury', 'Exclusive high-end amenities and services', 5, 'Los Cabos', 'Luxury Hotel', 5000), 
('RESERVED', 'Nature', 'Guided tours through beautiful natural landscapes', 1, 'Chiapas', 'Nature Reserve', 800), 
('MAINTENANCE', 'Relaxation', 'Peaceful retreats for relaxation and meditation', 3, 'Yucatán', 'Retreat Center', 1500), 
('AVAILABLE', 'Family', 'Fun-filled activities for the whole family', 2, 'Veracruz', 'Family Resort', 1800), 
('NOT_AVAILABLE', 'Music', 'Live music performances and festivals', 1, 'Monterrey', 'Music Venue', 900), 
('RESERVED', 'Educational', 'Informative and engaging educational tours', 1, 'Querétaro', 'Science Museum', 600), 
('AVAILABLE', 'Art', 'Workshops and exhibitions by local artists', 2, 'San Miguel de Allende', 'Art Gallery', 1200), 
('MAINTENANCE', 'Fitness', 'Comprehensive fitness programs and classes', 2, 'Tulum', 'Fitness Center', 1400), 
('AVAILABLE', 'Luxury', 'High-end accommodations with exclusive services', 5, 'Acapulco', 'Boutique Hotel', 4000);

INSERT INTO Reservation.Reservations (endDate, notes, startDate, status, service_id, user_id, number_people)
VALUES ('2024-06-15', 'Schedule 2 single bedrooms', '2024-06-01', 'IN_PROGRESS', 2, 1, 2), 
('2024-07-25', '', '2024-07-15', 'COMPLETED', 5, 2, 5), 
('2024-06-15', '', '2024-06-04', 'CANCELLED', 8, 1, 1);
