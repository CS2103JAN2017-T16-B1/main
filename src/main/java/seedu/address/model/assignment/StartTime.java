package seedu.address.model.assignment;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's or event's description in the Task Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class StartTime {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Event Start Time should only contain numerals and dashes such as 2017-3-14-2100"; 

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String STARTTIME_VALIDATION_REGEX = "d{4}-d{1-2}-d{1-2}-d{4}";

    public final String StartTime;

    /**
     * Validates given Start time.
     *
     * @throws IllegalValueException if given time string is invalid.
     */
    public StartTime(String startTime) throws IllegalValueException {
        assert startTime != null;
        String trimmedTime = startTime.trim();
        if (!isValidTime(trimmedTime)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.StartTime = trimmedTime;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(STARTTIME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return StartTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.StartTime.equals(((StartTime) other).StartTime)); // state check
    }

    @Override
    public int hashCode() {
        return StartTime.hashCode();
    }

}
