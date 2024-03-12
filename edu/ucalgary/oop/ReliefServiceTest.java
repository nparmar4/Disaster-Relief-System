
package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReliefServiceTest {
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String validDate = "2024-03-12";
    private String invalidDate = "2027/01/10";
    private String expectedInfoProvided = "Looking for friend";
    private String expectedLogDetails = "Inquirer: Neha, Missing Person: John Doe, Date of Inquiry: 2024-03-12, Info Provided: Looking for friend, Last Known Location: Office"; 

    @Before
    public void setUp() {
        inquirer = new Inquirer("Neha", "Parmar", "1234567890", "Looking for friend.");
        missingPerson = new DisasterVictim("John Doe", "2024-03-10");
        lastKnownLocation = new Location("Office", "2500 University Dr NW");
        reliefService = new ReliefService(inquirer, missingPerson, validDate, expectedInfoProvided, lastKnownLocation);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    @Test
    public void testGetInquirer() {
        assertEquals("Inquirer should match.", inquirer, reliefService.getInquirer());
    }

    @Test
    public void testGetMissingPerson() {
        assertEquals("Missing person should match.", missingPerson, reliefService.getMissingPerson());
    }

    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Date of inquiry should match.", validDate, reliefService.getDateOfInquiry());
    }

    @Test
    public void testGetInfoProvided() {
        assertEquals("Info provided should match.", expectedInfoProvided, reliefService.getInfoProvided());
    }

    @Test
    public void testGetLastKnownLocation() {
        assertEquals("Last known location should match.", lastKnownLocation, reliefService.getLastKnownLocation());
    }

    @Test
    public void testSetDateOfInquiryWithValidDate() {
        reliefService.setDateOfInquiry(validDate);
        assertEquals("Setting a valid date should update DateOfInquiry", validDate, reliefService.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryWithInvalidDate() {
        reliefService.setDateOfInquiry(invalidDate); 
    }

    @Test
    public void testGetLogDetails() {
        assertEquals("Log details should match.", expectedLogDetails, reliefService.getLogDetails());
    }
}
