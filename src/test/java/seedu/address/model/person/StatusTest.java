package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.Status;
//@@author A0140072X
public class StatusTest {

    @Test
    public void isValidStatus() {
        //Invalid Statues
        assertFalse(Status.isValidName("")); // empty Statues
        assertFalse(Status.isValidName(" ")); // spaces Statues
        assertFalse(Status.isValidName("low")); // Invalid Statues
        assertFalse(Status.isValidName("ongoing")); // Invalid Statues
        assertFalse(Status.isValidName("complete")); // Invalid Statues
        assertFalse(Status.isValidName("Finished")); // Invalid Statues
        assertFalse(Status.isValidName("DONE*&")); // Invalid Statues

        //Valid Statues
        assertTrue(Status.isValidName("done"));
        assertTrue(Status.isValidName("undone"));
    }
}
