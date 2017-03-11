package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.Description;

public class PhoneTest {

    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("phone")); // non-numeric
        assertFalse(Description.isValidDescription("9011p041")); // alphabets within digits
        assertFalse(Description.isValidDescription("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Description.isValidDescription("93121534"));
        assertTrue(Description.isValidDescription("4")); // short phone numbers
        assertTrue(Description.isValidDescription("124293842033123")); // long phone numbers
    }
}
