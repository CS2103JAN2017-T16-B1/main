package seedu.address.model;

import java.util.Set;

import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.UniqueTaskList;
import seedu.address.model.Task.UniqueTaskList.DuplicatetaskException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given task. */
    void deletePerson(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given task */
    void addPerson(Task task) throws UniqueTaskList.DuplicatetaskException;

    /**
     * Updates the Task located at {@code filteredPersonListIndex} with {@code editedPerson}.
     *
     * @throws DuplicatetaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code filteredPersonListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicatetaskException;

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyPerson>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered task list to filter by the given keywords*/
    void updateFilteredPersonList(Set<String> keywords);

}
