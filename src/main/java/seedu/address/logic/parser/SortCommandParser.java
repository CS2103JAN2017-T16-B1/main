package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SortCommand;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SortCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     */
    public Command parse(String args) {
        return new SortCommand(args);
    }

}
