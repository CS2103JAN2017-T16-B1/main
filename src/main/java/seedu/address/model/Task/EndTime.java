package seedu.address.model.Task;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joestelmach.natty.*;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a event's end time in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
//@@author A0138998B
public class EndTime {

	 public static final String MESSAGE_DATETIME_CONSTRAINTS =
	            "Event end times must be in the form of yyyy-mm-dd-HHMM or other relaxed forms of it";;
	 private static final String DATETIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";
	 private static final String DATE_VALIDATION_REGEX ="^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)";
	 private static final String TIME_VALIDATION_REGEX ="^([0-9]{4})";
	 public static final String MESSAGE_DAY_CONSTRAINTS =
	            "Dates must be in the form of full names of days of the week i.e. Monday";
	public static final String MESSAGE_TIME_CONSTRAINTS =
	            "Times must be in the form of HHMM i.e. 1000";
    public final String endTime;

    /**
     * Validates given endTime.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public EndTime(String endTime) throws IllegalValueException {
        assert endTime != null;
        String trimmedTime = endTime.trim();
        
        if(endTime!=null){
        	trimmedTime = parseDate(trimmedTime);
        }
        
        if(!isValidTime(trimmedTime)){
        	throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
        }
        
        this.endTime=trimmedTime;
      
    }
    /**
     * Uses Natty to parse a possibly relaxed user input for end Time
     *
     * @throws IllegalValueException if given address string is invalid.
     */
	private String parseDate(String trimmedTime) throws IllegalValueException {
		if(!isValidTime(trimmedTime)){
			Parser parser = new Parser();
			List<DateGroup> groups = parser.parse(trimmedTime);
			List<Date> dates = null;
			if(groups.isEmpty()){
				throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
			}
			for(DateGroup group:groups) {
			  dates = group.getDates();
			}
			DateTimeFormatter nattyDateFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
			nattyDateFormat.parse(dates.get(0).toString());
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HHmm");
			trimmedTime=dateFormat.format(dates.get(0));
		}
		return trimmedTime;
	}

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(DATETIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return endTime+"\n";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && this.endTime.equals(((EndTime) other).endTime)); // state check
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
    
    /**
     * splits date and time into different
     * @param time
     * @return
     */
	private static ArrayList<String> parseDayAndTime(String time) {
		
		time=time.toLowerCase();
		ArrayList<String> times= new ArrayList<String>(Arrays.asList(time.split(" ")));
		return times;
	}
	/**
	 * 
	 * @param times
	 * @throws IllegalValueException when format of date and time is not right
	 */

}
