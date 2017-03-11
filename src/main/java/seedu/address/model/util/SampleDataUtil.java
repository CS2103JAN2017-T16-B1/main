package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskManager;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.UniqueTaskList.DuplicatetaskException;
import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.tag.UniqueTagList;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Name("buy milk"),new Description("for cereal"),new StartTime("2017-08-12-1000"),
                	new EndTime(""), new ID("1"), new Priority("m"), new Status("undone"), new UniqueTagList("groceries")),
                new Task(new Name("Sell Milk"),new Description("for money"),new StartTime("2017-08-12-1200"),
                    new EndTime(""), new ID("2"), new Priority("h"), new Status("undone"), new UniqueTagList("money")),
                new Task(new Name("go to gym"),new Description("for health"),new StartTime("2017-08-12-1400"),
                    new EndTime(""), new ID("3"), new Priority("h"), new Status("undone"), new UniqueTagList("fitness")),
                new Task(new Name("eat dinner"),new Description("moms birthday"),new StartTime("2017-08-12-1600"),
                    new EndTime(""), new ID("4"), new Priority("h"), new Status("undone"), new UniqueTagList("family"))
                };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        try {
            TaskManager sampleAB = new TaskManager();
            for (Task samplePerson : getSampleTasks()) {
                sampleAB.addTask(samplePerson);
            }
            return sampleAB;
        } catch (DuplicatetaskException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }
}
