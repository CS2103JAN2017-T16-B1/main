package seedu.address.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.Task.StartTime;
//@@author A0138998B
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

    @Test
    public void parseDate() throws IllegalValueException {
        //natty parsing
        StartTime startTime1 = new StartTime("first tuesday of april 2017 3pm"); // first form of relaxed user input
        assertEquals("2017-04-04-1500", startTime1.startTime);
        StartTime startTime2 = new StartTime("23rd day of april 2017 3pm"); // relaxed user input
        assertEquals("2017-04-23-1500", startTime2.startTime);
    }
}
