package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InquirerInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Inquirer> inquirers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Inquiry Management System");

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ensf380project", "oop", "ucalgary");
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Log a new inquiry");
                System.out.println("2. Search for an inquiry");
                System.out.println("3. Exit");

                System.out.print("Enter your choice: ");
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
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    static boolean logNewInquiry(Connection connection, String firstName, String lastName, String phoneNumber, String details) throws SQLException {
        // Check if the inquirer already exists in the database
        int foundId = -1;
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
    
        // If the inquirer doesn't exist, add them to the database
        if (foundId == -1) {
            String insertQuery = "INSERT INTO INQUIRER (firstname, lastname, phonenumber) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, firstName);
                insertStatement.setString(2, lastName);
                insertStatement.setString(3, phoneNumber);
                int rowsAffected = insertStatement.executeUpdate();
    
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        foundId = generatedKeys.getInt(1);
                    }
                } else {
                    return false; // Failed to insert the new inquirer
                }
            }
        }
    
        // Log the inquiry in the INQUIRY_LOG table
        String logQuery = "INSERT INTO INQUIRY_LOG (inquirer, calldate, details) VALUES (?, current_date, ?)";
        try (PreparedStatement logStatement = connection.prepareStatement(logQuery)) {
            logStatement.setInt(1, foundId);
            logStatement.setString(2, details);
            logStatement.executeUpdate();
            return true;
        }
    }
    
    static void searchForInquirer(Connection connection, String searchTerm) throws SQLException {
        String searchQuery = "SELECT INQUIRER.id, firstname, lastname, phonenumber, callDate, details FROM INQUIRER JOIN INQUIRY_LOG ON INQUIRER.id = INQUIRY_LOG.inquirer WHERE LOWER(firstname) LIKE ? OR LOWER(lastname) LIKE ?";
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
                    String callDate = resultSet.getString("callDate");
                    String details = resultSet.getString("details");
    
                    System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + ", Phone Number: " + phoneNumber + ", Call Date: " + callDate + ", Details: " + details);
                }
            }
        }
    }
} 
