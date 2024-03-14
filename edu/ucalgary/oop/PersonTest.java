package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PersonTest {
private Person person;
private String expectedFirstName = "Neha";
private String expectedLastName = "Parmar";


@Before
public void setUp() {
person = new Person(expectedFirstName, expectedLastName);
}


@Test
public void testSetAndGetFirstName() {
String newFirstName = "Alia";
person.setFirstName(newFirstName);
assertEquals("setFirstName should update; getFirstName should return new first name", newFirstName, person.getFirstName());
}


@Test
public void testSetAndGetLastName() {
String newLastName = "Abdul";
person.setLastName(newLastName);
assertEquals("setLastName should update; getLastName should return new last name", newLastName, person.getLastName());
}
}
