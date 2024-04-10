package edu.ucalgary.oop;


import org.junit.*;
import static org.junit.Assert.*;


public class InquirerTest{


    private String expectedFirstName = "Neha";
    private String expectedLastName = "Parmar";
    private String expectedPhoneNumber = "+1-123-123-1234";
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);


    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }


    @Test
    public void testGetFirstName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
        assertEquals(expectedFirstName, inquirer.getFirstName());
    }


    @Test
    public void testGetLastName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
        assertEquals(expectedLastName, inquirer.getLastName());
    }


    @Test
    public void testSetFirstName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
        inquirer.setFirstName("John");
        assertEquals("John", inquirer.getFirstName());
    }


    @Test
    public void testSetLastName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
        inquirer.setLastName("Doe");
        assertEquals("Doe", inquirer.getLastName());
    }


    @Test
    public void testGetServicesPhoneNum() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
        assertEquals(expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }


}

