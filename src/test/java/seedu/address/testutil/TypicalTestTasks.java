package seedu.address.testutil;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.TaskManager;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.Task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, task11;

    public TypicalTestTasks() {
        try {
            task1 = new TaskBuilder().withName("Study for Midterm").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-05-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task2 = new TaskBuilder().withName("Study for Midterm2").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-06-2100").withTags("school").withStatus("undone")
                    .withPriority("l").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task3 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-07-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task4 = new TaskBuilder().withName("Study for Midterm4").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-08-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task5 = new TaskBuilder().withName("Study for Midterm5").withStartTime("2009-03-03-2100")
                    .withEndTime("2010-04-09-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task6 = new TaskBuilder().withName("ABC Study for Midterm6").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-10-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task7 = new TaskBuilder().withName("Study for Midterm7").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-11-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();

            // Manually added
            task8 = new TaskBuilder().withName("Study for Midterm8").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task9 = new TaskBuilder().withName("Study for Midterm9").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("school").withStatus("undone")
                    .withPriority("m").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task10 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-03-2100")
                    .withEndTime("2017-04-04-2100").withTags("school").withStatus("done")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("")
                    .withRecurEndDate("").build();
            task11 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-12-2100")
                    .withEndTime("2017-04-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("9")
                    .withRecurEndDate("").build();

        } catch (IllegalValueException e) {
            e.printStackTrace();

            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) throws IllegalValueException {
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

    public TaskManager getTypicalTaskManager() throws IllegalValueException {
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}
