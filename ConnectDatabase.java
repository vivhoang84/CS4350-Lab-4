
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class ConnectDatabase {

    private static String URL = "jdbc:mysql://localhost:3306/lab4?useSSL=false&allowPublicKeyRetrieval=true";
    private static String username = "root";
    private static String password = "Capaciic5!5!";

    
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, username, password)){
            Scanner scan = new Scanner(System.in);

        int num = 0;

        while (true) {
            // print menu to user
            System.out.println("\nPomona Transit System");
            System.out.println("\t(1) Display schedule for all trips given the location, destination, and date");
            System.out.println("\t(2) Edit Schedule");
            System.out.println("\t(3) Display stops for a given trip");
            System.out.println("\t(4) Display weekly schedule for a given driver and date");
            System.out.println("\t(5) Add a driver");
            System.out.println("\t(6) Add a bus");
            System.out.println("\t(7) Delete a bus");
            System.out.println("\t(8) Record (insert) actual data of a given trip offering specified by its key");
            System.out.println("\t(9) Exit");

            // check to see if input is valid
            while (true) {

                System.out.print("Enter a number: ");
                num = scan.nextInt();

                if (num < 1 || num > 9) {
                    System.out.println("Invlaid input!");
                } else
                    break;
            }

            // executes action based on user inpout
            switch (num) {
                case 1:
                    Display.displaySchedule(conn, scan);
                    break;
                case 2:
                    System.out.println("What would you like to edit?");
                    System.out.println("\t(1) Delete a trip offering");
                    System.out.println("\t(2) Add a set of trip offerings");
                    System.out.println("\t(3) Change the driver for a given trip offering");
                    System.out.println("\t(4) Change the bus for a given trip offering");

                    while (true) {

                        System.out.print("Enter a number: ");
                        num = scan.nextInt();
                        scan.nextLine();
        
                        if (num < 1 || num > 9) {
                            System.out.println("Invlaid input!");
                        } else
                            break;
                    }

                    switch (num) {
                        case 1:
                            TripOfferingOperations.deleteTripOffering(conn, scan);
                            break;
                        case 2:
                            TripOfferingOperations.addTripOffering(conn, scan);
                            break;
                        case 3:
                            BusDriver.changeDriver(conn, scan);
                            break;
                        case 4:
                            TripOfferingOperations.changeBus(conn, scan);
                            break;
                    }
                    break;

                case 3:
                    Display.displayTripStops(conn, scan);
                    break;
                case 4:
                    Display.displayWeeklySchedule(conn, scan);
                    break;
                case 5:
                    BusDriver.addDriver(conn, scan);
                    break;
                case 6:
                    TripOfferingOperations.addBus(conn, scan);
                    break;
                case 7:
                    TripOfferingOperations.deleteBus(conn, scan);
                    break;
                case 8:
                    TripOfferingOperations.insertActualData(conn, scan);
                    break;
                case 9:
                    System.exit(0);
            }
        }
        }
        catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }

}
