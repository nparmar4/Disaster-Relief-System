package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisasterVictimTest {

    private DisasterVictim victim;

    @Before
    public void setUp() {
        victim = new DisasterVictim("Michael", "2024-03-14", 28);
    }

    @Test
    public void testConstructorWithAge() {
        assertEquals("Michael", victim.getFirstName());
        assertEquals("2024-03-14", victim.getEntryDate());
        assertEquals("28", victim.getAge());
    }

    @Test
    public void testConstructorWithDateOfBirth() {
        DisasterVictim victimWithDOB = new DisasterVictim("Sarah", "2024-03-14", "1996-05-25");
        assertEquals("Sarah", victimWithDOB.getFirstName());
        assertEquals("2024-03-14", victimWithDOB.getEntryDate());
        assertEquals("1996-05-25", victimWithDOB.getDateOfBirth());
    }

    @Test
    public void testSetAndGetComments() {
        victim.setComments("Updated comments");
        assertEquals("Updated comments", victim.getComments());
    }

    @Test
    public void testSetAndGetMedicalRecords() {
        Location location = new Location("Michael", "USA");
        MedicalRecord record = new MedicalRecord(location, "Fever", "2024-03-14");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(record);
        victim.setMedicalRecords(medicalRecords);
        assertEquals(1, victim.getMedicalRecords().length);
        assertEquals("Fever", victim.getMedicalRecords()[0].getTreatmentDetails());
    }    

    @Test
    public void testSetAndGetPersonalBelongings() {
        ArrayList<Supply> personalBelongings = new ArrayList<>();
        Supply supply = new Supply("Food", 8);
        personalBelongings.add(supply);
        victim.setPersonalBelongings(personalBelongings);
        assertEquals(1, victim.getPersonalBelongings().size());
        assertEquals("Food", victim.getPersonalBelongings().get(0).getType());
        assertEquals(8, victim.getPersonalBelongings().get(0).getQuantity());
    }

    @Test
    public void testSetAndGetFamilyConnections() {
        DisasterVictim personOne = new DisasterVictim("Michael", "2024-03-14", 28);
        DisasterVictim personTwo = new DisasterVictim("Eva", "2024-03-14", 24);
        FamilyRelation relation = new FamilyRelation(personOne, "Parent", personTwo);
        ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
        familyConnections.add(relation);
        victim.setFamilyConnections(familyConnections);
        assertEquals(1, victim.getFamilyConnections().size());
        assertEquals("Parent", victim.getFamilyConnections().get(0).getRelationshipTo());
        assertEquals("Michael", victim.getFamilyConnections().get(0).getPersonOne().getFirstName());
        assertEquals("Eva", victim.getFamilyConnections().get(0).getPersonTwo().getFirstName());
    }
    
    @Test
    public void testRemovePersonalBelonging() {
        ArrayList<Supply> personalBelongings = new ArrayList<>();
        Supply supply = new Supply("Medicine", 6);
        personalBelongings.add(supply);
        victim.setPersonalBelongings(personalBelongings);
        victim.removePersonalBelonging(supply);
        assertEquals(0, victim.getPersonalBelongings().size());
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply supply = new Supply("Blanket", 3);
        victim.addPersonalBelonging(supply);
        assertEquals(1, victim.getPersonalBelongings().size());
        assertEquals("Blanket", victim.getPersonalBelongings().get(0).getType());
        assertEquals(3, victim.getPersonalBelongings().get(0).getQuantity());
    }

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim person = new DisasterVictim("Eva", "2024-03-14", 24);
        FamilyRelation relation = new FamilyRelation(person, "Parent", victim);
        victim.addFamilyConnection(relation);

        assertEquals(1, victim.getFamilyConnections().size());
        assertEquals("Parent", victim.getFamilyConnections().get(0).getRelationshipTo());
        assertEquals("Eva", victim.getFamilyConnections().get(0).getPersonOne().getFirstName());
        assertEquals("Michael", victim.getFamilyConnections().get(0).getPersonTwo().getFirstName());
    }
    

    @Test
    public void testRemoveFamilyConnection() {
        FamilyRelation relation = new FamilyRelation(victim, "Sibling", new DisasterVictim("Eva", "2024-03-14", 24));
        victim.addFamilyConnection(relation);
        victim.removeFamilyConnection(relation);
        assertEquals(0, victim.getFamilyConnections().size());
    }

    
    @Test
    public void testAddMedicalRecord() {
        Location location = new Location("Michael", "USA");
        MedicalRecord record = new MedicalRecord(location, "Fever", "2024-03-14");
        victim.addMedicalRecord(record);
        assertEquals(1, victim.getMedicalRecords().length);
        assertEquals("Fever", victim.getMedicalRecords()[0].getTreatmentDetails());
        assertEquals("2024-03-14", victim.getMedicalRecords()[0].getDateOfTreatment());
    }


    @Test
    public void testSetAndGetSupply() {
        victim.setSupply("Medicine");
        assertEquals("Medicine", victim.getSupply());
    }

    @Test
    public void testSetAndGetAge() {
        victim.setAge(33);
        assertEquals("33", victim.getAge());
    }

    @Test
    public void testSetAndGetDateOfBirth() {
        victim.setDateOfBirth("1986-12-05");
        assertEquals("1986-12-05", victim.getDateOfBirth());
    }

    @Test
    public void testSetAndGetFirstName() {
        victim.setFirstName("Jack");
        assertEquals("Jack", victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        victim.setLastName("Smith");
        assertEquals("Smith", victim.getLastName());
    }

    @Test
    public void testDietRestriction() {
        victim.dietRestriction(DisasterVictim.Diet.AVML);
    }

    @Test
    public void testDecreaseSupplyQuantity() {
        ArrayList<Supply> supplies = new ArrayList<>();
        supplies.add(new Supply("Medicine", 8));
        victim.setPersonalBelongings(supplies);

        Supply medicineSupply = new Supply("Medicine", 3);
        victim.decreaseSupplyQuantity(medicineSupply);

        assertEquals(5, victim.getPersonalBelongings().get(0).getQuantity());
    }
    @Test
    public void testSetAndGetGender() {
        try (BufferedReader br = new BufferedReader(new FileReader("GenderOptions.txt"))) {
            String line;
            String[] genderOptions = new String[7]; 
            int index = 0;
            while ((line = br.readLine()) != null && index < genderOptions.length) {
                genderOptions[index] = line.trim().toLowerCase();
                index++;
            }
            victim.setGender("boy"); 
            assertEquals("boy", victim.getGender());

            victim.setGender("invalid_gender"); 
            assertFalse(validateGender(genderOptions, victim.getGender()));
        } catch (IOException e) {
            fail("Error reading gender options file: " + e.getMessage());
        }
    }

    // Helper method to validate if the gender is one of the options in the file
    private boolean validateGender(String[] genderOptions, String gender) {
        for (String option : genderOptions) {
            if (option.equals(gender)) {
                return true;
            }
        }
        return false;
    }
    
}
