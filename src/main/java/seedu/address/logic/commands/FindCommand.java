package seedu.address.logic.commands;

import java.util.Set;

import org.junit.Assert;

import seedu.address.model.Task.Priority;
import seedu.address.model.tag.Tag;
/**
 * Finds and lists all tasks in task manager whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final String HIGH_PRIORITY = "h";
    private static final String MEDIUM_PRIORITY = "m";
    private static final String LOW_PRIORITY = "l";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;
    
    private final Tag tag;
    
    private final Priority priority;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.tag = null;
        this.priority = null;
    }

    public FindCommand(Tag tag) {
        this.keywords = null;
        this.tag = tag;
        this.priority = null;
    }
    
    public FindCommand(Priority priority) {
        this.keywords = null;
        this.tag = null;
        this.priority = priority;
    }
    
    @Override
    public CommandResult execute() {
    	if(this.keywords!=null){
    		model.updateFilteredTaskListByKeywords(keywords);
    	}
    	else if(this.tag!=null){
    		model.updateFilteredTaskListByTag(tag);
    	}
    	else if(this.priority != null){
    		updateByPriorityLevel(priority);
    	}
    	return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

	private void updateByPriorityLevel(Priority priority) {
		if(this.priority.toString() == HIGH_PRIORITY) {
            model.updateFilteredTaskListByHighPriority();
        } else if(this.priority.toString() == MEDIUM_PRIORITY) {
            model.updateFilteredTaskListByMediumPriority();
        } else if(this.priority.toString() == LOW_PRIORITY) {
            model.updateFilteredTaskListByLowPriority();
        } else {
            Assert.fail("unable to execute FindCommand due to incorrect Priority");
        }
	}

}
