package seedu.address.model.Task;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a event's start time or task's due date in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
//@@author A0138998B

public class StartTime {

    public static final String MESSAGE_DATETIME_CONSTRAINTS =
            "Event start times must be in the form of yyyy-mm-dd-HHMM or other relaxed forms";;
    
    private static final String DATETIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";
	
    public static final String MESSAGE_DAY_CONSTRAINTS =
            "Dates must be in the form of full names of days of the week i.e. Monday";
	
    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Times must be in the form of HHMM i.e. 1000";
    
    public final String startTime;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public StartTime(String startTime) throws IllegalValueException {
        
        String trimmedTime = startTime.trim();
        
        if(startTime!=null){
        	trimmedTime = parseDate(trimmedTime);
        }
        
        if(!isValidTime(trimmedTime)){
        	throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
        }
        
        this.startTime=trimmedTime;
               
    }

	/**
	 * @param trimmedTime
	 * @return a string parsed using natty. 
	 * @throws IllegalValueException
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
     * Returns if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(DATETIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return startTime+"\n";
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


