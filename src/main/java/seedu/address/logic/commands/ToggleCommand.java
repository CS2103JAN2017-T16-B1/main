package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

public class ToggleCommand extends Command{

	public static final String COMMAND_WORD = "toggle";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD;

	private String currentToggleStatus;
	
	@Override
	public CommandResult execute() throws CommandException {
		
		currentToggleStatus = model.getCurrentToggleStatus();

		if(currentToggleStatus.equals("ALL")){
			model.updateFilteredTaskListByEvent();
		}
		else if(currentToggleStatus.equals("EVENT")){
			model.updateFilteredTaskListByTask();
		}
		else if(currentToggleStatus.equals("TASK")){
			model.updateFilteredTaskListByFloatingTask();
		}
		else if(currentToggleStatus.equals("FLOATING_TASK")){	
			model.updateFilteredListToShowAll();
		}
		else {	
			model.updateFilteredListToShowAll();
		}
		
		return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
	}

}
