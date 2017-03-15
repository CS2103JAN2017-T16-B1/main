package seedu.address.model;

import java.util.Set;

import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.UniqueTaskList;
import seedu.address.model.Task.UniqueTaskList.DuplicatetaskException;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given person. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;
    
    /** Adds the given person */
    void addPerson(Task task) throws UniqueTaskList.DuplicatetaskException;

    /**
     * Updates the Task located at {@code filteredPersonListIndex} with {@code editedPerson}.
     *
     * @throws DuplicatetaskException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws IndexOutOfBoundsException if {@code filteredPersonListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicatetaskException;

    /** Returns the filtered person list as an {@code UnmodifiableObservableList<ReadOnlyPerson>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered person list to show all persons */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered person list to filter by the given keywords*/
    void updateFilteredTaskListByKeywords(Set<String> keywords);

    /** Updates the filter of the filtered task list to filter by the given tags*/
    void updateFilteredTaskListByTag(Tag tag);
    
    /** Updates the filter of the filtered task list to filter by low priority*/
    void updateFilteredTaskListByLowPriority();
    
    /** Updates the filter of the filtered task list to filter by priority*/
    void updateFilteredTaskListByMediumPriority();
    
    /** Updates the filter of the filtered task list to filter by high priority*/
    void updateFilteredTaskListByHighPriority();
}
