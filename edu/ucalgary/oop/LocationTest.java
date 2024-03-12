package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class LocationTest {
   private Location location;
   private DisasterVictim victim;
   private Supply supply;


   @Before
   public void setUp() {


       location = new Location("Calgary Shelter", "321 Southwest Ave");
       victim = new DisasterVictim("John Doe", "2024-02-01", 20);
       supply = new Supply("Water Bottle", 8);
   }


   @Test
   public void testConstructor() {
       assertNotNull("Constructor should create a non-null Location object", location);
       assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
       assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
   }


   @Test
   public void testSetName() {
       String newName = "Edmonton Shelter";
       location.setName(newName);
       assertEquals("setName should update the location name.", newName, location.getName());
   }


   @Test
   public void testSetAddress() {
       String newAddress = "543 Southwest Blvd";
       location.setAddress(newAddress);
       assertEquals("setAddress should update the location address.", newAddress, location.getAddress());
   }


   @Test
   public void testAddOccupant() {
       location.addOccupant(victim);
       assertTrue("addOccupant should add a disaster victim to the list.", location.getOccupants().contains(victim));
   }


   @Test
   public void testRemoveOccupant() {
       location.addOccupant(victim); 
       location.removeOccupant(victim);
       assertFalse("removeOccupant should remove disaster victim from occupants list.", location.getOccupants().contains(victim));
   }


   @Test
   public void testSetAndGetOccupants() {
       ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
       newOccupants.add(victim);
       location.setOccupants(newOccupants);
       assertEquals("setOccupants should replace occupants list with new list.", newOccupants, location.getOccupants());
   }


   @Test
   public void testAddSupply() {
       location.addSupply(supply);
       assertTrue("addSupply should add supply to supplies list.", containsSupply(location.getSupplies(), supply));
   }


   @Test
   public void testRemoveSupply() {
       location.addSupply(supply); // Ensure the supply is added first
       location.removeSupply(supply);
       assertFalse("removeSupply should remove supply from supplies list.", containsSupply(location.getSupplies(), supply));
   }


   @Test
   public void testSetAndGetSupplies() {
       ArrayList<Supply> newSupplies = new ArrayList<>();
       newSupplies.add(supply);
       location.setSupplies(newSupplies);
       assertEquals("setSupplies should replace supplies list with new list.", newSupplies, location.getSupplies());
   }


   @Test(expected = IllegalArgumentException.class)
   public void testSetNameWithNull() {
       location.setName(null); // This should throw IllegalArgumentException
   }


   @Test(expected = IllegalArgumentException.class)
   public void testSetAddressWithNull() {
       location.setAddress(null); // This should throw IllegalArgumentException
   }
}


