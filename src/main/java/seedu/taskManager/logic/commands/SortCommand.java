package seedu.taskManager.logic.commands;

import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.exceptions.CommandException;

//@@author A0138998B
/**
 * Sorts the entire task list in the task manager and displays the last shown list in a sorted format
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD

            + ": Sorts the tasks in last task listing.\n"
            + "Parameters:  PARAMETER (name/duedate/priority)"
            + " Example: " + COMMAND_WORD + " duedate";

    public static final String MESSAGE_SORT_PERSON_SUCCESS = "Sorted by %1$s ";

    public static final String VALID_PARAMETER = "^(name|duedate|priority)";

    public final String parameter;

    /**
     * Creates a SortCommand using the String parameter.
     * Parameters can take on values such as name, duedate and priority
    */
    public SortCommand(String parameter) {

        this.parameter = parameter.trim();

    }

    /**
     * Ensures that parameter variable is a valid one before executing a sort defined by the parameter
    */
    @Override
    public CommandResult execute() throws CommandException {


        if (!parameter.matches(VALID_PARAMETER)) {

            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        }

        switch(parameter) {

        case("name"):
            model.sortTasksByName();
            break;

        case("duedate"):
            model.sortTasksByEndTime();
            break;

        case("priority"):
            model.sortTasksByPriority();
            break;

        }

        return new CommandResult(
                String.format(MESSAGE_SORT_PERSON_SUCCESS, parameter));

    }





}
