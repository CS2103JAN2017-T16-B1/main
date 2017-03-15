package seedu.address.model.Task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's Id in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ID {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task/Event ID should only contain alphanumeric characters and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String ID_VALIDATION_REGEX = "\\d+";

    public final int id;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public ID(String id) throws IllegalValueException {
        assert id != null;
        String trimmedId = id.trim();
        if (!isValidName(trimmedId)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        int trimmedIntId= Integer.parseInt(trimmedId);
        this.id = trimmedIntId;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidName(String test) {
        return test.matches(ID_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ID // instanceof handles nulls
                && this.toString().equals(((ID) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
