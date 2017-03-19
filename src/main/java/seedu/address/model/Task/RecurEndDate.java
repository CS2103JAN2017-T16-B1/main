package seedu.address.model.Task;

import seedu.address.commons.exceptions.IllegalValueException;

public class RecurEndDate {
	
	public static final String MESSAGE_ENDDATE_CONSTRAINTS =
            "Recur end date must be in the form of yyyy-mm-dd";

	public static final String ENDDATE_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))*";

    public final String endDate;
    
    /**
     * Validates given endDate.
     *
     * @throws IllegalValueException if the given endDate string is invalid.
     */
	
	public RecurEndDate(String endDate) throws IllegalValueException {
		String trimmedEndDate = endDate.trim();
		
		if (!isValidEndDate(trimmedEndDate)) {
            throw new IllegalValueException(MESSAGE_ENDDATE_CONSTRAINTS);
        }
		this.endDate = trimmedEndDate;
	}
	
	 /**
     * Returns true if a given string is a valid endDate.
     */
	
	private boolean isValidEndDate(String test) {
		return test.matches(ENDDATE_VALIDATION_REGEX);
	}
	
	@Override
    public String toString() {
        return endDate;
    }
}
