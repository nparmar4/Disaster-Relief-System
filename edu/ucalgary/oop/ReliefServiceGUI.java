package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReliefServiceGUI extends JFrame {
    private JTextField searchField;
    private JTextArea displayArea;
    private JComboBox<String> genderComboBox;
    private ArrayList<InteractionLog> interactionHistory;

    public ReliefServiceGUI() {
        interactionHistory = new ArrayList<>();

        setTitle("Relief Service Application");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel(new FlowLayout());
        JLabel loginLabel = new JLabel("Login as:");
        JRadioButton centralWorkerRadioButton = new JRadioButton("Central Worker");
        JRadioButton locationWorkerRadioButton = new JRadioButton("Location Worker");
        ButtonGroup loginGroup = new ButtonGroup();
        loginGroup.add(centralWorkerRadioButton);
        loginGroup.add(locationWorkerRadioButton);
        loginPanel.add(loginLabel);
        loginPanel.add(centralWorkerRadioButton);
        loginPanel.add(locationWorkerRadioButton);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Search by name:");
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByName(searchField.getText());
            }
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel interactionPanel = new JPanel(new FlowLayout());
        JLabel interactionLabel = new JLabel("Interaction Log:");
        displayArea = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        interactionPanel.add(interactionLabel);
        interactionPanel.add(scrollPane);

        JPanel logQueryPanel = new JPanel(new FlowLayout());
        JLabel genderLabel = new JLabel("Select Gender:");
        genderComboBox = new JComboBox<>();
        populateGenderComboBox(); // Populate gender options from file
        JButton logQueryButton = new JButton("Log Query");
        logQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logQuery();
            }
        });
        logQueryPanel.add(genderLabel);
        logQueryPanel.add(genderComboBox);
        logQueryPanel.add(logQueryButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(loginPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(interactionPanel);
        mainPanel.add(logQueryPanel);

        Container contentPane = getContentPane();
        contentPane.add(mainPanel);
    }

    private void searchByName(String name) {
        // Implement search logic here
        // This is just a placeholder method
        displayArea.append("Searching for: " + name + "\n");
    }

    private void logQuery() {
        // Implement log query logic here
        // This is just a placeholder method
        String selectedGender = (String) genderComboBox.getSelectedItem();
        InteractionLog log = new InteractionLog(selectedGender);
        interactionHistory.add(log);
        displayArea.append("Query logged: " + selectedGender + "\n");
    }

    private void populateGenderComboBox() {
        try (BufferedReader reader = new BufferedReader(new FileReader("GenderOptions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderComboBox.addItem(line.trim());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading gender options file", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReliefServiceGUI().setVisible(true);
            }
        });
    }
}

class InteractionLog {
    private String logDetails;

    public InteractionLog(String logDetails) {
        this.logDetails = logDetails;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }
}