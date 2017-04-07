package seedu.taskManager.logic.parser;

import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.taskManager.logic.commands.ArchiveCommand;
import seedu.taskManager.logic.commands.Command;
import seedu.taskManager.logic.commands.IncorrectCommand;
//@@author A0140072X
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
