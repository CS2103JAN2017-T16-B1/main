package seedu.address.model.Task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's priority in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Priority {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task/Event Priority should only be labelled as \"h\" for high, \"m\" for medium and \"l\" for low";

    /*
     * The urgency of the task must be either h,m or l,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String PRIORITY_VALIDATION_REGEX = "[h,m,l]";

    public final String priority;

    /**
     * Validates given priority.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Priority(String priority) throws IllegalValueException {
        assert priority != null;
        String trimmedpriority = priority.trim();
        if (!isValidName(trimmedpriority)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid priority name.
     */
    public static boolean isValidName(String test) {
        return test.matches(PRIORITY_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.valueOf(priority);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && this.toString().equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
