package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.EndTime;

public class EndTimeTest {

    @Test
    public void isValidEndTime() {
        // invalid addresses
        assertFalse(EndTime.isValidTime("2017-03-051000")); // empty string
        assertFalse(EndTime.isValidTime("201703-05-1000")); // spaces only

        // valid addresses
        assertTrue(EndTime.isValidTime("2017-03-05-1000"));
        assertTrue(EndTime.isValidTime("")); // one character
        assertTrue(EndTime.isValidTime("2017-12-05-1000")); // long address
    }
}
