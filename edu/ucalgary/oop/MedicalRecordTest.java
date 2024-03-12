package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {

    Location expectedLocation = new Location("Calgary Shelter", "15 Ave");
    private String expectedTreatmentDetails = "Concussion treated";
    private String expectedDateOfTreatment = "2024-02-11";
    private String validDateOfTreatment = "2024-03-06";
    private String inValidDateOfTreatment = "2024/03/06";
    MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, expectedTreatmentDetails, expectedDateOfTreatment);


    @Test
    public void testObjectCreation() {
        assertNotNull(medicalRecord);
    }	
	
    @Test
    public void testGetLocation() {
    assertEquals("getLocation should return correct Location.", expectedLocation, medicalRecord.getLocation());
    }

 @Test
    public void testSetLocation() {
	Location newExpectedLocation = new Location("Edmonton Shelter", "18 Ave");
	medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update Location.", newExpectedLocation.getName(), medicalRecord.getLocation().getName());
    }

    @Test
    public void testGetTreatmentDetails() {
        assertEquals("getTreatmentDetails should return correct treatment details.", expectedTreatmentDetails, medicalRecord.getTreatmentDetails());
    }
@Test
    public void testSetTreatmentDetails() {
	String newExpectedTreatment = "No x-ray required";
	medicalRecord.setTreatmentDetails(newExpectedTreatment);
    assertEquals("setTreatmentDetails should update treatment details.", newExpectedTreatment, medicalRecord.getTreatmentDetails());
    }


    @Test
    public void testGetDateOfTreatment() {
    assertEquals("getDateOfTreatment should return the correct date of treatment", expectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }
	
	@Test
    public void testSetDateOfTreatment() {
	String newExpectedDateOfTreatment = "2024-02-05";
	medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
    assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }
	@Test
    public void testSetDateOfTreatmentWithValidFormat() {
        
        medicalRecord.setDateOfTreatment(validDateOfTreatment); 
    }

    @Test
    public void testSetDateOfBirthWithInvalidFormat() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
           medicalRecord.setDateOfTreatment(inValidDateOfTreatment); //IllegalArgumentException
        }
        catch (IllegalArgumentException e) {
           correctValue = true;
        }
        catch (Exception e) {
           failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw IllegalArgumentException with invalid date format '" + inValidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }

    @Test
    public void testSetDateOfBirthWithNotADate() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
           medicalRecord.setDateOfTreatment(expectedTreatmentDetails); // IllegalArgumentException
        }
        catch (IllegalArgumentException e) {
           correctValue = true;
        }
        catch (Exception e) {
           failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid non-date input '" + inValidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }
}

