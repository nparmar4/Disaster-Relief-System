package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;


public class FamilyRelationTest {
   private DisasterVictim personOne;
   private DisasterVictim personTwo;
   private String relationshipTo;
   private FamilyRelation testFamilyRelationObject;
  
   @Before
   public void setup() {
       personOne = new DisasterVictim("Neha Parmar", "2024-01-10", 19);
       personTwo = new DisasterVictim("John Doe", "2024-02-20", 27);
       relationshipTo = "sibling";
       testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
   }

   @Test
   public void testObjectCreation() {
       assertNotNull(testFamilyRelationObject);
   }
  
   @Test
   public void testSetAndGetPersonOne() {
       DisasterVictim newPersonOne = new DisasterVictim("New Person One", "2024-01-01", 19); 
       testFamilyRelationObject.setPersonOne(newPersonOne); 
       assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());//assert
   }


   @Test
   public void testSetAndGetPersonTwo() {
       DisasterVictim newPersonTwo = new DisasterVictim("New Person Two", "2024-01-02", 19); 
       testFamilyRelationObject.setPersonTwo(newPersonTwo); 
       assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo()); 
   }

   @Test
   public void testSetAndGetRelationshipTo() {
       String newRelationship = "sister"; 
       testFamilyRelationObject.setRelationshipTo(newRelationship); 
       assertEquals("setRelationshipTo should update the relationship", newRelationship, testFamilyRelationObject.getRelationshipTo()); 
   }

   @Test
   public void testDuplicationCheck() {
       DisasterVictim personThree = new DisasterVictim("Neha Parmar", "2024-01-10", 19); 
      
       assertTrue(" expected to return true if both persons are the same",
                   testFamilyRelationObject.duplicationCheck(personOne, personTwo));
       assertFalse("expected to return false if persons are different",
                   testFamilyRelationObject.duplicationCheck(personOne, personThree));
   }
   @Test
    public void testDeleteRelationship() {
        testFamilyRelationObject.deleteRelationship();
        assertNull(testFamilyRelationObject.getPersonOne());
        assertNull(testFamilyRelationObject.getPersonTwo());
        assertNull(testFamilyRelationObject.getRelationshipTo());
    }

    @Test
    public void testCheckExistingRelationship() {
        assertTrue(testFamilyRelationObject.checkExistingRelationship());
    }

    @Test
    public void testConnectedRelation() {
        DisasterVictim personThree = new DisasterVictim("Judy", "2024-03-20", 30);
        String relationshipAB = "Parent";
        String relationshipBC = "Child";
        FamilyRelation relationAB = new FamilyRelation(personOne, relationshipAB, personTwo);
        FamilyRelation relationBC = new FamilyRelation(personTwo, relationshipBC, personThree);
        boolean isRelatedAC = relationAB.checkExistingRelationship() && relationBC.checkExistingRelationship();
        System.out.println("Is A related to B: " + relationAB.checkExistingRelationship());
        System.out.println("Is B related to C: " + relationBC.checkExistingRelationship());
        assertTrue(isRelatedAC);
    }

}
