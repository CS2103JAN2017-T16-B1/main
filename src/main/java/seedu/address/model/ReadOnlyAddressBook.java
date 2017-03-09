package seedu.address.model;


import javafx.collections.ObservableList;
import seedu.address.model.assignment.ReadOnlyEvent;
import seedu.address.model.assignment.ReadOnlyTask;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();
    
    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
	ObservableList<ReadOnlyEvent> getEventList();

	/**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
	ObservableList<ReadOnlyTask> getTaskList();

}
