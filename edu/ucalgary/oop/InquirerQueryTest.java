package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class InquirerQueryTest {

    @Test
    public void testLogInquiry() {
        ReliefService reliefService = new ReliefService();
        InquiryDetails inquiryDetails = new InquiryDetails("Sample inquiry");
        reliefService.logInquiry(inquiryDetails);
        assertEquals("logInquiry() should correctly log 'Sample inquiry'", "Sample inquiry", reliefService.getInquiryDetails());
    }

    @Test
    public void testSearchByName() {
        ReliefService reliefService = new ReliefService();
        DisasterVictim victim = new DisasterVictim("John Doe", "2024-01-23");
        String partialName = "John";
        List<DisasterVictim> missingPersons = reliefService.searchDisasterVictim(partialName);

        assertFalse("The list of missing persons should not be empty", missingPersons.isEmpty());

        boolean containsPartialName = false;
        for (DisasterVictim missingPerson : missingPersons) {
            if (missingPerson.getFullName().contains(partialName)) {
                containsPartialName = true;
                break;
            }
        }
        assertTrue("searchDisasterVictim() should have found a match if it exists", containsPartialName);
    }
}
