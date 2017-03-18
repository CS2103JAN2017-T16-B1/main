package seedu.address.logic.parser;

import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.EndTime;

import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Description;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes
 */
public class ParserUtil {
	private static final String DATETIME_VALIDATION_REGEX = "(((18|19|20|21)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-[0-9]{4})*";
	private static final String DATE_VALIDATION_REGEX ="^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)";
	private static final String TIME_VALIDATION_REGEX ="^([0-9]{4})";
	private static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
	public static final String MESSAGE_DAY_CONSTRAINTS =
            "Dates must be in the form of full names of days of the week i.e. Monday";
	public static final String MESSAGE_TIME_CONSTRAINTS =
            "Times must be in the form of HHMM i.e. 1000";
	public static final String MESSAGE_INVALID_INPUT =
            "Format of date and time must be a day followed by a 24H time i.e. Monday 1000";
	/**
     * Returns the specified index in the {@code command} if it is a positive unsigned integer
     * Returns an {@code Optional.empty()} otherwise.
     */
    
    private static final Pattern LIST_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static Optional<Integer> parseIndex(String command) {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(command.trim());
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String index = matcher.group("targetIndex");
        if (!StringUtil.isUnsignedInteger(index)) {
            return Optional.empty();
        }
        return Optional.of(Integer.parseInt(index));

    }

    public static Optional<Character> parseList(String command) {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(command.trim());
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String list = matcher.group("targetIndex");
        if (!StringUtil.isUnsignedInteger(list)) {
            return Optional.empty();
        }
        return null;

    }
    /**
     * Returns a new Set populated by all elements in the given list of strings
     * Returns an empty set if the given {@code Optional} is empty,
     * or if the list contained in the {@code Optional} is empty
     */
    public static Set<String> toSet(Optional<List<String>> list) {
        List<String> elements = list.orElse(Collections.emptyList());
        return new HashSet<>(elements);
    }

    /**
    * Splits a preamble string into ordered fields.
    * @return A list of size {@code numFields} where the ith element is the ith field value if specified in
    *         the input, {@code Optional.empty()} otherwise.
    */
    public static List<Optional<String>> splitPreamble(String preamble, int numFields) {
        return Arrays.stream(Arrays.copyOf(preamble.split("\\s+", numFields), numFields))
                .map(Optional::ofNullable)
                .collect(Collectors.toList());
    }

    /**
     * Parses a {@code Optional<String> name} into an {@code Optional<Name>} if {@code name} is present.
     */
    public static Optional<Name> parseName(Optional<String> name) throws IllegalValueException {
        assert name != null;
        return name.isPresent() ? Optional.of(new Name(name.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> start time} into an {@code Optional<Phone>} if {@code start time} is present.
     */
    public static Optional<StartTime> parseStartTime(Optional<String> startTime) throws IllegalValueException {
        assert startTime != null;
        String time=null;
        
        //Returns empty if no user input
        if(startTime.isPresent()){
        	time =startTime.get();
        }else{
        	return Optional.empty();
        }
        
        //Returns start time object if user input was in the correct format
        if(time.matches(DATETIME_VALIDATION_REGEX)){
        	return Optional.of(new StartTime(startTime.get()));
        }else{
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-");
            int intTime = 0;
        	ArrayList<String> times = parseDayAndTime(time);
        	checkForCorrectFormats(times);
        	LocalDateTime date = LocalDateTime.now();
            intTime = getDayAsInt(times, intTime);
            date = getNearestDate(date, intTime);
            return Optional.of(new StartTime(dtf.format(date)+times.get(1)));
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
			throw new IllegalValueException(MESSAGE_INVALID_INPUT);
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

    /**
     * Parses a {@code Optional<String> end time} into an {@code Optional<EndTime>} if {@code end time} is present.
     */
    public static Optional<EndTime> parseEndTime(Optional<String> endTime) throws IllegalValueException {
        assert endTime != null;
        String time=null;
        
        //Returns empty if no user input
        if(endTime.isPresent()){
        	time =endTime.get();
        }else{
        	return Optional.empty();
        }
        
        //Returns start time object if user input was in the correct format
        if(time.matches(DATETIME_VALIDATION_REGEX)){
        	return Optional.of(new EndTime(endTime.get()));
        }else{
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-");
            int intTime = 0;
        	ArrayList<String> times = parseDayAndTime(time);
        	checkForCorrectFormats(times);
        	LocalDateTime date = LocalDateTime.now();
            intTime = getDayAsInt(times, intTime);
            date = getNearestDate(date, intTime);
            return Optional.of(new EndTime(dtf.format(date)+times.get(1)));
        }
        
    }

    /**
     * Parses a {@code Optional<String> description} into an {@code Optional<Description>} if {@code description} is present.
     */
    public static Optional<Description> parseDescription(Optional<String> description) throws IllegalValueException {
        assert description != null;
        return description.isPresent() ? Optional.of(new Description(description.get())) : Optional.empty();
    }
    /**
     * Parses a {@code Optional<String> priority } into an {@code Optional<Priority>} if {@code priority} is present.
     */
    public static Optional<Priority> parsePriority(Optional<String> priority) throws IllegalValueException {
        assert priority != null;
        return priority.isPresent() ? Optional.of(new Priority(priority.get())) : Optional.empty();
    }
    /**
     * Parses a {@code Optional<String> status} into an {@code Optional<Status>} if {@code status} is present.
     */
    public static Optional<Status> parseStatus(Optional<String> status) throws IllegalValueException {
        assert status != null;
        return status.isPresent() ? Optional.of(new Status(status.get())) : Optional.empty();
    }
    /**
     * Parses {@code Collection<String> tags} into an {@code UniqueTagList}.
     */
    public static UniqueTagList parseTags(Collection<String> tags) throws IllegalValueException {
        assert tags != null;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        return new UniqueTagList(tagSet);
    }
}
