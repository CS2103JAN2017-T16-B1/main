package seedu.address.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.assignment.*;
import seedu.address.model.assignment.UniqueEventList.DuplicateEventException;
import seedu.address.model.assignment.UniqueEventList.EventNotFoundException;
import seedu.address.model.assignment.UniqueTaskList.DuplicateTaskException;
import seedu.address.model.assignment.UniqueTaskList.TaskNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEventList events;
    private final UniqueTaskList tasks;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new UniqueEventList();
        tasks = new UniqueTaskList();
        tags = new UniqueTagList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Tags in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

//// list overwrite operations

    public void setEvent(List<? extends ReadOnlyEvent> events) throws DuplicateEventException {
        this.events.setEvents(events);
    }
    
    public void setTask(List<? extends ReadOnlyTask> tasks) throws DuplicateTaskException {
        this.tasks.setTasks(tasks);
    }
    
    public void setTags(Collection<Tag> tags) throws UniqueTagList.DuplicateTagException {
        this.tags.setTags(tags);
    }

    public void resetData(ReadOnlyAddressBook newData) {
        assert newData != null;
        try {
            setEvent(newData.getEventList());
            setTask(newData.getTaskList());
        } catch (DuplicateEventException e) {
            assert false : "Task Manager should not have duplicate events or tasks";
        } catch( DuplicateTaskException e){
        	assert false : "Task Manager should not have duplicate events or tasks";
        }
        try {
            setTags(newData.getTagList());
        } catch (UniqueTagList.DuplicateTagException e) {
            assert false : "Task Manager should not have duplicate tags";
        }
        syncMasterTagListWith(tasks);
        syncMasterTagListWith(events);
    }

//// Task operations

    /**
     * Adds a Task to the Task Manager.
     * Also checks the new task's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #tags}.
     *
     * @throws DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(Task p) throws DuplicateTaskException {
        syncMasterTagListWith(p);
        tasks.add(p);
    }
    

    /**
     * Updates the Task in the list at position {@code index} with {@code editedReadOnlyTask}.
     * {@code Task Manager}'s tag list will be updated with the tags of {@code editedReadOnlyTask}.
     * @see #syncMasterTagListWith(Task)
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedReadOnlyTask)
            throws DuplicateTaskException {
        assert editedReadOnlyTask != null;

        Task editedTask = new Task(editedReadOnlyTask);
        syncMasterTagListWith(editedTask);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        tasks.updateTask(index, editedTask);
    }
////Event operations

   /**
    * Adds a Task to the Task Manager.
    * Also checks the new task's tags and updates {@link #tags} with any new tags found,
    * and updates the Tag objects in the person to point to those in {@link #tags}.
    *
    * @throws DuplicateTaskException if an equivalent task already exists.
    */
   public void addEvent(Event p) throws DuplicateEventException {
       syncMasterTagListWith(p);
       events.add(p);
   }
   

   /**
    * Updates the Event in the list at position {@code index} with {@code editedReadOnlyEvent}.
    * {@code Event Manager}'s tag list will be updated with the tags of {@code editedReadOnlyEvent}.
    * @see #syncMasterTagListWith(Event)
    *
    * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
    *      another existing person in the list.
    * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
    */
   public void updateEvent(int index, ReadOnlyEvent editedReadOnlyEvent)
           throws DuplicateEventException {
       assert editedReadOnlyEvent != null;

       Event editedEvent = new Event(editedReadOnlyEvent);
       syncMasterTagListWith(editedEvent);
       // TODO: the tags master list will be updated even though the below line fails.
       // This can cause the tags master list to have additional tags that are not tagged to any person
       // in the person list.
       events.updateEvent(index, editedEvent);
   }
   
////General operations

    /**
     * Ensures that every tag in this assignment
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     */
    private void syncMasterTagListWith(Assignment assignment) {
        final UniqueTagList assignmentTags = assignment.getTags();
        tags.mergeFrom(assignmentTags);

        // Create map with values = tag object references in the master list
        // used for checking person tag references
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        tags.forEach(tag -> masterTagObjects.put(tag, tag));

        // Rebuild the list of tags to point to the relevant tags in the master tag list.
        final Set<Tag> correctTagReferences = new HashSet<>();
        assignmentTags.forEach(tag -> correctTagReferences.add(masterTagObjects.get(tag)));
        assignment.setTags(new UniqueTagList(correctTagReferences));
    }

    /**
     * Ensures that every tag in the task or event:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     *  @see #syncMasterTagListWith(Person)
     */
    private void syncMasterTagListWith(UniqueEventList events) {
        events.forEach(this::syncMasterTagListWith);
    }
    
    private void syncMasterTagListWith(UniqueTaskList tasks) {
        tasks.forEach(this::syncMasterTagListWith);
    }

    public boolean removeTask(ReadOnlyTask key) throws TaskNotFoundException {
        if (tasks.remove(key)) {
            return true;
        } else {
            throw new TaskNotFoundException();
        }
    }
    
    public boolean removeEvent(ReadOnlyEvent key) throws EventNotFoundException {
        if (events.remove(key)) {
            return true;
        } else {
            throw new EventNotFoundException();
        }
    }

//// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

//// util methods

    @Override
    public String toString() {
        return events.asObservableList().size() + " Events, " + tasks.asObservableList().size() + " Tasks, " + tags.asObservableList().size() +  " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyEvent> getEventList() {
        return new UnmodifiableObservableList<>(events.asObservableList());
    }
    
    @Override
    public ObservableList<ReadOnlyTask> getTaskList() {
        return new UnmodifiableObservableList<>(tasks.asObservableList());
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return new UnmodifiableObservableList<>(tags.asObservableList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.events.equals(((AddressBook) other).events)
                && this.tasks.equals(((AddressBook) other).tasks)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(events, tasks, tags);
    }
}
