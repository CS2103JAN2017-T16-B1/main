package seedu.address.model.assignment;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's or event's description in the Task Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class DeadLine {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Task DeadLine should only contain numerals and dashes such as 2017-3-14-2100"; 

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DEADLINE_VALIDATION_REGEX = "d{4}-d{1-2}-d{1-2}-d{4}";

    public final String DeadLine;

    /**
     * Validates given Start time.
     *
     * @throws IllegalValueException if given time string is invalid.
     */
    public DeadLine(String deadline) throws IllegalValueException {
        assert deadline != null;
        String trimmedTime = deadline.trim();
        if (!isValidTime(trimmedTime)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.DeadLine = trimmedTime;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(DEADLINE_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return DeadLine;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadLine // instanceof handles nulls
                && this.DeadLine.equals(((DeadLine) other).DeadLine)); // state check
    }

    @Override
    public int hashCode() {
        return DeadLine.hashCode();
    }

}
