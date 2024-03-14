package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DisasterVictimEntryTest {
    private DisasterVictim victim;
    private String relationType;
    private int relativeIdentifier;
    private String incidentDetail;
    private String treatmentDate;
    private String locationTitle;
    private String addressDetails;

    @Before
    public void setUp() {
        victim = new DisasterVictim("Eleanor", "2024-01-18");
    }

    @Test
    public void testEnterPersonalInfo() {
        DisasterVictim newVictim = new DisasterVictim("Alice", "2024-03-13");
        assertEquals("getFirstName() should return Alice", "Alice", newVictim.getFirstName());
        assertEquals("getEntryDate() should return 2024-03-13", "2024-03-13", newVictim.getEntryDate());
    }

    @Test
    public void testAddFamilyRelation() {
        int victimIdentifier = 3;
        int relativeIdentifier = 7;
        String relationType = "Brother";
        victim.addFamilyRelation(victimIdentifier, relationType, relativeIdentifier);
        assertTrue("addFamilyRelation() should have successfully established this relation", victim.isRelated(victimIdentifier, relationType, relativeIdentifier));
    }

    @Test
    public void testAddMedicalRecord() {
        int victimIdentifier = 22;
        String incidentDetail = "Fractured Arm";
        String treatmentDate = "2023-02-05";
        victim.addMedicalRecord(victimIdentifier, incidentDetail, treatmentDate);
        assertTrue("addMedicalRecord() should have added the medical record", victim.hasMedicalRecord(victimIdentifier, incidentDetail, treatmentDate));
    }

    @Test
    public void testSelectLocation() {
        String locationTitle = "The Evacuation Center";
        String addressDetails = "123 Relief Street";
        victim.selectLocation(locationTitle, addressDetails);
        assertEquals("locationAddress() should return the address", "123 Relief Street", victim.locati
