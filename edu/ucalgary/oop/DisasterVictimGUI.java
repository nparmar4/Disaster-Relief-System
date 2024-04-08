package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DisasterVictimGUI extends JFrame {

    private JTextField firstNameField, dateOfBirthField, ageField, entryDateField, commentsField;
    private JTextField treatmentDetailsField, dateOfTreatmentField, relationshipToField;
    private JButton addVictimButton, viewVictimsButton, addMedicalRecordButton, addFamilyRelationButton;
    private JTextArea displayArea;
    private ArrayList<DisasterVictim> victims;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public DisasterVictimGUI() {
        victims = new ArrayList<>();

        setTitle("Disaster Victim Information");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2)); // Adjusted to 8 rows
        addLabelAndField(inputPanel, "First Name:", firstNameField = new JTextField());
        addLabelAndField(inputPanel, "Date of Birth:", dateOfBirthField = new JTextField());
        addLabelAndField(inputPanel, "Age:", ageField = new JTextField());
        addLabelAndField(inputPanel, "Entry Date:", entryDateField = new JTextField());
        addLabelAndField(inputPanel, "Comments:", commentsField = new JTextField());
        addLabelAndField(inputPanel, "Treatment Details:", treatmentDetailsField = new JTextField());
        addLabelAndField(inputPanel, "Date of Treatment:", dateOfTreatmentField = new JTextField());
        addLabelAndField(inputPanel, "Relationship To:", relationshipToField = new JTextField());

        addVictimButton = new JButton("Add Victim");
        addVictimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVictimToDatabase();
            }
        });

        viewVictimsButton = new JButton("View Victims");
        viewVictimsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewVictimsFromDatabase();
            }
        });

        addMedicalRecordButton = new JButton("Add Medical Record");
        // Action listener for adding medical record to the database
        addMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMedicalRecordToDatabase();
            }
        });

        addFamilyRelationButton = new JButton("Add Family Relation");
        // Action listener for adding family relation to the database
        addFamilyRelationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFamilyRelationToDatabase();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(addVictimButton);
        buttonPanel.add(viewVictimsButton);
        buttonPanel.add(addMedicalRecordButton);
        buttonPanel.add(addFamilyRelationButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(scrollPane, BorderLayout.SOUTH);
    }

    // Method to add a label and text field to the input panel
    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(textField);
    }

    // Method to add a victim to the database
    private void addVictimToDatabase() {
        // Get victim information from text fields
        String firstName = firstNameField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        int age = Integer.parseInt(ageField.getText());
        String entryDate = entryDateField.getText();
        String comments = commentsField.getText();

        // Create a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Prepare SQL statement to insert victim information
            String sql = "INSERT INTO victims (first_name, date_of_birth, age, entry_date, comments) " +
                         "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameters for the SQL statement
                statement.setString(1, firstName);
                statement.setString(2, dateOfBirth);
                statement.setInt(3, age);
                statement.setString(4, entryDate);
                statement.setString(5, comments);
                // Execute the SQL statement
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    displayArea.append("Victim added: " + firstName + "\n");
                    clearFields();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to view victims from the database
    private void viewVictimsFromDatabase() {
        displayArea.setText("");
        // Create a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Prepare SQL statement to select all victims
            String sql = "SELECT * FROM victims";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                // Iterate over the result set and display victim information
                while (resultSet.next()) {
                    displayArea.append("Name: " + resultSet.getString("first_name") + "\n");
                    displayArea.append("Date of Birth: " + resultSet.getString("date_of_birth") + "\n");
                    displayArea.append("Age: " + resultSet.getInt("age") + "\n");
                    displayArea.append("Entry Date: " + resultSet.getString("entry_date") + "\n");
                    displayArea.append("Comments: " + resultSet.getString("comments") + "\n\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to add a medical record to the database
    private void addMedicalRecordToDatabase() {
        // Get medical record information from text fields
        String treatmentDetails = treatmentDetailsField.getText();
        String dateOfTreatment = dateOfTreatmentField.getText();

        // Create a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Prepare SQL statement to insert medical record information
            String sql = "INSERT INTO medical_records (treatment_details, date_of_treatment) " +
                         "VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameters for the SQL statement
                statement.setString(1, treatmentDetails);
                statement.setString(2, dateOfTreatment);
                // Execute the SQL statement
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    displayArea.append("Medical Record added: " + treatmentDetails + "\n");
                    clearFields();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to add a family relation to the database
    private void addFamilyRelationToDatabase() {
        // Get family relation information from text fields
        String firstNameOne = firstNameField.getText();
        String relationshipTo = relationshipToField.getText();
        String firstNameTwo = firstNameField.getText();

        // Create a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Fetch victim IDs for the given names
            int victimIdOne = getVictimId(connection, firstNameOne);
            int victimIdTwo = getVictimId(connection, firstNameTwo);
            if (victimIdOne == -1 || victimIdTwo == -1) {
                JOptionPane.showMessageDialog(this, "One or both victims not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prepare SQL statement to insert family relation information
            String sql = "INSERT INTO family_relations (victim_id_one, relationship_to, victim_id_two) " +
                         "VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameters for the SQL statement
                statement.setInt(1, victimIdOne);
                statement.setString(2, relationshipTo);
                statement.setInt(3, victimIdTwo);
                // Execute the SQL statement
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    displayArea.append("Family Relation added: " + firstNameOne + " - " + firstNameTwo + "\n");
                    clearFields();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to retrieve victim ID based on first name from the database
    private int getVictimId(Connection connection, String firstName) throws SQLException {
        // Prepare SQL statement to select victim ID by first name
        String sql = "SELECT id FROM victims WHERE first_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameter for the SQL statement
            statement.setString(1, firstName);
            // Execute the SQL statement
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    return -1; // Victim not found
                }
            }
        }
    }

    // Method to clear input fields
    private void clearFields() {
        firstNameField.setText("");
        dateOfBirthField.setText("");
        ageField.setText("");
        entryDateField.setText("");
        commentsField.setText("");
        treatmentDetailsField.setText("");
        dateOfTreatmentField.setText("");
        relationshipToField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DisasterVictimGUI().setVisible(true);
            }
        });
    }
}