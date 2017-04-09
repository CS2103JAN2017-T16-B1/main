package seedu.taskManager.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.taskManager.commons.exceptions.IllegalValueException;
//@@author A0139375W
public class RecurPeriod {

    public static final String MESSAGE_PERIOD_CONSTRAINTS =
            "Recur period should only a positive integer for the number of days "
                    + "inbetween each recurrance or weekly, monthly, yearly";

    private static final String PERIOD_VALIDATION_REGEX = "^([1-999]|weekly|monthly|yearly)*";

    public final String period;



    /**
     * Validates given period.
     *
     * @throws IllegalValueException if the given period string is invalid.
     */

    public RecurPeriod(String period) throws IllegalValueException {
        if (period != "") {
            String trimmedPeriod = period.trim();

            if (!isValidPeriod(trimmedPeriod)) {
                throw new IllegalValueException(MESSAGE_PERIOD_CONSTRAINTS);
            }

            this.period = trimmedPeriod;
        }
        else this.period = period;
    }

    public static boolean isValidPeriod(String test) {
        return test.matches(PERIOD_VALIDATION_REGEX);
    }


    public String updatedDate(String date) {
        if (date == "\n") {
            return date;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm\n");
        LocalDateTime oldDate = LocalDateTime.parse(date, formatter);
        String newDate;

        switch(this.period) {

        case("weekly"):
            oldDate = oldDate.plusWeeks(1); break;

        case("monthly"):
            oldDate = oldDate.plusMonths(1); break;

        case("annually"):
            oldDate = oldDate.plusYears(1); break;

        default:
            oldDate = oldDate.plusDays(Integer.parseInt(this.period));
            break;

        }
        newDate = oldDate.format(formatter);
        return newDate;
    }



    public boolean hasRecurPeriod() {
        if (period == "") return false;
        return true;
    }

    @Override
    public String toString() {
        return period;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object

                || (other instanceof RecurPeriod) // instanceof handles nulls
                && this.period.equals(((RecurPeriod) other).period); // state check

    }

    @Override
    public int hashCode() {
        return period.hashCode();
    }
}
