package seedu.taskManager.logic.commands;

import java.util.List;

import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.logic.commands.exceptions.CommandException;
import seedu.taskManager.model.Task.EndTime;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.model.Task.StartTime;
import seedu.taskManager.model.Task.Status;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.Task.UniqueTaskList;

//@@author A0140072X
/**
 * Archive a task identified using it's last displayed index from the task
 * manager.
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager.";
    public static final String MESSAGE_ILLEGAL_VALUE = "Illegal value detected.";
    public static final String MESSAGE_USAGE = COMMAND_WORD

            + ": Archive the item identified by the index number used in last task listing.\n"
            + "Parameters:  INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_TASK_SUCCESS = "Archived Item: %1$s";


    public final int targetIndex;

    public ArchiveCommand(int targetIndex) {
        this.targetIndex = targetIndex - 1;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();
        if (targetIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        ReadOnlyTask taskToArchive = lastShownList.get(targetIndex);

        try {
            Task updatedTask = new Task(taskToArchive.getName(), taskToArchive.getDescription(),
                    taskToArchive.getStartTime(), taskToArchive.getEndTime(), taskToArchive.getId(),
                    taskToArchive.getPriority(), new Status("done"), taskToArchive.getRecurPeriod(),
                    taskToArchive.getRecurEndDate(), taskToArchive.getTags());
            Task toAdd = new Task(taskToArchive.getName(), taskToArchive.getDescription(), taskToArchive.getStartTime(),
                    taskToArchive.getEndTime(), taskToArchive.getId(), taskToArchive.getPriority(),
                    new Status("undone"), taskToArchive.getRecurPeriod(), taskToArchive.getRecurEndDate(),
                    taskToArchive.getTags());
            model.updateTask(targetIndex, updatedTask);

            // @@author A0139375W
            if (model.isRecurringTask(toAdd)) {
                EndTime newEndTime = new EndTime(
                        taskToArchive.getRecurPeriod().updatedDate(taskToArchive.getEndTime().toString()));
                if (model.hasPassedEndDate(toAdd, newEndTime)) {
                    if (toAdd.getStartTime().hasStartTime()) {
                        StartTime newStartTime = new StartTime(
                                taskToArchive.getRecurPeriod().updatedDate(taskToArchive.getStartTime().toString()));
                        toAdd.setStartTime(newStartTime);
                    }
                    toAdd.setEndTime(newEndTime);

                    model.addTask(toAdd);
                    // @@author
                }
            }
        }

        catch (UniqueTaskList.DuplicatetaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        } catch (IllegalValueException ive) {
            throw new CommandException(MESSAGE_ILLEGAL_VALUE);
        }
        model.updateFilteredListToShowAll();

        return new CommandResult(String.format(MESSAGE_ARCHIVE_TASK_SUCCESS, taskToArchive));

    }

}
