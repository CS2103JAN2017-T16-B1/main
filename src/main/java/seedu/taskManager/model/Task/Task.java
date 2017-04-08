package seedu.taskManager.model.Task;

import java.util.Objects;

import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.commons.util.CollectionUtil;
import seedu.taskManager.logic.parser.ParserUtil;
import seedu.taskManager.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {


    private Name name;
    private Description description;
    private StartTime startTime;
    private EndTime endTime;
    private ID id;
    private Priority priority;
    private Status status;
    private RecurPeriod recurPeriod;
    private RecurEndDate recurEndDate;
    private UniqueTagList tags;

    /**
     * Every parameter must be present and not null.
     * @throws IllegalValueException
     */

    public Task(Name name, Description description, StartTime startTime, EndTime endTime,
            ID id, Priority priority, Status status, RecurPeriod recurPeriod,
            RecurEndDate recurEndDate, UniqueTagList tags) throws IllegalValueException {
        assert !CollectionUtil.isAnyNull(name, id);

        if (startTime.toString() != null) {
            ParserUtil.isAfter(startTime.toString(), endTime.toString());
        }

        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.recurPeriod = recurPeriod;
        this.recurEndDate = recurEndDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    public Task(Name name, Description description, StartTime startTime, EndTime endTime,
            ID id, Priority priority, Status status, UniqueTagList tags) throws IllegalValueException {
        assert !CollectionUtil.isAnyNull(name, id);
        ParserUtil.isAfter(startTime.startTime, endTime.endTime);

        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    /**
     * Creates a copy of the given ReadOnlyPerson.
     * @throws IllegalValueException
     */
    public Task(ReadOnlyTask source) throws IllegalValueException {
        this(source.getName(), source.getDescription(), source.getStartTime(),
                source.getEndTime(), source.getId(), source.getPriority(), source.getStatus(),
                source.getRecurPeriod(), source.getRecurEndDate(), source.getTags());
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    public void setPriority(Priority priority) {
        assert priority != null;
        this.priority = priority;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setStatus(Status status) {
        assert status != null;
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setRecurPeriod(RecurPeriod recurPeriod) {
        assert recurPeriod != null;
        this.recurPeriod = recurPeriod;
    }

    @Override
    public RecurPeriod getRecurPeriod() {
        return recurPeriod;
    }


    public void setRecurEndDate(RecurEndDate recurEndDate) {
        assert recurEndDate != null;
        this.recurEndDate = recurEndDate;
    }


    @Override
    public RecurEndDate getRecurEndDate() {
        return recurEndDate;
    }



    @Override
    public ID getId() {
        return id;
    }

    public void setDescription(Description description) {
        assert description != null;
        this.description = description;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    public void setStartTime(StartTime startTime) {
        assert startTime != null;
        this.startTime = startTime;
    }
    @Override
    public boolean noStartTime() {
        assert startTime != null;
        if (this.startTime.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public StartTime getStartTime() {
        return startTime;
    }

    public void setEndTime(EndTime endTime) {
        assert endTime != null;
        this.endTime = endTime;
    }

    @Override
    public EndTime getEndTime() {
        return endTime;
    }
    @Override
    public boolean noEndTime() {
        assert endTime != null;
        if (this.endTime.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setDescription(replacement.getDescription());
        this.setStartTime(replacement.getStartTime());
        this.setEndTime(replacement.getEndTime());
        this.setPriority(replacement.getPriority());
        this.setStatus(replacement.getStatus());
        this.setRecurPeriod(replacement.getRecurPeriod());
        this.setRecurEndDate(replacement.getRecurEndDate());
        this.setTags(replacement.getTags());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, startTime, endTime, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
