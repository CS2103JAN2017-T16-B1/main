package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.Status;

public class StatusTest {

    @Test
    public void isValidStatus() {
       
        assertFalse(Status.isValidName("")); // empty string
        assertFalse(Status.isValidName(" ")); // spaces only
        assertFalse(Status.isValidName("don")); // incomplete word
        assertFalse(Status.isValidName("undo")); // incomplete word



        assertTrue(Status.isValidName("done")); 
        assertTrue(Status.isValidName("undone"));
    }
}
