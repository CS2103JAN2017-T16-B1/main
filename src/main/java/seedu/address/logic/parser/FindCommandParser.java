package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.KEYWORDS_ARGS_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.TaskStringReference;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser {
    //@@author A0139509X
    private static final char PREFIX_HASHTAG = '#';
    private static final char PREFIX_AT = '@';

    // @@author A0139509X
    /**
     *  Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns an FindCommand object for execution.
     *
     * @throws IllegalValueException
     */
    public Command parse(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");

        try {
            if (keywords[0].charAt(0) == PREFIX_HASHTAG) {
                return returnFindCommandForHashtagPrefix(keywords[0]);
            }
        }
        catch (IllegalValueException e) {
            return new IncorrectCommand(e.getMessage());
        }

        if (keywords[0].charAt(0) == PREFIX_AT) {
            return returnFindCommandForAtPrefix(keywords[0]);
        }

        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

    private Command returnFindCommandForAtPrefix(String keywords) {
        if (keywords.substring(1).isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(keywords.substring(1));
    }

    private Command returnFindCommandForHashtagPrefix(String keywords) throws IllegalValueException {
        if (keywords.substring(1).isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (keywords.substring(1).equalsIgnoreCase(TaskStringReference.STATUS_DONE)) {
            Status status = new Status(keywords.substring(1));
            return new FindCommand(status);
        } else if (keywords.substring(1).equalsIgnoreCase(TaskStringReference.STATUS_UNDONE)) {
            Status status = new Status(keywords.substring(1));
            return new FindCommand(status);
        } else if (keywords.substring(1).equalsIgnoreCase(TaskStringReference.PRIORITY_HIGH)) {
            Priority priority = new Priority(keywords.substring(1));
            return new FindCommand(priority);
        } else if (keywords.substring(1).equalsIgnoreCase(TaskStringReference.PRIORITY_MEDIUM)) {
            Priority priority = new Priority(keywords.substring(1));
            return new FindCommand(priority);
        } else if (keywords.substring(1).equalsIgnoreCase(TaskStringReference.PRIORITY_LOW)) {
            Priority priority = new Priority(keywords.substring(1));
            return new FindCommand(priority);
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
