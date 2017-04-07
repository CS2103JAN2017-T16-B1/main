package seedu.taskManager.logic.commands;

import java.util.logging.Logger;

import org.junit.Assert;

import seedu.taskManager.commons.core.LogsCenter;
import seedu.taskManager.logic.commands.exceptions.CommandException;
import seedu.taskManager.model.Task.TaskStringReference;
import seedu.taskManager.ui.CommandBox;

/**
 * Toggles the view that is shown to user, by using the "TAB" keyword
 * Can also be executed by typing "toggle" in {@link CommandBox}
 * Toggles in a loop to show all task, events, task and floating task correspondingly
 */
public class ToggleCommand extends Command {
    private static final Logger logger = LogsCenter.getLogger(ToggleCommand.class);

    public static final String COMMAND_WORD = "toggle";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String TOGGLE_MESSAGE = "Toggled to list ";

    public static final String TOGGLE_FAILED_MESSAGE = "Unable to execute toggle due to invalid current toggle status";

    private String currentToggleStatus;

    @Override
    public CommandResult execute() throws CommandException {

        currentToggleStatus = model.getCurrentToggleStatus();

        if (currentToggleStatus.equals(TaskStringReference.SHOWING_ALL)) {
            model.updateFilteredTaskListByEvent();
            logger.info("toggle function executed, updated list to show events");
        }
        else if (currentToggleStatus.equals(TaskStringReference.SHOWING_EVENT)) {
            model.updateFilteredTaskListByTask();
            logger.info("toggle function executed, updated list to show task");
        }
        else if (currentToggleStatus.equals(TaskStringReference.SHOWING_TASK)) {
            model.updateFilteredTaskListByFloatingTask();
            logger.info("toggle function executed, updated list to show floating task");
        }
        else if (currentToggleStatus.equals(TaskStringReference.SHOWING_FLOATING_TASK)) {
            model.updateFilteredListToShowAll();
            logger.info("toggle function executed, updated list to show all");
        }
        else if (currentToggleStatus.equals(TaskStringReference.SHOWING_SPECIAL)) {
            model.updateFilteredListToShowAll();
            logger.info("toggle function executed while not in list all view, updated list to show all");
            return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
        }
        else {
            Assert.fail(TOGGLE_FAILED_MESSAGE);
        }

        return new CommandResult(TOGGLE_MESSAGE + model.getCurrentToggleStatus() + "\n" +
        getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

}
