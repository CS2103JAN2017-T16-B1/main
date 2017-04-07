package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.model.Task.Description;
//@@author A0140072X
public class DescriptionTest {

    @Test
    public void isValidDescription() {
        //invalid Task description
        assertFalse(Description.isValidDescription("d/hello")); // additional description prefix
        assertFalse(Description.isValidDescription("s/this saturday")); // additional starttime prefix
        assertFalse(Description.isValidDescription("e/next sunday")); // additional endtime prefix

        //valid Task Description
        assertTrue(Description.isValidDescription("Venue at LT27"));
        assertTrue(Description.isValidDescription("4")); // short Description
        assertTrue(Description.isValidDescription("Venue at National University of Singapore")); // long Description
        assertTrue(Description.isValidDescription("9011p041")); // alphabets within Description
        assertTrue(Description.isValidDescription("9312-1534*()")); // symbols within Description
        assertTrue(Description.isValidDescription("hello hello hello")); // spaces within Description
    }
}
