package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.RecurPeriod;
import seedu.address.model.Task.RecurEndDate;
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
    		String endTime, String recurPeriod, String recurEndDate,String priority, Set<String> tags)

    		
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
                new Priority(priority),
                new Status("undone"),
                new RecurPeriod(recurPeriod),
                new RecurEndDate(recurEndDate),
                new UniqueTagList(tagSet)
        );
        incrementID();
        
      /*  while (this.toAdd.getRecurEndDate().hasPassedEndDate(endTime)){
        	startTime = this.toAdd.getRecurPeriod().updatedDate(startTime);
        	
        	this.toAdd = new Task(
                    new Name(name),
                    new Description(description),
                    new StartTime(startTime),
                    new EndTime(endTime),
                    new ID(id),
                    //new ID(id++),
                    new Priority("m"),
                    new Status("undone"),
                    new RecurPeriod(recurPeriod),
                    new RecurEndDate(recurEndDate),
                    new UniqueTagList(tagSet)
            );
            incrementID();
        }*/
    }

  /*Add Task
    public AddCommand(String name, String description, String endTime, Set<String> tags)

            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),
         		new Description(description),
                new StartTime(""),
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
                new StartTime(""),
                new EndTime(""),
                new ID(id),
                new Priority("m"),
                new Status("undone"),
                new UniqueTagList(tagSet)
        );
        incrementID();
    }*/
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
            model.sortTasksByEndTime();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicatetaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
