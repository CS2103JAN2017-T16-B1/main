package seedu.address.model.assignment;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's or event's description in the Task Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Task and Event descriptions should only contain alphanumeric characters and spaces";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "^$|^[a-zA-Z0-9]+$";

    public final String fullDescription;

    /**
     * Validates given description.
     *
     * @throws IllegalValueException if given description is invalid.
     */
    public Description(String description) throws IllegalValueException {
        assert description != null;
        String trimmedDescription = description.trim();
        if (!isValidDescription(trimmedDescription)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.fullDescription = trimmedDescription;
    }

    /**
     * Returns true if a given string is a valid description name.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.fullDescription.equals(((Description) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
