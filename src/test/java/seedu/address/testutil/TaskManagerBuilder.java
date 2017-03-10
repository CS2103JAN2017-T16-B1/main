package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building TaskManager objects.
 * Example usage: <br>
 *     {@code TaskManager ab = new TaskManagerBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class TaskManagerBuilder {

    private TaskManager taskManager;

    public TaskManagerBuilder(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public TaskManagerBuilder withPerson(Person person) throws UniquePersonList.DuplicatePersonException {
        taskManager.addPerson(person);
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
