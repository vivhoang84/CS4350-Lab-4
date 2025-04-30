import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Display {

    public static void displaySchedule (Connection conn, Scanner scan) throws SQLException {
        String query = "SELECT T.TripNumber, O.Date, O.ScheduledStartTime, O.ScheduledArrivalTime, O.DriverName, O.BusID " +
                "FROM TripOffering O JOIN Trip T ON O.TripNumber = T.TripNumber " +
                "WHERE T.StartLocationName = ? AND T.DestinationName = ? AND O.Date = ?";

        System.out.print("Enter start location: ");
        scan.nextLine();
        String start = scan.nextLine();
        System.out.print("Enter destination: ");
        String destination = scan.nextLine();
        System.out.print("Enter trip date (YYYY-MM-DD): ");
        String date = scan.nextLine();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, start);
            stmt.setString(2, destination);
            stmt.setString(3, date);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf(
                        "Trip Number: %d\nDate: %s\nStart time: %s\nArrival Time: %s\nDriver: %s\nBusID: %d%n",
                        rs.getInt("TripNumber"),
                        rs.getString("Date"),
                        rs.getString("ScheduledStartTime"),
                        rs.getString("ScheduledArrivalTime"),
                        rs.getString("DriverName"),
                        rs.getInt("BusID")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void displayTripStops(Connection conn, Scanner scan) throws SQLException {
        String query = "SELECT TripNumber, StopNumber, SequenceNumber, DrivingTime FROM TripStopInfo WHERE TripNumber = ? ORDER BY SequenceNumber";
    
        System.out.print("Enter trip number: ");
        int tripNumber = scan.nextInt();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tripNumber);
    
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                System.out.println("Stops for Trip " + tripNumber + ":");
                while (rs.next()) {
                    found = true;
                    System.out.printf("Stop #%d\nStopNumber = %d\nDrivingTime = %s%n",
                        rs.getInt("SequenceNumber"),
                        rs.getInt("StopNumber"),
                        rs.getString("DrivingTime")
                    );
                }
                if (!found) {
                    System.out.println("No stops found for this trip.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayWeeklySchedule(Connection conn, Scanner scan) throws SQLException {
        String query = "SELECT TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, BusID " +
                        "FROM TripOffering " +
                        "WHERE DriverName = ? AND Date BETWEEN ? AND DATE_ADD(?, INTERVAL 6 DAY) " +
                        "ORDER BY Date, ScheduledStartTime";

    
        System.out.print("Enter driver's name: ");
        scan.nextLine();
        String driverName = scan.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String startDate = scan.nextLine();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, driverName);
            stmt.setString(2, startDate);
            stmt.setString(3, startDate);
    
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                System.out.println("\nWeekly Schedule for " + driverName + ":");
                while (rs.next()) {
                    found = true;
                    System.out.printf("Trip %d on %s:\n\tStart %s → Arrival %s\n\tBus: %d%n",
                        rs.getInt("TripNumber"),
                        rs.getString("Date"),
                        rs.getString("ScheduledStartTime"),
                        rs.getString("ScheduledArrivalTime"),
                        rs.getInt("BusID")
                    );
                }
                if (!found) {
                    System.out.println("No trips scheduled for this driver during the given week.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
