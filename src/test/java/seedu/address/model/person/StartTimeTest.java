package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.StartTime;

public class StartTimeTest {

    @Test
    public void isValidStartTime() {
        // invalid addresses
        assertFalse(StartTime.isValidTime("2017-03-051000")); // empty string
        assertFalse(StartTime.isValidTime("201703-05-1000")); // spaces only

        // valid addresses
        assertTrue(StartTime.isValidTime("2017-03-05-1000"));
        assertTrue(StartTime.isValidTime("")); // one character
        assertTrue(StartTime.isValidTime("2017-12-05-1000")); // long address
    }
}
