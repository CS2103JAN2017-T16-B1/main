package seedu.address.model;

import java.util.Set;

import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.Task;
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
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;
    
    /** Adds the given task */
    void addTask(Task task) throws UniqueTaskList.DuplicatetaskException;

    /**
     * Updates the Task located at {@code filteredTaskListIndex} with {@code editedTask}.
     *
     * @throws DuplicatetaskException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws IndexOutOfBoundsException if {@code filteredPersonListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicatetaskException;

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyPerson>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Returns the current toggle status */
    String getCurrentToggleStatus();
    
    /** sets the current toggle status*/
    void setCurrentToggleStatus(String currentToggleStatus);
    
    /** Updates the filter of the filtered person list to show all persons */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered person list to filter by the given keywords*/
    void updateFilteredTaskListByKeywords(Set<String> keywords);

    /** Updates the filter of the filtered task list to filter by done status*/
    void updateFilteredTaskListByDoneStatus();
    
    /** Updates the filter of the filtered task list to filter by undone status*/
    void updateFilteredTaskListByUnDoneStatus();
    
    /** Updates the filter of the filtered task list to filter by low priority*/
    void updateFilteredTaskListByLowPriority();
    
    /** Updates the filter of the filtered task list to filter by priority*/
    void updateFilteredTaskListByMediumPriority();
    
    /** Updates the filter of the filtered task list to filter by high priority*/
    void updateFilteredTaskListByHighPriority();

    boolean undoTask();

    void updateFilteredTaskListByArchived();

    /** Updates the filer of the filtered task list to filter by archived and keyword*/
	void updateArchivedFilteredTaskListByKeyword(String archive);


	/* Updates the filter of the filtered task list to filter by events only*/
	void updateFilteredTaskListByEvent();

	/** Updates the filter of the filtered task list to filter by task only*/
	void updateFilteredTaskListByTask();

	/** Updates the filter of the filtered task list to filter by floating task only*/
	void updateFilteredTaskListByFloatingTask();

  void indicateLoadEvent();


	void sortTasksByEndTime();

	void sortTasksByName();

	void sortTasksByPriority();

	
}
