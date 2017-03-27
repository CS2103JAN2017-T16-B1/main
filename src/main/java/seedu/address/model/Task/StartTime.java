package seedu.address.model.Task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a event's start time or task's due date in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
//@@A0138998B

public class StartTime {

    public static final String MESSAGE_DATETIME_CONSTRAINTS =
            "Event start times must be in the form of yyyy-mm-dd-HHMM or Monday HHMM";;
    private static final String DATETIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";
	private static final String DATE_VALIDATION_REGEX ="^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)";
	private static final String TIME_VALIDATION_REGEX ="^([0-9]{4})";
	private static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
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
        int intTime = 0;
        if(startTime!=null){
            String trimmedTime = startTime.trim();
            
          
            if(!isValidTime(trimmedTime)){
            	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-");
            	ArrayList<String> times = parseDayAndTime(startTime);
            	checkForCorrectFormats(times);
            	LocalDateTime date = LocalDateTime.now();
                intTime = getDayAsInt(times, intTime);
                date = getNearestDate(date, intTime);
                trimmedTime=dtf.format(date)+times.get(1);
                if (!isValidTime(trimmedTime)) {
                    throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
                }
            }
            
            
           
            this.startTime = trimmedTime;
        }
        else{
            this.startTime=null;
        }
               
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

	private static void checkForCorrectFormats(ArrayList<String> times) throws IllegalValueException {
		if(times.size()!=2){
			throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
		}
		
		if(!times.get(0).matches(DATE_VALIDATION_REGEX)){
			throw new IllegalValueException(MESSAGE_DAY_CONSTRAINTS);
		}
		if(!times.get(1).matches(TIME_VALIDATION_REGEX)){
			throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
		}
	}
/**
 * 
 * @param times is an array that holds both the day and time
 * @param intTime 
 * @return intTime to match the day of the week specified as an integer
 */
	private static int getDayAsInt(ArrayList<String> times, int intTime) {
		switch(times.get(0)){
		
			case("monday"):
			intTime=1;break;
			
			case("tuesday"):
		   	 intTime=2;break;
			
			case("wednesday"):
		   	 intTime=3;break;
			
			case("thursday"):
		   	 intTime=4;break;
			
			case("friday"):
		   	 intTime=5;break;
			
			case("saturday"):
		     intTime=6;break;
			
			case("sunday"):
		     intTime=7;break;
			
		}
		return intTime;
	}
	/**
	 * 
	 * @param date is the current date time on the users computer
	 * @param intTime is the user specified date 
	 * @return the closest date from current date 
	 */
	private static LocalDateTime getNearestDate(LocalDateTime date, int intTime) {
		while (date.getDayOfWeek().getValue() != intTime) {
		    date=date.plusDays(1);
		}
		return date;
	}

    
}
//@@A0138998B

