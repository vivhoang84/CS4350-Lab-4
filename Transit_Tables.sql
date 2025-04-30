INSERT INTO Trip (TripNumber, StartLocationName, DestinationName)
VALUES 
    (101, 'City A', 'City B'),
    (102, 'City C', 'City D');

INSERT INTO Bus (BusID, Model, Year)
VALUES
    (1, 'Bus 1', 2025),
    (2, 'Bus 2', 2024);

INSERT INTO Driver (DriverName, DriverTelephoneNumber)
VALUES
    ('Vivian', '123-456-7890'),
    ('Malia', '098-765-4321');


INSERT INTO Stop (StopNumber, StopAddress)
VALUES
    (1, '123 A St'),
    (2, '456 B St'),
    (3, '789 C St'),
    (4, '101 D ST');


INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID)
VALUES
    (101, '2025-05-01', '08:00:00', '10:00:00', 'Vivian', 1),
    (102, '2025-05-02', '09:00:00', '11:30:00', 'Malia', 2);


INSERT INTO ActualTripStopInfo (
    TripNumber, Date, ScheduledStartTime, StopNumber, 
    ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, 
    NumberOfPassengerIn, NumberOfPassengerOut
)
VALUES
    (101, '2025-05-01', '08:00:00', 1, '08:15:00', '08:05:00', '08:20:00', 5, 0),
    (101, '2025-05-01', '08:00:00', 2, '08:30:00', '08:25:00', '08:35:00', 3, 1),
    (102, '2025-05-02', '09:00:00', 3, '09:20:00', '09:05:00', '09:25:00', 7, 2),
    (102, '2025-05-02', '09:00:00', 4, '09:40:00', '09:30:00', '09:45:00', 4, 3);


INSERT INTO TripStopInfo (TripNumber, StopNumber, SequenceNumber, DrivingTime)
VALUES
    (101, 1, 1, 15),
    (101, 2, 2, 15),
    (102, 3, 1, 20),
    (102, 4, 2, 20);