package seedu.address.model.assignment;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's or event's description in the Task Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class EndTime {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Event End Time should only contain numerals and dashes such as 2017-3-14-2100"; 

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String ENDTIME_VALIDATION_REGEX = "d{4}-d{1-2}-d{1-2}-d{4}";

    public final String EndTime;

    /**
     * Validates given Start time.
     *
     * @throws IllegalValueException if given time string is invalid.
     */
    public EndTime(String endtime) throws IllegalValueException {
        assert endtime != null;
        String trimmedTime = endtime.trim();
        if (!isValidTime(trimmedTime)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.EndTime = trimmedTime;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(ENDTIME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return EndTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && this.EndTime.equals(((EndTime) other).EndTime)); // state check
    }

    @Override
    public int hashCode() {
        return EndTime.hashCode();
    }

}
