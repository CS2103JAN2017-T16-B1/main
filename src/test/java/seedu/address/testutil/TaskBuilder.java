package seedu.address.testutil;

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
import seedu.taskManager.model.tag.Tag;
import seedu.taskManager.model.tag.UniqueTagList;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(TestTask taskToCopy) {
        this.task = new TestTask(taskToCopy);
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        task.setTags(new UniqueTagList());
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withEndTime(String endTime) throws IllegalValueException {
        this.task.setEndTime(new EndTime(endTime));
        return this;
    }

    public TaskBuilder withDescription(String description) throws IllegalValueException {
        this.task.setDescription(new Description(description));
        return this;
    }

    public TaskBuilder withStartTime(String startTime) throws IllegalValueException {
        this.task.setStartTime(new StartTime(startTime));
        return this;
    }

    public TaskBuilder withStatus(String status) throws IllegalValueException {
        this.task.setStatus(new Status(status));
        return this;
    }

    public TaskBuilder withPriority(String priority) throws IllegalValueException {
        this.task.setPriority(new Priority(priority));
        return this;
    }

    public TaskBuilder withID(String id) throws IllegalValueException {
        this.task.setId(new ID(id));
        return this;
    }

    public TaskBuilder withRecurPeriod(String recurPeriod) throws IllegalValueException {
        this.task.setRecurPeriod(new RecurPeriod(recurPeriod));
        return this;
    }

    public TaskBuilder withRecurEndDate(String recurEndDate) throws IllegalValueException {
        this.task.setRecurEndDate(new RecurEndDate(recurEndDate));
        return this;
    }

    public TestTask build() {
        return this.task;
    }


}
