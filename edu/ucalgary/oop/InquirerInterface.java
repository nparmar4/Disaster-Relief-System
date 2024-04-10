package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class acts as an interface for managing inquiries within a database system.
 * It facilitates the logging of new inquiries, searching for existing ones, and exiting the system.
 * The interface connects to a PostgreSQL database using JDBC.
 */
public class InquirerInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Inquirer> inquirers = new ArrayList<>();
    
    /**
     * The main method to execute the Inquiry Management System interface.
     *
     * @param args Command-line arguments (not utilized in this application)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Inquiry Management System");

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ensf380project", "oop", "ucalgary");
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Log a new inquiry");
                System.out.println("2. Search for an inquiry");
                System.out.println("3. Exit");

                System.out.print("Your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("\nEnter details for the new inquiry:");

                        System.out.print("First Name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Last Name: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Phone Number: ");
                        String phoneNumber = scanner.nextLine();

                        System.out.print("Details: ");
                        String details = scanner.nextLine();

                        logNewInquiry(connection, firstName, lastName, phoneNumber, details);
                        break;
                    case 2:
                        System.out.println("\nEnter a part of the name to search for:");

                        System.out.print("Name: ");
                        String searchTerm = scanner.nextLine();
                        searchForInquirer(connection, searchTerm);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid selection. Please choose a valid option.");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Logs a new inquiry into the database.
     *
     * @param connection The database connection
     * @param firstName   The first name of the inquirer
     * @param lastName    The last name of the inquirer
     * @param phoneNumber The phone number of the inquirer
     * @param details     The details of the inquiry
     * @return True if the inquiry was successfully logged, false otherwise
     * @throws SQLException if a database access error occurs
     */
    static boolean logNewInquiry(Connection connection, String firstName, String lastName, String phoneNumber, String details) throws SQLException {
        boolean found = false;
        for (Inquirer inquirer: inquirers) {
            if (inquirer.getFirstName().equals(firstName) && inquirer.getLastName().equals(lastName) && inquirer.getServicesPhoneNum().equals(phoneNumber)) {
                found = true;
            }
        }

        int foundId = -1;
        if (!found) {
            Inquirer inquirer = new Inquirer(firstName, lastName, phoneNumber);
            inquirers.add(inquirer);
        
            String insertQuery = "INSERT INTO INQUIRER (firstname, lastname, phonenumber) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, phoneNumber);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        foundId = generatedKeys.getInt(1);
                    }
                } else {
                    System.out.println("Failed to log the inquiry.");
                }
            }
        } else {
            String searchQuery = "SELECT id FROM INQUIRER WHERE firstname = ? AND lastname = ? AND phonenumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, phoneNumber);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    foundId = resultSet.getInt("id");
                }
            }
        }

        if (foundId != -1) {
            String logQuery = "INSERT INTO INQUIRY_LOG (inquirer, calldate, details) VALUES (?, current_date, ?)";
            try (PreparedStatement logStatement = connection.prepareStatement(logQuery, Statement.RETURN_GENERATED_KEYS)) {
                logStatement.setInt(1, foundId);
                logStatement.setString(2, details);
                logStatement.executeUpdate();

                System.out.println("Inquiry logged successfully.");
                return logStatement.getGeneratedKeys().next();
            }
        } else {
            System.out.println("ID not found!");
        }

        return false;
    }

    /**
     * Searches for an inquirer by name in the database.
     *
     * @param connection The database connection
     * @param searchTerm The name (or part of the name) to search for
     * @return A string representation of the search results
     * @throws SQLException if a database access error occurs
     */
    static String searchForInquirer(Connection connection, String searchTerm) throws SQLException {
        String searchQuery = "SELECT * FROM INQUIRER WHERE LOWER(firstname) LIKE ? OR LOWER(lastname) LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, "%" + searchTerm.toLowerCase() + "%");
            preparedStatement.setString(2, "%" + searchTerm.toLowerCase() + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("\nSearch Results:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String phoneNumber = resultSet.getString("phonenumber");
                    System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + ", Phone Number: " + phoneNumber);
                    return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Phone Number: " + phoneNumber;
                }
            }
        }

        return "";
    }

    /**
     * Setter method to set the list of inquirers.
     *
     * @param inquirers The list of inquirers to set
     */
    public static void setInquirers(List<Inquirer> inquirers) {
        InquirerInterface.inquirers = inquirers;
    }
}
