package seedu.address.logic.parser;



import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UndoCommand;

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
