package seedu.address.logic.commands;

import java.util.Set;

import org.junit.Assert;

import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Status;
/**
 * Finds and lists all tasks in task manager whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final String HIGH_PRIORITY = "h";
    private static final String MEDIUM_PRIORITY = "m";
    private static final String LOW_PRIORITY = "l";
	private static final Object DONE = "done";
	private static final Object UNDONE = "undone";
	
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "When prefix (#) is used, will display all task with the associated status or priority level.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " study meeting friend\n"
            + "Parameters: #KEYWORD \n"
            + "Example: " + COMMAND_WORD + " #h\n";

    private final Set<String> keywords;
    
    private final Status status;
    
    private final Priority priority;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.status = null;
        this.priority = null;
    }

    public FindCommand(Status status) {
        this.keywords = null;
        this.status = status;
        this.priority = null;
    }
    
    public FindCommand(Priority priority) {
        this.keywords = null;
        this.status = null;
        this.priority = priority;
    }
    
    @Override
    public CommandResult execute() {
    	if(this.keywords!=null){
    		model.updateFilteredTaskListByKeywords(keywords);
    	}
    	else if(this.status!=null){
    		updateByStatusLevel(status);
    	}
    	else if(this.priority != null){
    		updateByPriorityLevel(priority);
    	}
    	return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

	private void updateByStatusLevel(Status status) {
		if(this.status.toString().equals(DONE)){
			model.updateFilteredTaskListByDoneStatus();
		}
		else if (this.status.toString().equals(UNDONE)){
			model.updateFilteredTaskListByUnDoneStatus();
		}
		else {
			Assert.fail("unable to execute FindCommand due to incorrect Status");
		}
	}

	private void updateByPriorityLevel(Priority priority) {
		if(this.priority.toString().equals(HIGH_PRIORITY)) {
            model.updateFilteredTaskListByHighPriority();
        } else if(this.priority.toString().equals(MEDIUM_PRIORITY)) {
            model.updateFilteredTaskListByMediumPriority();
        } else if(this.priority.toString().equals(LOW_PRIORITY)) {
            model.updateFilteredTaskListByLowPriority();
        } else {
            Assert.fail("unable to execute FindCommand due to incorrect Priority");
        }
	}

}
