package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;


public class FamilyRelationTest {
   private DisasterVictim personOne;
   private DisasterVictim personTwo;
   private String relationshipTo;
   private FamilyRelation testFamilyRelationObject;
  
   @Before
   public void setup() {
       personOne = new DisasterVictim("Neha Parmar", "2024-01-10");
       personTwo = new DisasterVictim("John Doe", "2024-02-20");
       relationshipTo = "sibling";
       testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
   }

   @Test
   public void testObjectCreation() {
       assertNotNull(testFamilyRelationObject);
   }
  
   @Test
   public void testSetAndGetPersonOne() {
       DisasterVictim newPersonOne = new DisasterVictim("New Person One", "2024-01-01"); 
       testFamilyRelationObject.setPersonOne(newPersonOne); 
       assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());//assert
   }


   @Test
   public void testSetAndGetPersonTwo() {
       DisasterVictim newPersonTwo = new DisasterVictim("New Person Two", "2024-01-02"); 
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
       DisasterVictim personThree = new DisasterVictim("Neha Parmar", "2024-01-10"); 
      
       assertTrue(" expected to return true if both persons are the same",
                   testFamilyRelationObject.duplicationCheck(personOne, personTwo));
       assertFalse("expected to return false if persons are different",
                   testFamilyRelationObject.duplicationCheck(personOne, personThree));
   }
}
