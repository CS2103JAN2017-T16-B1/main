package seedu.address.testutil;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.TaskManager;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.Task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, task11, task12,
                     task13, task14, task15, task16, task17, task18;

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
                    .withRecurEndDate("2017-04-30-2100").build();
            task12 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-19-2100")
                    .withEndTime("2017-04-25-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("9")
                    .withRecurEndDate("2017-04-30-2100").build();
            task13 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-12-2100")
                    .withEndTime("2017-04-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("weekly")
                    .withRecurEndDate("").build();
            task14 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-19-2100")
                    .withEndTime("2017-04-23-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("weekly")
                    .withRecurEndDate("").build();
            task15 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-12-2100")
                    .withEndTime("2017-04-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("monthly")
                    .withRecurEndDate("").build();
            task16 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-04-12-2100")
                    .withEndTime("2017-05-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("monthly")
                    .withRecurEndDate("").build();
            task17 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2017-03-12-2100")
                    .withEndTime("2017-04-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("yearly")
                    .withRecurEndDate("").build();
            task18 = new TaskBuilder().withName("Study for Midterm3").withStartTime("2018-03-12-2100")
                    .withEndTime("2018-04-16-2100").withTags("school").withStatus("undone")
                    .withPriority("h").withID("1").withDescription("").withRecurPeriod("yearly")
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
