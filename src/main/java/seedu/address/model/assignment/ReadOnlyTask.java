package seedu.address.model.assignment;

import seedu.address.model.tag.UniqueTagList;

public interface ReadOnlyTask {
	Name getName();
	Description getDescription();
	DeadLine getDeadLine();
	
	UniqueTagList getTags();
	
	default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getDescription().equals(this.getDescription())
                && other.getDeadLine().equals(this.getDeadLine())
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
                .append(" DeadLine: ")
                .append(getDeadLine())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
