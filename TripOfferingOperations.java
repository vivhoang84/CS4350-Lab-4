
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TripOfferingOperations {

    public static void deleteTripOffering(Connection conn, Scanner scan) throws SQLException {
        String query = "DELETE FROM TripOffering WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        System.out.println("Enter trip information: ");
        System.out.print("\tTrip number: ");
        int num = scan.nextInt();
        scan.nextLine();
        System.out.print("\tTrip date (format: YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("\tTrip scheduled start time: ");
        String startTime = scan.nextLine();

        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, num);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            if (stmt.executeUpdate() > 0){
                System.out.println("Trip offering was successfully deleted!");
            }
            else {
                System.out.println("Trip offering with those deatils not found");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addTripOffering(Connection conn, Scanner scan) throws SQLException {
        String query = "INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println("Enter the following information");
        System.out.print("\tTrip number: ");
        int num = scan.nextInt();
        scan.nextLine();
        System.out.print("\tTrip date (format: YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("\tScheduled start time: ");
        String  scheduledStartTime = scan.nextLine();
        System.out.print("\tScheduled Arrival Time: ");
        String  scheduledArrivalTime = scan.nextLine();
        System.out.print("\tDriver's name: ");
        String  driverName = scan.nextLine();
        System.out.print("\tBus ID: ");
        int  busID = scan.nextInt();
        scan.nextLine();


        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, num);
            stmt.setString(2, date);
            stmt.setString(3, scheduledStartTime);
            stmt.setString(4, scheduledArrivalTime);
            stmt.setString(5, driverName);
            stmt.setInt(6, busID);

            if (stmt.executeUpdate() > 0){
                System.out.println("Trip offering was successfully added!");
            }
            else {
                System.out.println("Trip offering was not added");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeDriver(Connection conn, Scanner scan) throws SQLException {
        String query = "UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        System.out.println("Enter trip information:");
        System.out.print("\tTrip Number: ");
        int tripNumber = scan.nextInt();
        scan.nextLine();
        System.out.print("\tDate (YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("\tScheduled Start Time: ");
        String startTime = scan.nextLine();
        System.out.print("\tNew Driver Name: ");
        String driverName = scan.nextLine();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, driverName);
            stmt.setInt(2, tripNumber);
            stmt.setString(3, date);
            stmt.setString(4, startTime);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Driver updated successfully.");
            } else {
                System.out.println("No matching trip offering found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeBus(Connection conn, Scanner scan) throws SQLException {
        String query = "UPDATE TripOffering SET BusID = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        System.out.println("Enter the following information:");
        System.out.print("\tTrip Number: ");
        int tripNumber = scan.nextInt();
        scan.nextLine();
        System.out.print("\tDate (YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("\tScheduled Start Time: ");
        String startTime = scan.nextLine();
        System.out.print("\tNew Bus ID: ");
        int busID = scan.nextInt();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, busID);
            stmt.setInt(2, tripNumber);
            stmt.setString(3, date);
            stmt.setString(4, startTime);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bus updated successfully.");
            } else {
                System.out.println("No matching trip offering found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addDriver(Connection conn, Scanner scan) throws SQLException {
        System.out.print("Enter the trip number: ");

    }

    public static void addBus(Connection conn, Scanner scan) throws SQLException {
        String query = "INSERT INTO Bus (BusID, Model, Year) VALUES (?, ?, ?)";

        System.out.println("Enter bus information: ");
        System.out.print("\tID: ");
        int busID = scan.nextInt();
        scan.nextLine();
        System.out.print("\tModel: ");
        String model = scan.nextLine();
        System.out.print("\tYear: ");
        int year = scan.nextInt();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, busID);
            stmt.setString(2, model);
            stmt.setInt(3, year);

            if (stmt.executeUpdate() > 0) {
                System.out.println("Bus added successfully!");
            } else {
                System.out.println("Failed to add the bus.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBus(Connection conn, Scanner scan) throws SQLException {
        String query = "DELETE FROM Bus WHERE BusID = ?";

        System.out.print("Enter Bus ID to delete: ");
        int busID = scan.nextInt();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, busID);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bus with ID " + busID + " has been successfully deleted.");
            } else {
                System.out.println("No bus found with ID " + busID + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertActualData(Connection conn, Scanner scan) throws SQLException {
        String query = "INSERT INTO ActualTripStopInfo (TripNumber, Date, ScheduledStartTime, StopLocation, ActualArrivalTime, ActualDepartureTime, NumberOfPassengerIn, NumberOfPassengerOut) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        System.out.print("Enter Trip Number: ");
        int tripNumber = scan.nextInt();
        scan.nextLine();
    
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scan.nextLine();
    
        System.out.print("Enter Scheduled Start Time (HH:MM:SS): ");
        String scheduledStartTime = scan.nextLine();
    
        System.out.print("Enter Stop Location: ");
        String stopLocation = scan.nextLine();
    
        System.out.print("Enter Actual Arrival Time (HH:MM:SS): ");
        String actualArrivalTime = scan.nextLine();
    
        System.out.print("Enter Actual Departure Time (HH:MM:SS): ");
        String actualDepartureTime = scan.nextLine();
    
        System.out.print("Enter Number Of Passenger In: ");
        String NumberOfPassengerIn = scan.nextLine();

        System.out.print("Enter Number Of Passenger Out: ");
        String NumberOfPassengerOut = scan.nextLine();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tripNumber);
            stmt.setString(2, date);
            stmt.setString(3, scheduledStartTime);
            stmt.setString(4, stopLocation);
            stmt.setString(5, actualArrivalTime);
            stmt.setString(6, actualDepartureTime);
            stmt.setString(7, NumberOfPassengerIn);
            stmt.setString(8, NumberOfPassengerOut);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Actual data recorded successfully.");
            } else {
                System.out.println("Failed to record actual data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
