package seedu.taskManager.logic.parser;

import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskManager.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.taskManager.logic.commands.AddCommand;
import seedu.taskManager.logic.commands.ArchiveCommand;
import seedu.taskManager.logic.commands.ArchivedCommand;
import seedu.taskManager.logic.commands.ClearCommand;
import seedu.taskManager.logic.commands.Command;
import seedu.taskManager.logic.commands.DeleteCommand;
import seedu.taskManager.logic.commands.EditCommand;
import seedu.taskManager.logic.commands.ExitCommand;
import seedu.taskManager.logic.commands.FindCommand;
import seedu.taskManager.logic.commands.HelpCommand;
import seedu.taskManager.logic.commands.IncorrectCommand;
import seedu.taskManager.logic.commands.ListCommand;
import seedu.taskManager.logic.commands.SelectCommand;
import seedu.taskManager.logic.commands.SortCommand;
import seedu.taskManager.logic.commands.ToggleCommand;
import seedu.taskManager.logic.commands.UndoCommand;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final String STARTTIME_VARIANTS_REGEX = "(FROM )";
    public static final String ENDTIME_VARIANTS_REGEX = "(TO |BY )";
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommandParser().parse(arguments);

        case ArchivedCommand.COMMAND_WORD:
            return new ArchivedCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ToggleCommand.COMMAND_WORD:
            return new ToggleCommand();

        default:
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND);
        }
    }
    //@@author A0138998B
    /*
     * Parses user input and replaces any commonly used words such as FROM/TO/BY 
     * with the appropriate prefixes
     */
    public String parseArguments(String args) {
        args = args.replaceAll(STARTTIME_VARIANTS_REGEX, "s/");
        args = args.replaceAll(ENDTIME_VARIANTS_REGEX, "e/");
        return args;
    }

}
