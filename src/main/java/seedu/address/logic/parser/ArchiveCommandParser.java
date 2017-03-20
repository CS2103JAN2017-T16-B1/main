package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new ArchiveCommand object
 */
public class ArchiveCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveCommand
     * and returns an ArchiveCommand object for execution.
     */
    public Command parse(String args) {

        Optional<Integer> index = ParserUtil.parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE));
        }

        return new ArchiveCommand(index.get());
    }

}
