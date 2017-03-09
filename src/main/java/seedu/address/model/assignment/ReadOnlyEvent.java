package seedu.address.model.assignment;

import seedu.address.model.tag.UniqueTagList;

public interface ReadOnlyEvent {
	Name getName();
	Description getDescription();
	StartTime getStartTime();
	EndTime getEndTime();
	
	UniqueTagList getTags();
	
	default boolean isSameStateAs(ReadOnlyEvent other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getDescription().equals(this.getDescription())
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime())
               );
    }
	
	/**
     * Formats the Task as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Start Time: ")
                .append(getStartTime())
                .append(" End Time: ")
                .append(getEndTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
