package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.UniqueTaskList.TaskNotFoundException;

/**
 * Deletes a person identified using it's last displayed index from the task manager.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the type of item and index number used in last task listing.\n"
            + "Parameters: TYPE (must be 'e' for event,'t' for task or 'f' for floating task), INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " e1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Item: %1$s";

    public final int targetIndex;
    
    //public final char targetList;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
        //this.targetList = targetList;
    }


    @Override
    public CommandResult execute() throws CommandException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask itemToDelete = lastShownList.get(targetIndex - 1);

        try {

            model.deleteTask(itemToDelete);
        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, itemToDelete));
    }

}
