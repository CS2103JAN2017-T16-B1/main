package seedu.taskManager.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.logic.commands.exceptions.CommandException;
import seedu.taskManager.model.Task.Description;
import seedu.taskManager.model.Task.EndTime;
import seedu.taskManager.model.Task.ID;
import seedu.taskManager.model.Task.Name;
import seedu.taskManager.model.Task.Priority;
import seedu.taskManager.model.Task.RecurEndDate;
import seedu.taskManager.model.Task.RecurPeriod;
import seedu.taskManager.model.Task.StartTime;
import seedu.taskManager.model.Task.Status;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.Task.UniqueTaskList;
import seedu.taskManager.model.tag.Tag;
import seedu.taskManager.model.tag.UniqueTagList;
//@@author A0140072X
/**
 * Adds a task to the task manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task manager. "
            + "Parameters: add EVENT_NAME [s/START_TIME] [e/END_TIME or DEADLINE] [d/DESCRIPTION] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD

            + " Midterms s/2017-03-01-1300 e/2017-03-01-1500 d/CS2103 t/school";



    public static final String MESSAGE_SUCCESS = " Added:\n %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    public static String id = "1";
    private Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    //Add Event
    public AddCommand(String name, String description, String startTime,
            String endTime, String recurPeriod, String recurEndDate, String priority, Set<String> tags)
                    throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName.toLowerCase()));
        }
        this.toAdd = new Task(
                new Name(name),

                new Description(description),
                new StartTime(startTime),
                new EndTime(endTime),
                new ID(id),
                new Priority(priority),
                new Status("undone"),
                new RecurPeriod(recurPeriod),
                new RecurEndDate(recurEndDate),
                new UniqueTagList(tagSet)
                );
        incrementID();


    }
    private void incrementID() {
        Integer intid = Integer.parseInt(id);
        intid++;
        id = intid.toString();
    }


    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicatetaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
