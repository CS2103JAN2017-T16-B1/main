package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.UniqueTaskList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Adds a task to the task manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task manager. "
            + "Parameters: add event EVENT_NAME st/START_TIME et/END_TIME [des/DESCRIPTION] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD

            + " Midterms st/2017-3-1-1300 et/2017-3-1-1500 des/CS2103 t/school";


    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the task manager";
    public static String id = "1";
    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    //Add Event
    public AddCommand(String name, String description, String startTime, String endTime, Set<String> tags)

            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),

                new Description(description),
                new StartTime(startTime),
                new EndTime(endTime),
                new ID(id),
                //new ID(id++),
                new Priority("m"),
                new Status("undone"),
                new UniqueTagList(tagSet)
        );
        incrementID();
    }
  //Add Task
    public AddCommand(String name, String description, String endTime, Set<String> tags)

            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),
                new Description(description),
                new StartTime(null),
                new EndTime(endTime),
                new ID(id),
                new Priority("m"),
                new Status("undone"),
                new UniqueTagList(tagSet)
        );
        incrementID();
    }
  //Add Floating
    public AddCommand(String name, String description,  Set<String> tags)

            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),

                new Description(description),
                new StartTime(null),
                new EndTime(null),
                new ID(id),
                new Priority("m"),
                new Status("undone"),
                new UniqueTagList(tagSet)
        );
        incrementID();
    }
    private void incrementID() {
        Integer intid = Integer.parseInt(id);
        intid++;
        id = intid.toString();
    }
    /*
    public AddCommand(String name, String description, String startTime, String endTime,
            String id, String priority, String status, Set<String> tags)

            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),

                new Description(description),
                new StartTime(startTime),
                new EndTime(endTime),
                new ID(id),

                new Priority(priority),
                new Status(status),
                new UniqueTagList(tagSet)
        );
    }*/

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicatetaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

    }

}
