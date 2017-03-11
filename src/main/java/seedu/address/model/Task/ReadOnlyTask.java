package seedu.address.model.Task;

import seedu.address.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Task in the taskmanager.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Name getName();
    Description getDescription();
    StartTime getStartTime();
    EndTime getEndTime();
    Priority getPriority();
	Status getStatus();
	ID getId();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the task's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getDescription().equals(this.getDescription())
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime()))
                && other.getPriority().equals(this.getPriority())
                && other.getId().equals(this.getId())
                && other.getStatus().equals(this.getStatus());
    }

    /**
     * Formats the Task/event as text, showing all task/event details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription());
        		if(noEndTime() && noStartTime()){
        			
        		}else 
        		if(noEndTime() && !noStartTime()){
        			builder.append(" DeadLine: ")
        			.append(getStartTime());
        		}else
        		if(!noEndTime() && !noStartTime()){
        			builder.append(" Event Start time: ")
        			.append(getStartTime())
        			.append(" Event end time: ")
        			.append(getEndTime());
        		}
                
                builder.append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
	boolean noEndTime();
	boolean noStartTime();
	
}
