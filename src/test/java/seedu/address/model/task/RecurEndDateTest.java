package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.model.Task.RecurEndDate;

//@@author A0139375W
public class RecurEndDateTest {

    @Test
    public void isValidRecurEndDate() {
        //invalid RecurEndDate
        assertFalse(RecurEndDate.isValidEndDate("2017-05-05")); // missing time
        assertFalse(RecurEndDate.isValidEndDate("201706051200")); // Date time without -
        assertFalse(RecurEndDate.isValidEndDate("2017-13-20-1200")); // Incorrect date, 13th month
        assertFalse(RecurEndDate.isValidEndDate("2017-13-20-5000")); // Incorrect time

        //valid RecurEndDate
        assertTrue(RecurEndDate.isValidEndDate("2017-05-05-1000")); //standard date format
        assertTrue(RecurEndDate.isValidEndDate("")); //empty endDate
    }
}
