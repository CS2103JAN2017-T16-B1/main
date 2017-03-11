package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.EndTime;

public class AddressTest {

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(EndTime.isValidAddress("")); // empty string
        assertFalse(EndTime.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(EndTime.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(EndTime.isValidAddress("-")); // one character
        assertTrue(EndTime.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
