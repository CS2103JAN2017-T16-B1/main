package seedu.taskManager.logic.commands;

import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.commons.core.UnmodifiableObservableList;
import seedu.taskManager.logic.commands.exceptions.CommandException;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.model.Task.UniqueTaskList.TaskNotFoundException;

/**
 * Deletes a task identified using it's last displayed index from the task manager.
 */
//@@author A0139375W
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD

            + ": Deletes the item identified by the index number used in last task listing.\n"
            + "Parameters:  INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted:\n %1$s";

    public final int targetIndex;


    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute() throws CommandException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToDelete = lastShownList.get(targetIndex - 1);

        try {

            model.deleteTask(taskToDelete);


        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));

    }

}
