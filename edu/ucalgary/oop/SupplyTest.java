
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {
    String expectedType = "Socks";
	int expectedQuantity = 3;
    private Supply supply = new Supply(expectedType, expectedQuantity);

    @Test
    public void testObjectCreation() {
        assertNotNull(supply);
    }

    @Test
    public void testGetType() {
        assertEquals("getType should return correct type.", expectedType, supply.getType());
    }

    @Test
    public void testSetType() {
        supply.setType("Blanket");
        assertEquals("setType should update type.", "Blanket", supply.getType());
    }

    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return correct quantity.", expectedQuantity, supply.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        supply.setQuantity(10);
        assertEquals("setQuantity should update quantity.", 10, supply.getQuantity());
    }
}
