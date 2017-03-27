package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.logic.commands.exceptions.CommandException;


/**
 * Deletes a task identified using it's last displayed index from the task manager.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD

            + ": Sorts the tasks in last task listing.\n"
            + "Parameters:  None\n"
            + "Example: " + COMMAND_WORD ;

    public static final String MESSAGE_SORT_PERSON_SUCCESS = "Sorted";
    
    //public final char targetList;

    public SortCommand() {
    }


    @Override
    public CommandResult execute() throws CommandException {
    	model.sortTasks();
        return new CommandResult(MESSAGE_SORT_PERSON_SUCCESS);

    }
    
  
    
    

}
