package seedu.address.testutil;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.Task.UniqueTaskList;
import seedu.taskManager.model.TaskManager;
import seedu.taskManager.model.tag.Tag;

/**
 * A utility class to help with building TaskManager objects.
 * Example usage: <br>
 *     {@code TaskManager ab = new TaskManagerBuilder().withTask("John", "Doe").withTag("Friend").build();}
 */
public class TaskManagerBuilder {

    private TaskManager taskManager;

    public TaskManagerBuilder(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public TaskManagerBuilder withTask(Task task) throws UniqueTaskList.DuplicatetaskException {
        taskManager.addTask(task);
        return this;
    }

    public TaskManagerBuilder withTag(String tagName) throws IllegalValueException {
        taskManager.addTag(new Tag(tagName));
        return this;
    }

    public TaskManager build() {
        return taskManager;
    }
}
