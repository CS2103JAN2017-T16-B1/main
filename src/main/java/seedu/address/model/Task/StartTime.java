package seedu.address.model.Task;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a event's start time or task's due date in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Times must be in the form of yyyy-mm-dd-HHMM";
    public static final String TIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";

    public final String startTime;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public StartTime(String startTime) throws IllegalValueException {
        assert startTime != null;
        String trimmedTime = startTime.trim();
        if (!isValidTime(trimmedTime)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.startTime = trimmedTime;
    }

    /**
     * Returns if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return startTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.startTime.equals(((StartTime) other).startTime)); // state check
    }

    @Override
    public int hashCode() {
        return startTime.hashCode();
    }
    
    public boolean isEmpty(){
    	if(startTime==""){
    		return true;
    	}else{
    		return false;
    	}
    }

}
