package edu.ucalgary.oop;


import org.junit.*;
import static org.junit.Assert.*;


public class InquirerTest{


    private String expectedFirstName = "Neha";
    private String expectedLastName = "Parmar";
    private String expectedPhoneNumber = "+1-123-123-1234";
    private String expectedMessage = "Looking for my mom";
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);


    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }


    @Test
    public void testGetFirstName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        assertEquals(expectedFirstName, inquirer.getFirstName());
    }


    @Test
    public void testGetLastName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        assertEquals(expectedLastName, inquirer.getLastName());
    }


    @Test
    public void testSetFirstName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        inquirer.setFirstName("John");
        assertEquals("John", inquirer.getFirstName());
    }


    @Test
    public void testSetLastName() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        inquirer.setLastName("Doe");
        assertEquals("Doe", inquirer.getLastName());
    }


    @Test
    public void testGetServicesPhoneNum() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        assertEquals(expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }


    @Test
    public void testGetInfo() {
        Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        assertEquals(expectedMessage, inquirer.getInfo());
    }


}


