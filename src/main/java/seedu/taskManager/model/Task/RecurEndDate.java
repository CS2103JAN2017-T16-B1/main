package seedu.taskManager.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.taskManager.commons.exceptions.IllegalValueException;
//@@author A0139375W
public class RecurEndDate {

    public static final String MESSAGE_ENDDATE_CONSTRAINTS =
            "Recur end date must be in the form of yyyy-mm-dd";

    public static final String ENDDATE_VALIDATION_REGEX =
            "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";

    public final String endDate;

    /**
     * Validates given endDate.
     *
     * @throws IllegalValueException if the given endDate string is invalid.
     */

    public RecurEndDate(String endDate) throws IllegalValueException {
        if (endDate != "") {
            String trimmedEndDate = endDate.trim();

            if (!isValidEndDate(trimmedEndDate)) {
                throw new IllegalValueException(MESSAGE_ENDDATE_CONSTRAINTS);
            }
            this.endDate = trimmedEndDate;
        } else {
            this.endDate = endDate;
        }
    }

    /**
     * Returns true if a given string is a valid endDate.
     */


    private boolean isValidEndDate(String test) {
        return test.matches(ENDDATE_VALIDATION_REGEX);
    }

    public boolean hasPassedEndDate(String date) {
        if (date != null && this.endDate != "") {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm\n");
            LocalDateTime a = LocalDateTime.parse(date, formatter);
            LocalDateTime b = LocalDateTime.parse(this.endDate, formatter);
            return a.isAfter(b);
        }
        return false;
    }

    public boolean hasRecurEndDate() {
        if (endDate == "") return false;
        return true;
    }


    @Override
    public String toString() {
        return endDate + "\n";
    }

    @Override
    public int hashCode() {
        return endDate.hashCode();
    }

}
