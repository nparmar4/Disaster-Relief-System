package edu.ucalgary.oop;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Prompt the user to choose which GUI to open
        int choice = JOptionPane.showOptionDialog(null, "Which GUI do you want to open?",
                "Choose GUI", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Disaster Victim GUI", "Relief Service GUI"}, null);

        if (choice == JOptionPane.YES_OPTION) {
            // Open DisasterVictimGUI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new DisasterVictimGUI().setVisible(true);
                }
            });
        } else {
            // Open ReliefServiceGUI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ReliefServiceGUI().setVisible(true);
                }
            });
        }
    }
}


