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
import seedu.address.model.Task.RecurEndDate;
import seedu.address.model.Task.RecurPeriod;
import seedu.address.model.Task.Description;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes
 */
public class ParserUtil {

	private static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

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
        
        return startTime.isPresent() ? Optional.of(new StartTime(startTime.get())) : Optional.empty();
    }
    
    /**
     * Parses a {@code Optional<String> end time} into an {@code Optional<EndTime>} if {@code end time} is present.
     */
    public static Optional<EndTime> parseEndTime(Optional<String> endTime) throws IllegalValueException {
        assert endTime != null;
        return endTime.isPresent() ? Optional.of(new EndTime(endTime.get())) : Optional.empty();
        
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
     * Parses a {@code Optional<String> recurPeriod} into an {@code Optional<RecurPeriod>} if {@code status} is present.
     */
    public static Optional<RecurPeriod> parseRecurPeriod(Optional<String> recurPeriod) throws IllegalValueException {
        assert recurPeriod != null;
        return recurPeriod.isPresent() ? Optional.of(new RecurPeriod(recurPeriod.get())) : Optional.empty();
    }
    /**
     * Parses a {@code Optional<String> recurEndDate} into an {@code Optional<RecurEndDate>} if {@code status} is present.
     */
    public static Optional<RecurEndDate> parseRecurEndDate(Optional<String> recurEndDate) throws IllegalValueException {
        assert recurEndDate != null;
        return recurEndDate.isPresent() ? Optional.of(new RecurEndDate(recurEndDate.get())) : Optional.empty();
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
