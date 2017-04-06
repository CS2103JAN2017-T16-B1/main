package seedu.address.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.Task;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalTestTasks;

public class TaskManagerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final TaskManager taskManager = new TaskManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskManager.getTaskList());
        assertEquals(Collections.emptyList(), taskManager.getTagList());
    }

    @Test
    public void resetData_null_throwsAssertionError() {
        thrown.expect(AssertionError.class);
        taskManager.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyTaskManager_replacesData() throws IllegalValueException {
        TaskManager newData = new TypicalTestTasks().getTypicalTaskManager();
        taskManager.resetData(newData);
        assertEquals(newData, taskManager);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsAssertionError() throws IllegalValueException {
        TypicalTestTasks td = new TypicalTestTasks();
        // Repeat td.alice twice
        List<Task> newTasks = Arrays.asList(new Task(td.task1), new Task(td.task1));
        List<Tag> newTags = td.task1.getTags().asObservableList();
        TaskManagerStub newData = new TaskManagerStub(newTasks, newTags);

        thrown.expect(AssertionError.class);
        taskManager.resetData(newData);
    }

    @Test
    public void resetData_withDuplicateTags_throwsAssertionError() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();
        List<ReadOnlyTask> newTasks = typicalTaskManager.getTaskList();
        List<Tag> newTags = new ArrayList<>(typicalTaskManager.getTagList());
        // Repeat the first tag twice
        newTags.add(newTags.get(0));
        TaskManagerStub newData = new TaskManagerStub(newTasks, newTags);

        thrown.expect(AssertionError.class);
        taskManager.resetData(newData);
    }

    //@@author A0138998B
    @Test
    public void sortTasks_byEndTime() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();

        typicalTaskManager.sortTasksByEndTime();

        List<ReadOnlyTask> Tasks = typicalTaskManager.getTaskList();

        assertEquals("Study for Midterm5", Tasks.get(0).getName().fullName);
    }

    @Test
    public void sortTasks_byName() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();

        typicalTaskManager.sortTasksByName();

        List<ReadOnlyTask> Tasks = typicalTaskManager.getTaskList();

        assertEquals("ABC Study for Midterm6", Tasks.get(0).getName().fullName);
    }

    @Test
    public void sortTasks_byPriority() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();

        typicalTaskManager.sortTaskByPriority();

        List<ReadOnlyTask> Tasks = typicalTaskManager.getTaskList();
        //top of the list
        assertEquals("Study for Midterm3", Tasks.get(0).getName().fullName);

        //bottom of the list
        assertEquals("Study for Midterm2", Tasks.get(Tasks.size() - 1).getName().fullName);
    }

    @Test
    public void updateTask() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();


        TypicalTestTasks task = new TypicalTestTasks();

        typicalTaskManager.updateTask(0, task.task8);

        List<ReadOnlyTask> Tasks = typicalTaskManager.getTaskList();

        assertEquals(new Task(task.task8), Tasks.get(0));
    }

    @Test
    public void addTask_addNewTask() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();

        TypicalTestTasks task = new TypicalTestTasks();

        typicalTaskManager.addTask(new Task(task.task9));;

        List<ReadOnlyTask> Tasks = typicalTaskManager.getTaskList();

        assertEquals(new Task(task.task9), Tasks.get(Tasks.size() - 1));
    }

    @Test
    public void addTask_addDuplicateTask_ThrowException() throws IllegalValueException {
        TaskManager typicalTaskManager = new TypicalTestTasks().getTypicalTaskManager();

        TypicalTestTasks task = new TypicalTestTasks();


        thrown.expect(IllegalValueException.class);
        typicalTaskManager.addTask(new Task(task.task1));;
    }

    //@@author

    /**
     * A stub ReadOnlyTaskManager whose tasks and tags lists can violate interface constraints.
     */
    private static class TaskManagerStub implements ReadOnlyTaskManager {
        private final ObservableList<ReadOnlyTask> tasks = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        TaskManagerStub(Collection<? extends ReadOnlyTask> tasks, Collection<? extends Tag> tags) {
            this.tasks.setAll(tasks);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<ReadOnlyTask> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
