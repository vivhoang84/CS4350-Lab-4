
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BusDriver {

    public static void changeDriver (Connection conn, Scanner scan) throws SQLException {
        String query = "UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?;";

        System.out.print("Trip number: ");
        int tripNum = scan.nextInt();
        scan.nextLine();
        System.out.print("Trip date (YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("Scheduled start time (HH:MM:SS): ");
        String startTime = scan.nextLine();
        System.out.print("New driver name: ");
        String newDriver = scan.nextLine();


        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, newDriver);
            stmt.setInt(2, tripNum);
            stmt.setString(3, date);
            stmt.setString(3, startTime);

            if (stmt.executeUpdate() > 0){
                System.out.println("Driver was successfully changed!");
            }
            else {
                System.out.println("Driver was not changed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addDriver(Connection conn, Scanner scan) throws SQLException {
        String query = "INSERT INTO Driver (DriverName, DriverTelephoneNumber) VALUES (?, ?);";

        System.out.println("Enter the following information");
        System.out.print("\tDriver's Name: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("\tDriver's telephone number (format: ###-###-####): ");
        String teleNum = scan.nextLine();


        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            stmt.setString(2, teleNum);

            if (stmt.executeUpdate() > 0){
                System.out.println("Driver was successfully added!");
            }
            else {
                System.out.println("Driver was not added");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
