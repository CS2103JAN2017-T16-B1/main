package seedu.taskManager.logic.commands;

import java.util.Set;

import org.junit.Assert;

import seedu.taskManager.model.Task.Priority;
import seedu.taskManager.model.Task.Status;
import seedu.taskManager.model.Task.TaskStringReference;

/**
 * Finds and lists all tasks in task manager whose name contains any of the
 * argument keywords. Keyword matching is case sensitive.
 */
public class FindCommand extends Command {
    //@@author A0139509X
    public static final String COMMAND_WORD = "find";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks whose names/description/tags contain any of "

            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "When prefix (#) is used, will display all task with the associated status or priority level.\n"
            + "When prefix (@) is used, will display all archived task with the associated names/descriptions/tags.\n"
            + "(Usage 1) Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " study meeting friend\n"

            + "(Usage 2) Parameters: #KEYWORD \n" + "Example: "
            + COMMAND_WORD + " #h\n"

            + "(Usage 3) Parameters: @KEYWORD \n" + "Example: " + COMMAND_WORD + " @study\n";

    private final Set<String> keywords;

    private final Status status;

    private final Priority priority;

    private final String archive;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.status = null;
        this.priority = null;
        this.archive = null;
    }

    public FindCommand(String keyword) {
        this.keywords = null;
        this.status = null;
        this.priority = null;
        this.archive = keyword;
    }

    public FindCommand(Status status) {
        this.keywords = null;
        this.status = status;
        this.priority = null;
        this.archive = null;
    }

    public FindCommand(Priority priority) {
        this.keywords = null;
        this.status = null;
        this.priority = priority;
        this.archive = null;
    }

    @Override
    public CommandResult execute() {
        if (this.keywords != null) {
            updateByKeywords();
        } else if (this.status != null) {
            updateByStatusLevel(status);
        } else if (this.priority != null) {
            updateByPriorityLevel(priority);
        } else if (this.archive != null) {
            updateByArchivedKeyword();
        }
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

    public void updateByArchivedKeyword() {
        model.updateArchivedFilteredTaskListByKeyword(archive);
    }

    public void updateByKeywords() {
        model.updateFilteredTaskListByKeywords(keywords);
    }

    private void updateByStatusLevel(Status status) {
        if (this.status.toString().equals(TaskStringReference.STATUS_DONE)) {
            model.updateFilteredTaskListByDoneStatus();
        } else if (this.status.toString().equals(TaskStringReference.STATUS_UNDONE)) {
            model.updateFilteredTaskListByUnDoneStatus();
        } else {
            Assert.fail("unable to execute FindCommand due to incorrect Status");
        }
    }

    private void updateByPriorityLevel(Priority priority) {
        if (this.priority.toString().equals(TaskStringReference.PRIORITY_HIGH)) {
            model.updateFilteredTaskListByHighPriority();
        } else if (this.priority.toString().equals(TaskStringReference.PRIORITY_MEDIUM)) {
            model.updateFilteredTaskListByMediumPriority();
        } else if (this.priority.toString().equals(TaskStringReference.PRIORITY_LOW)) {
            model.updateFilteredTaskListByLowPriority();
        } else {
            Assert.fail("unable to execute FindCommand due to incorrect Priority");
        }
    }


}
