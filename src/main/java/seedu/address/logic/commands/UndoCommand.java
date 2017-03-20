package seedu.address.logic.commands;

import seedu.address.model.TaskManager;

/**
 * Clears the address book.
 */
public class UndoCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Undo Successful!";
    public static final String MESSAGE_FAILED = "No Command to Undo!";
    public static final String COMMAND_WORD = "undo";

    @Override
    public CommandResult execute() {
        assert model != null;
        if(model.undoTask()) {
            return new CommandResult(MESSAGE_SUCCESS);
        }
        else {
            return new CommandResult(MESSAGE_FAILED);
        } 
    }
}
