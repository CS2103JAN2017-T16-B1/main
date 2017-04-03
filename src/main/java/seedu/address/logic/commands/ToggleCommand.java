package seedu.address.logic.commands;
import org.junit.Assert;

//@@author A0139509X
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Task.TaskStringReference;

public class ToggleCommand extends Command{

	public static final String COMMAND_WORD = "toggle";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD;
	
	public static final String TOGGLE_MESSAGE = "Toggled to list ";

	public static final String TOGGLE_FAILED_MESSAGE = "Unable to execute toggle due to invalid current toggle status";

	private String currentToggleStatus;
	
	@Override
	public CommandResult execute() throws CommandException {
		
		currentToggleStatus = model.getCurrentToggleStatus();

		if(currentToggleStatus.equals(TaskStringReference.SHOWING_ALL)){
			model.updateFilteredTaskListByEvent();
		}
		else if(currentToggleStatus.equals(TaskStringReference.SHOWING_EVENT)){
			model.updateFilteredTaskListByTask();
		}
		else if(currentToggleStatus.equals(TaskStringReference.SHOWING_TASK)){
			model.updateFilteredTaskListByFloatingTask();
		}
		else if(currentToggleStatus.equals(TaskStringReference.SHOWING_FLOATING_TASK)){	
			model.updateFilteredListToShowAll();
		}
		else if(currentToggleStatus.equals(TaskStringReference.SHOWING_SPECIAL)){
			model.updateFilteredListToShowAll();
			return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
		}
		else {
			Assert.fail(TOGGLE_FAILED_MESSAGE);
			
		}
		
		return new CommandResult(TOGGLE_MESSAGE + model.getCurrentToggleStatus() + "\n" + 
				getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
	}

}
