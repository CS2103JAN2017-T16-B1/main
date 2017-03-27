package seedu.address.testutil;

import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.RecurEndDate;
import seedu.address.model.Task.RecurPeriod;
import seedu.address.model.tag.UniqueTagList;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private EndTime endTime;
    private StartTime startTime;
    private Description description;
    private UniqueTagList tags;
    private ID id;
    private Priority priority;
    private Status status;
    private RecurPeriod recurPeriod;
    private RecurEndDate recurEndDate;


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

    public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setTags(UniqueTagList tags) {
        this.tags = tags;
    }
    
    public void setRecurPeriod(RecurPeriod recurPeriod){
    	this.recurPeriod=recurPeriod;
    }
    
    public void setRecurEndDate(RecurEndDate recurEndDate){
    	this.recurEndDate=recurEndDate;
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
        sb.append("e/" + this.getEndTime().endTime + " ");
        sb.append("d/" + this.getDescription().description + " ");
        sb.append("s/" + this.getStartTime().startTime + " ");
        this.getTags().asObservableList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
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
    
    @Override
    public ID getId() {
        return id;
    }
	
    
    public void setId(ID id) {
        assert id != null;
        this.id=id;
    }

	@Override
	public boolean noEndTime() {
	    assert endTime != null;
        if(this.endTime.isEmpty()){
            return true;
        }else {
            return false;
        }
	}

	@Override
	public boolean noStartTime() {
	    assert startTime != null;
        if(this.startTime.isEmpty()){
            return true;
        }else {
            return false;
        }
	}

	@Override
	public RecurPeriod getRecurPeriod() {
		
		return this.recurPeriod;
	}

	@Override
	public RecurEndDate getRecurEndDate() {
		return this.recurEndDate;
	}
}
