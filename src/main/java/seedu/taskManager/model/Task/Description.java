package seedu.taskManager.model.Task;

import seedu.taskManager.commons.exceptions.IllegalValueException;

/**
 * Represents a description in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Description should only contain alphanumeric characters and whitespace";
    public static final String DESCRIPTION_VALIDATION_REGEX = "^[a-zA-Z0-9_]*$";

    public final String description;

    /**
     * Validates given description.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Description(String description) throws IllegalValueException {
        if (description != null) {
            String trimmeddescription = description.trim();
            if (!isValidDescription(trimmeddescription)) {
                throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
            }
            this.description = trimmeddescription;
        } else {
            this.description = description;
        }
    }

    /**
     * Returns true if a given string is a valid task phone number.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX) &&
                !test.contains("d/") && !test.contains("e/") && !test.contains("s/");
    }

    @Override
    public String toString() {
        return description + "\n";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object

                || (other instanceof Description) // instanceof handles nulls
                && this.description.equals(((Description) other).description); // state check

    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
