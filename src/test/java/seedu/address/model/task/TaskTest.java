package seedu.address.model.task;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.TypicalTestTasks;
import seedu.taskManager.commons.exceptions.IllegalValueException;
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
import seedu.taskManager.model.tag.UniqueTagList;

//@@author A0138998B
public class TaskTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Name name;
    private Description description;
    private StartTime startTime;
    private EndTime endTime;
    private ID id;
    private Priority priority;
    private Status status;
    private RecurPeriod recurPeriod;
    private RecurEndDate recurEndDate;
    private UniqueTagList tags;

    @Before
    public void setUp() throws IllegalValueException {
        name = new Name("Study for Midterm");
        description = new Description("");
        startTime = new StartTime("2017-03-03-2100");
        endTime = new EndTime("2017-04-05-2100");;
        id = new ID("1");
        priority = new Priority("m");
        status = new Status("undone");
        recurPeriod = new RecurPeriod("");
        recurEndDate = new RecurEndDate("");
        tags = new UniqueTagList("school");
    }

    @Test
    public void constructor_validTaskParameter_strictParameters() throws IllegalValueException {

        Task testTask = new Task(name, description, startTime, endTime, id,
                priority, status, recurPeriod, recurEndDate, tags);

        TypicalTestTasks expectedTask = new TypicalTestTasks();

        assertEquals(testTask, new Task(expectedTask.task1));

    }

    @Test
    public void constructor_validTaskParameter_relaxedDates() throws IllegalValueException {

        StartTime relaxedStartTime = new StartTime("3rd march 2017 9pm");

        EndTime relaxedEndTime = new EndTime("5th april 2017 9pm");

        Task testTask = new Task(name, description, relaxedStartTime, relaxedEndTime, id,
                priority, status, recurPeriod, recurEndDate, tags);

        TypicalTestTasks expectedTask = new TypicalTestTasks();

        assertEquals(testTask, new Task(expectedTask.task1));

    }

    @Test
    public void constructor_invalidTaskParameter_invalidDates() throws IllegalValueException {

        StartTime relaxedStartTime = new StartTime("3rd march 2018 9pm");

        EndTime relaxedEndTime = new EndTime("4th april 2017 9pm");

        thrown.expect(IllegalValueException.class);

        Task testTask = new Task(name, description, relaxedStartTime, relaxedEndTime, id,
                priority, status, recurPeriod, recurEndDate, tags);
    }


}
