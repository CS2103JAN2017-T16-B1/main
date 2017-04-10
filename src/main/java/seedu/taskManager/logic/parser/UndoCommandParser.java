package seedu.taskManager.logic.parser;

import seedu.taskManager.logic.commands.Command;
import seedu.taskManager.logic.commands.UndoCommand;

/**
 * Parses input arguments and creates a new UndoCommand object
 */
public class UndoCommandParser {

    /**
     * Returns an UndoCommand object for execution.
     */
    public Command parse() {

        return new UndoCommand();
    }

}
