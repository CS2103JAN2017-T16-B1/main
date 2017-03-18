package seedu.address.model.Task;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a event's end time in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class EndTime {

    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Event end times must be in the form of yyyy-mm-dd-HHMM";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String ENDTIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";

    public final String endTime;

    /**
     * Validates given endTime.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public EndTime(String endTime) throws IllegalValueException {
        assert endTime != null;

        if(endTime!=null){

            if (!isValidTime(endTime)) {
                throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
            }
            this.endTime = endTime;
        }

        else{
            this.endTime=null;
        }
        

    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(ENDTIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               // || (other instanceof EndTime // instanceof handles nulls
                && this.endTime.equals(((EndTime) other).endTime); // state check
    }

    @Override
    public int hashCode() {
        return endTime.hashCode();
    }
    public boolean isEmpty() {
        if (endTime == "") {
            return true;
        }
        else {
            return false;
        }
    }

}
