package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.model.Task.RecurPeriod;
//@@author A0139375W
public class RecurPeriodTest {

    @Test
    public void isValidRecurPeriod() {
        //invalid Task description
        assertFalse(RecurPeriod.isValidPeriod("week")); // random words
        assertFalse(RecurPeriod.isValidPeriod("$#@")); // symbols
        assertFalse(RecurPeriod.isValidPeriod("-25")); // negative number
        assertFalse(RecurPeriod.isValidPeriod("0")); // invalid number
        assertFalse(RecurPeriod.isValidPeriod("1000")); // invalid number

        //valid Task Description
        assertTrue(RecurPeriod.isValidPeriod("1")); // small int
        assertTrue(RecurPeriod.isValidPeriod("999")); // large int
        assertTrue(RecurPeriod.isValidPeriod("weekly")); // Recognizable word
        assertTrue(RecurPeriod.isValidPeriod("monthly")); // Recognizable word
        assertTrue(RecurPeriod.isValidPeriod("yearly")); // Recognizable word
        assertTrue(RecurPeriod.isValidPeriod("98")); // double digit int
        assertTrue(RecurPeriod.isValidPeriod("98")); // empty period
    }
}
