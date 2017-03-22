package seedu.address.logic.parser;



import seedu.address.logic.commands.ArchivedCommand;
import seedu.address.logic.commands.Command;
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
