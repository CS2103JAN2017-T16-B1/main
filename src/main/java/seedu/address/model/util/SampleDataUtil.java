package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskManager;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;

import seedu.address.model.Task.Priority;
import seedu.address.model.Task.RecurEndDate;
import seedu.address.model.Task.RecurPeriod;
import seedu.address.model.Task.Status;

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


            		new Task(new Name("buy milk"), new Description("87438807"), new StartTime(""),
                            new EndTime("2017-03-04-1000"), new ID("1"), new Priority("l"), new Status("undone"),
                            new RecurPeriod(""), new RecurEndDate (""), new UniqueTagList("friends")),
            		new Task(new Name("buy house"), new Description("87438807"), new StartTime(""),
                            new EndTime("2017-03-04-1000"), new ID("1"), new Priority("h"), new Status("undone"), 
                            new RecurPeriod(""), new RecurEndDate (""), new UniqueTagList("friends")),
            		new Task(new Name("study"), new Description("87438807"), new StartTime(""),
                            new EndTime("2017-03-04-1000"), new ID("1"), new Priority("m"), new Status("undone"),
                            new RecurPeriod(""), new RecurEndDate (""),new UniqueTagList("friends")),
            		new Task(new Name("Buy more milk"), new Description("call boss at 87438807"), new StartTime("2016-03-04-1500"),
                            new EndTime("2017-03-04-1600"), new ID("3"), new Priority("h"), new Status("undone"),
                            new RecurPeriod(""), new RecurEndDate (""),new UniqueTagList("friends")),
            		new Task(new Name("review CS code"), new Description("after the tutorial"), new StartTime(""),
                            new EndTime("2017-03-04-1000"), new ID("1"), new Priority("h"), new Status("undone"),
                            new RecurPeriod(""), new RecurEndDate (""),new UniqueTagList("friends")),
            		new Task(new Name("skip class"), new Description("maybe it is for the better"), new StartTime(""),
                            new EndTime("2017-03-04-1000"), new ID("1"), new Priority("h"), new Status("done"),
                            new RecurPeriod(""), new RecurEndDate (""),new UniqueTagList("friends","awesome"))
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
