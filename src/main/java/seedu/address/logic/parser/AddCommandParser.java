package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CliSyntax.*;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.NoSuchElementException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {

        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_DESCRIPTION, PREFIX_STARTTIME, PREFIX_ENDTIME,  PREFIX_PRIORITY,
                		 PREFIX_STATUS, PREFIX_TAG);

        argsTokenizer.tokenize(args);
        String taskType = argsTokenizer.getCommandType(args);
 
        try {
                return new AddCommand(
                        argsTokenizer.getPreamble().get(),
                        argsTokenizer.getValue(PREFIX_DESCRIPTION).orElse(null),
                        argsTokenizer.getValue(PREFIX_STARTTIME).orElse(null),
                        argsTokenizer.getValue(PREFIX_ENDTIME).orElse(null),
                        ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_TAG))
                );
                
        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

}
