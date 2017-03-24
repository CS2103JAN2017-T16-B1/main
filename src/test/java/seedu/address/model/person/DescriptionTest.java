package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.Description;

public class DescriptionTest {

    @Test
    public void isValidDescription() {
     
        // valid phone numbers
        assertTrue(Description.isValidDescription("93121534"));
        assertTrue(Description.isValidDescription("4")); // short phone numbers
        assertTrue(Description.isValidDescription("124293842033123")); // long phone numbers
        assertTrue(Description.isValidDescription("9011p041")); // alphabets within digits
        assertTrue(Description.isValidDescription("9312 1534")); // spaces within digits
        assertTrue(Description.isValidDescription("hello hello hello")); // spaces within digits
    }
}
