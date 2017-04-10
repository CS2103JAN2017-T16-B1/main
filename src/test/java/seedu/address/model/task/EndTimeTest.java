package seedu.address.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.Task.EndTime;
//@@author A0138998B
public class EndTimeTest {

    @Test
    public void isValidEndTime() {
        // invalid addresses
        assertFalse(EndTime.isValidTime("2017-03-051000")); // empty string
        assertFalse(EndTime.isValidTime("201703-05-1000")); //
        assertFalse(EndTime.isValidTime("tomato")); // invalid date
        assertFalse(EndTime.isValidTime("foodclique food")); // invalid date

        // valid addresses
        assertTrue(EndTime.isValidTime("2017-03-05-1000"));
        assertTrue(EndTime.isValidTime("")); // one character
        assertTrue(EndTime.isValidTime("2017-12-05-1000")); // long address
    }

    @Test
    public void parseDate() throws IllegalValueException {
        //natty parsing
        EndTime endTime1 = new EndTime("first tuesday of april 2017 3pm"); // first form of relaxed user input
        assertEquals("2017-04-04-1500", endTime1.endTime);
        EndTime endTime2 = new EndTime("23rd day of april 2017 3pm"); // relaxed user input
        assertEquals("2017-04-23-1500", endTime2.endTime);
    }

}
