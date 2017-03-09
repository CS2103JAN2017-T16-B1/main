package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

public class Event extends Assignment implements ReadOnlyEvent {
	StartTime startTime;
	EndTime endTime;
	
	public Event(Name name, Description description, StartTime startTime, EndTime endTime, UniqueTagList tags){
		assert !CollectionUtil.isAnyNull(name, endTime, startTime, description, tags);
		this.Name=name;
		this.description=description;
		this.startTime=startTime;
		this.endTime=endTime;
		this.tags=tags;
	}
	
	public Event(ReadOnlyEvent source) {
		this(source.getName(), source.getDescription(), source.getStartTime(), source.getEndTime(), source.getTags());
	}
	
	
	public void setStartTime(StartTime startTime) {
	    assert startTime != null;
	    this.startTime = startTime;
	}

	public StartTime getStartTime() {
	    return startTime;
	}
	
	public void setEndTime(EndTime endTime) {
	    assert endTime != null;
	    this.endTime = endTime;
	}

	public EndTime getEndTime() {
	    return endTime;
	}
	
	/**
	 * Updates this task's with the details of {@code replacement}.
	 */
	public void resetData(ReadOnlyEvent replacement) {
	    assert replacement != null;
	
	    this.setName(replacement.getName());
	    this.setDescription(replacement.getDescription());
	    this.setStartTime(replacement.getStartTime());
	    this.setEndTime(replacement.getEndTime());
	    this.setTags(replacement.getTags());
	}
	
	public boolean equals(Object other) {
	    return other == this // short circuit if same object
	            || (other instanceof ReadOnlyEvent // instanceof handles nulls
	            && this.isSameStateAs((ReadOnlyEvent) other));
	}
	@Override
	public int hashCode() {
	    // use this method for custom fields hashing instead of implementing your own
	    return Objects.hash(Name, description, endTime, startTime, tags);
	}

	@Override
	public String toString() {
	    return getAsText();
	}
}
