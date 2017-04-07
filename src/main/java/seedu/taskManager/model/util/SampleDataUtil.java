package seedu.taskManager.model.util;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.ReadOnlyTaskManager;
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
import seedu.taskManager.model.Task.UniqueTaskList.DuplicatetaskException;
import seedu.taskManager.model.TaskManager;
import seedu.taskManager.model.tag.UniqueTagList;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Name("buy milk"), new Description("87438807"), new StartTime(""),
                        new EndTime("2017-03-04-1000"), new ID("1"), new Priority("l"), new Status("undone"),
                        new RecurPeriod(""), new RecurEndDate (""), new UniqueTagList("friends")),
                new Task(new Name("buy house"), new Description("87438807"), new StartTime(""),
                        new EndTime("2017-03-04-1000"), new ID("1"), new Priority("h"), new Status("undone"),
                        new RecurPeriod(""), new RecurEndDate (""), new UniqueTagList("friends")),
                new Task(new Name("study"), new Description("87438807"), new StartTime(""),
                        new EndTime("2017-03-04-1000"), new ID("1"), new Priority("m"), new Status("undone"),
                        new RecurPeriod(""), new RecurEndDate (""), new UniqueTagList("friends"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        try {
            TaskManager sampleAB = new TaskManager();

            for (Task sampleTask : getSampleTasks()) {
                sampleAB.addTask(sampleTask);

            }
            return sampleAB;
        } catch (DuplicatetaskException e) {
            throw new AssertionError("sample data cannot contain duplicate tasks", e);
        }
    }
}
