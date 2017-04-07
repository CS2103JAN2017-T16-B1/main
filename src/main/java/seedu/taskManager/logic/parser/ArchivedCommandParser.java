package seedu.taskManager.logic.parser;

import seedu.taskManager.logic.commands.ArchivedCommand;
import seedu.taskManager.logic.commands.Command;
//@@author A0140072X
/**
 * Parses input arguments and creates a new ArchivedCommand object
 */
public class ArchivedCommandParser {

    /**
     * Returns an ArchivedCommand object for execution.
     */
    public Command parse() {

        return new ArchivedCommand();
    }

}
