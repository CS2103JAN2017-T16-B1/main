package seedu.address.model;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.events.model.TaskManagerChangedEvent;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.UniqueTaskList;
import seedu.address.model.Task.UniqueTaskList.TaskNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the task manager data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskManager taskManager;
    private final FilteredList<ReadOnlyTask> filteredTasks;

    /**
     * Initializes a ModelManager with the given taskManager and userPrefs.
     */
    public ModelManager(ReadOnlyTaskManager taskManager, UserPrefs userPrefs) {
        super();
        assert !CollectionUtil.isAnyNull(taskManager, userPrefs);

        logger.fine("Initializing with task manager: " + taskManager + " and user prefs " + userPrefs);

        this.taskManager = new TaskManager(taskManager);


        filteredTasks = new FilteredList<>(this.taskManager.getTaskList());


    }

    public ModelManager() {
        this(new TaskManager(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyTaskManager newData) {
        taskManager.resetData(newData);
        indicateTaskManagerChanged();
    }

    @Override
    public ReadOnlyTaskManager getTaskManager() {
        return taskManager;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateTaskManagerChanged() {
        raise(new TaskManagerChangedEvent(taskManager));
    }

    @Override
    public synchronized void deleteTask(ReadOnlyTask target) throws TaskNotFoundException {

        taskManager.removeTask(target);

        indicateTaskManagerChanged();
    }

    @Override
    public synchronized void addTask(Task task) throws UniqueTaskList.DuplicatetaskException {
        taskManager.addTask(task);
        updateFilteredListToShowAll();
        indicateTaskManagerChanged();
    }

    @Override
    public void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicatetaskException {
        assert editedTask != null;


        int taskManagerIndex = filteredTasks.getSourceIndex(filteredTaskListIndex);
        taskManager.updateTask(taskManagerIndex, editedTask);

        indicateTaskManagerChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList() {
        return new UnmodifiableObservableList<>(filteredTasks);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredTasks.setPredicate(null);
    }

    @Override
    public void updateFilteredTaskListByKeywords(Set<String> keywords) {
        updateFilteredTaskListByKeywords(new PredicateExpression(new NameQualifier(keywords)));
    }

    private void updateFilteredTaskListByKeywords(Expression expression) {
        filteredTasks.setPredicate(expression::satisfies);
    }
    public void updateFilteredTaskListByHighPriority() {
    	filteredTasks.setPredicate(task -> {
            if(task.getPriority().toString() == ("h")) {
                return true;
            } else {
                return false;
            }
        });
    }
    
    public void updateFilteredTaskListByMediumPriority() {
    	filteredTasks.setPredicate(task -> {
            if(task.getPriority().toString() == ("m")) {
                return true;
            } else {
                return false;
            }
        });
    }
    
    public void updateFilteredTaskListByLowPriority() {
    	filteredTasks.setPredicate(task -> {
            if(task.getPriority().toString() == "l") {
                return true;
            } else {
                return false;
            }
        });
    }
    public void updateFilteredTaskListByTag(Tag tag){
        filteredTasks.setPredicate(task -> {
            if(task.getTags().contains(tag)) {
                return true;
            } else {
                return false;
            }
        });
    }

    //========== Inner classes/interfaces used for filtering =================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask task);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyTask task) {
            return qualifier.run(task);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyTask task);
        String toString();
    }

    private class NameQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        NameQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override

        public boolean run(ReadOnlyTask task) {
            return (nameKeyWords.stream()
                    .filter(keyword -> StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword))
                    .findAny()
                    .isPresent())
            		|| (nameKeyWords.stream()
            		.filter(keyword -> StringUtil.containsWordIgnoreCase(task.getStartTime().startTime, keyword))
            		.findAny().isPresent())
            		|| (nameKeyWords.stream()
                    		.filter(keyword -> StringUtil.containsWordIgnoreCase(task.getEndTime().endTime, keyword))
                    		.findAny().isPresent())
            		|| (nameKeyWords.stream()
                    		.filter(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().description, keyword))
                    		.findAny().isPresent());

        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }

}
