package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskManager;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask task1, task2, task3, task4, task5, task6, task7, task8, task9;

    public TypicalTestTasks() {
        try {
            task1 = new TaskBuilder().withName("Study for Midterm").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task2 = new TaskBuilder().withName("Study for Midterm2").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task3 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task4 = new TaskBuilder().withName("Study for Midterm4").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task5 = new TaskBuilder().withName("Study for Midterm5").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task6 = new TaskBuilder().withName("Study for Midterm6").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task7 = new TaskBuilder().withName("Study for Midterm7").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();

            // Manually added
            task8 = new TaskBuilder().withName("Study for Midterm8").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task9 = new TaskBuilder().withName("Study for Midterm9").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("School").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {
        for (TestTask task : new TypicalTestTasks().getTypicalTasks()) {
            try {
                ab.addTask(new Task(task));
            } catch (UniqueTaskList.DuplicatetaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{task1, task2, task3, task4, task5, task6, task7};
    }

    public TaskManager getTypicalTaskManager() {
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}
