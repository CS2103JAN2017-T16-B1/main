package seedu.address.testutil;

import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.tag.UniqueTagList;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private EndTime endTime;
    private StartTime startTime;
    private Description description;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    /**
     * Creates a copy of {@code personToCopy}.
     */
    public TestTask(TestTask personToCopy) {
        this.name = personToCopy.getName();
        this.description = personToCopy.getDescription();
        this.startTime = personToCopy.getStartTime();
        this.endTime = personToCopy.getEndTime();
        this.tags = personToCopy.getTags();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(EndTime endTime) {
        this.endTime = endTime;
    }

    public void setEmail(StartTime startTime) {
        this.startTime = startTime;
    }

    public void setPhone(Description description) {
        this.description = description;
    }

    public void setTags(UniqueTagList tags) {
        this.tags = tags;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }

    @Override
    public EndTime getEndTime() {
        return endTime;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("a/" + this.getEndTime().endTime + " ");
        sb.append("p/" + this.getDescription().description + " ");
        sb.append("e/" + this.getStartTime().startTime + " ");
        this.getTags().asObservableList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

	@Override
	public Priority getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ID getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean noEndTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noStartTime() {
		// TODO Auto-generated method stub
		return false;
	}
}
