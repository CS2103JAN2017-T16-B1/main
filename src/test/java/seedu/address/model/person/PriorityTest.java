package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.Priority;
//@@author A0138998B
public class PriorityTest {

    @Test
    public void isValidPriority() {

        assertFalse(Priority.isValidName("")); // empty string
        assertFalse(Priority.isValidName(" ")); // spaces only
        assertFalse(Priority.isValidName("don")); // incomplete word
        assertFalse(Priority.isValidName("undo")); // incomplete word



        assertTrue(Priority.isValidName("h"));
        assertTrue(Priority.isValidName("m"));
        assertTrue(Priority.isValidName("l"));
    }
}
