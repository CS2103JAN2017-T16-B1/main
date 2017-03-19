package seedu.address.model.Task;

import seedu.address.commons.exceptions.IllegalValueException;

public class RecurPeriod {
	
	public static final String MESSAGE_PERIOD_CONSTRAINTS =
            "Recur period should only a positive integer for the number of days inbetween each recurrance and it should not be blank";

	public static final String PERIOD_VALIDATION_REGEX = "^[0-9]";
	private static final String DATE_VALIDATION_REGEX ="^(weekly|fortnightly|monthly|yearly)";
	
    public final String period;
    
    /**
     * Validates given period.
     *
     * @throws IllegalValueException if the given period string is invalid.
     */
    
	public RecurPeriod(String period, String endDate) throws IllegalValueException{
		String trimmedPeriod = period.trim();
		String trimmedEndDate = endDate.trim();
		
		if (!isValidPeriod(trimmedPeriod)) {
            throw new IllegalValueException(MESSAGE_PERIOD_CONSTRAINTS);
        }
		this.period = period;
	}
	
	 /**
     * Returns true if a given string is a valid period.
     */

	private boolean isValidPeriod(String test) {
		return test.matches(PERIOD_VALIDATION_REGEX);
	}
	
	@Override
    public String toString() {
        return period;
    }
	
}
