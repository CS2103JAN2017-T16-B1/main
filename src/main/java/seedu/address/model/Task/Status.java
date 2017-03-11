package seedu.address.model.Task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's status in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Status {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task/Event status should only be labelled as done or undone";
    
    /*
     * The urgency of the task must be either h,m or l,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String STATUS_VALIDATION_REGEX = "^[done|undone]";

    public final String status;

    /**
     * Validates given status.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Status(String status) throws IllegalValueException {
        assert status != null;
        String trimmedstatus = status.trim();
        if (!isValidName(trimmedstatus)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
       this.status=status;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(STATUS_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.valueOf(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && this.toString().equals(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
