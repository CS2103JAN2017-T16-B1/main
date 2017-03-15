package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

	@XmlElement(required = true)
    private int id;
	@XmlElement(required = true)
    private String name;
    @XmlElement(required = false)
    private String description;
    @XmlElement(required = false)
    private String starttime;
    @XmlElement(required = false)
    private String endtime;
    @XmlElement(required = true)
	private String priority;
    @XmlElement(required = true)
    private String status;
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();
	


    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        name = source.getName().fullName;
        description = source.getDescription().description;
        starttime = source.getStartTime().startTime;
        endtime = source.getEndTime().endTime;
        priority = source.getPriority().priority;
        status = source.getStatus().status;
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final Description description = new Description(this.description);
        final StartTime startTime = new StartTime(this.starttime);
        final EndTime endTime = new EndTime(this.endtime);
        final ID id = new ID(String.valueOf(this.id));
        final Priority priority = new Priority(this.priority);
        final Status status = new Status(this.status);
        final UniqueTagList tags = new UniqueTagList(taskTags);
        return new Task(name, description, startTime, endTime, id, priority, status, tags);
    }
}