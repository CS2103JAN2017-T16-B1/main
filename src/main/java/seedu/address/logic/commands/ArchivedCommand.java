package seedu.address.logic.commands;


//@@author A0140072X
/**
 * Lists all archived tasks in task manager.
 *
 */
public class ArchivedCommand extends Command {

    public static final String COMMAND_WORD = "archived";



    public ArchivedCommand() {
    }


    @Override
    public CommandResult execute() {

        model.updateFilteredTaskListByArchived();

        return new CommandResult(getMessageForArchivedTaskListShownSummary(model.getFilteredTaskList().size()));
    }



}
