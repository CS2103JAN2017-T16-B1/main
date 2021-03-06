package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskManager.model.Task.Name;
//@@author A0140072X
public class NameTest {

    @Test
    public void isValidName() {
        // Invalid Task Name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("d/hello ")); //contain description prefix
        assertFalse(Name.isValidName("s/this saturday e/next sunday")); // No names but with start & end time

        // Valid Task Name
        assertTrue(Name.isValidName("2103 Exam")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("NUS 2017")); // alphanumeric characters
        assertTrue(Name.isValidName("National University of Singapore")); // with capital letters
    }
}
