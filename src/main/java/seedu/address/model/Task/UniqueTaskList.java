package seedu.address.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a Task to the list.
     *
     * @throws DuplicatetaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicatetaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicatetaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Updates the task in the list at position {@code index} with {@code editedtask}.
     *
     * @throws DuplicatetaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedTask) throws DuplicatetaskException {
        assert editedTask != null;

        Task taskToUpdate = internalList.get(index);
        if (taskToUpdate.equals(editedTask) || internalList.contains(editedTask) ){//&& editedTask.getStatus().toString()=="undone") {
            throw new DuplicatetaskException();
        }
        
        taskToUpdate.resetData(editedTask);
        // TODO: The code below is just a workaround to notify observers of the updated task.
        // The right way is to implement observable properties in the Person class.
        // Then, TaskCard should then bind its text labels to those observable properties.
        internalList.set(index, taskToUpdate);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }
    //@@author A0138998B
    /**
     * Sorts tasks in the list according to end times.
     * floating tasks are sorted to the back
     * @throws IllegalValueException 
     * 
     */
    public void sortByEndTime() throws IllegalValueException {
    	List<TaskAndDueDate> list = new ArrayList<TaskAndDueDate>();
        for(Task task:internalList){
        	list.add(new TaskAndDueDate(task,task.getEndTime()));
        }
        sortByEndTime(list);

        internalList.clear();
        for(TaskAndDueDate object:list){
        	internalList.add(new Task(object.task));
        	
        }
       
    }
    /**
     * Sorts tasks in the list according to their names.
     *
     * 
     */
    public void sortByName(){
    	internalList.sort(new Comparator<Task>(){
    		public int compare(Task task1, Task task2){
    			return task1.getName().toString().compareTo(task2.getName().toString());
    		}
    	});
    }
    /**
     * Sorts tasks in the list according to priorities.
     * 
     * 
     */
    public void sortByPriority(){
    	internalList.sort(new Comparator<Task>(){
    		public int compare(Task task1, Task task2){
    			return turnPriorityIntoInt(task1.getPriority())-turnPriorityIntoInt(task2.getPriority());
    		}
    	});
    }
    //@@author 
    public void setTasks(UniqueTaskList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws IllegalValueException {
        final UniqueTaskList replacement = new UniqueTaskList();
        for (final ReadOnlyTask task : tasks) {
            replacement.add(new Task(task));
        }
        setTasks(replacement);
    }

    public UnmodifiableObservableList<Task> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicatetaskException extends DuplicateDataException {
        public DuplicatetaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}
    //@@author A0138998B
    /**
     * Sorts list of tasks by duedate
     * Floating tasks will be sorted to the end
     */
    public void sortByEndTime(List<TaskAndDueDate> list){
    	Collections.sort(list, new Comparator<TaskAndDueDate>() {
    		  public int compare(TaskAndDueDate task1, TaskAndDueDate task2) {
    			  if(task1.dueDate!=null && task2.dueDate!=null){
    		      return task1.dueDate.compareTo(task2.dueDate);
    			  }
    			  else if(task1.dueDate==null && task2.dueDate!=null){
    				 return 1;
    			  }
    			  else if(task1.dueDate!=null && task2.dueDate==null){
    				  return -1;
    			  }
    			  else if(task1.dueDate==null && task2.dueDate==null){
    				  return 0;
    			  }
    			  return 0;
    		  }
    		});
    }
    
    
    
    /**
     * Utility class to store pairs of tasks and their endTimes as LocalDateTime variables to enable easy sorting
     */
    public class TaskAndDueDate{
    	public final ReadOnlyTask task;
    	public LocalDateTime dueDate;
    	
    	public TaskAndDueDate(ReadOnlyTask task,EndTime endTime){
    		this.task=task;
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm");
    		
    		try{
    		this.dueDate= LocalDateTime.parse(endTime.toString().replaceAll("\n",""), dtf);
    		} catch(DateTimeParseException e){
    			this.dueDate=null;
    		}
    	}
    }
    
    public int turnPriorityIntoInt(Priority priority){
    	switch(priority.toString()){
    	case("h"):
    		return 1;
    	case("m"):
    		return 2;
    	case("l"):
    		return 3;
    	default:
    		return 0;
    	}
    }

}
