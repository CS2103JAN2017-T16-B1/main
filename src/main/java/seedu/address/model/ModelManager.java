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

/**
 * Represents the in-memory model of the task manager data. All changes to any
 * model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
	private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

	private TaskManager taskManager;
	private TaskManager previousTaskMgr;
	private final FilteredList<ReadOnlyTask> filteredTasks;
	private String currentToggleStatus;

	/**
	 * Initializes a ModelManager with the given taskManager and userPrefs.
	 */
	public ModelManager(ReadOnlyTaskManager taskManager, UserPrefs userPrefs) {
		super();
		assert !CollectionUtil.isAnyNull(taskManager, userPrefs);

		logger.fine("Initializing with task manager: " + taskManager + " and user prefs " + userPrefs);

		this.taskManager = new TaskManager(taskManager);

		filteredTasks = new FilteredList<>(this.taskManager.getTaskList());

		filteredTasks.setPredicate(task -> {
			if (task.getStatus().toString().contains("un")) {
				return true;
			} else {
				return false;
			}
		});
		
		setCurrentToggleStatus("ALL");

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
		setPrevious();
		taskManager.removeTask(target);

		indicateTaskManagerChanged();
	}

	@Override
	public synchronized void addTask(Task task) throws UniqueTaskList.DuplicatetaskException {
		setPrevious();
		taskManager.addTask(task);
		updateFilteredListToShowAll();
		indicateTaskManagerChanged();
	}

	// @@author A0140072X
	private void setPrevious() {
		previousTaskMgr = new TaskManager(taskManager);
	}

	// @@author A0140072X
	public boolean undoTask() {
		if (previousTaskMgr == null) {
			return false;
		} else {
			TaskManager currentTaskMgr = new TaskManager(taskManager);
			taskManager.resetData(previousTaskMgr);
			previousTaskMgr.resetData(currentTaskMgr);
			return true;
		}

	}
	//@@author A0138998B
	@Override
	public void sortTasksByEndTime(){
		taskManager.sortTasksByEndTime();
	}
	
	@Override
	public void sortTasksByName(){
		taskManager.sortTasksByName();
	}
	
	@Override
	public void sortTasksByPriority(){
		taskManager.sortTaskByPriority();
	}
	
	
	@Override
	public void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
			throws UniqueTaskList.DuplicatetaskException {
		assert editedTask != null;
		setPrevious();

		int taskManagerIndex = filteredTasks.getSourceIndex(filteredTaskListIndex);
		taskManager.updateTask(taskManagerIndex, editedTask);

		indicateTaskManagerChanged();
	}

	//@@author A0139509X
	public String getCurrentToggleStatus() {
		return currentToggleStatus;
	}

	//@@author A0139509X
	public void setCurrentToggleStatus(String currentToggleStatus) {
		this.currentToggleStatus = currentToggleStatus;
	}
	
    //@@author 
	// =========== Filtered Person List Accessors
	// =============================================================

	@Override
	public UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList() {
		return new UnmodifiableObservableList<>(filteredTasks);
	}

	@Override
	public void updateFilteredListToShowAll() {
		// filteredTasks.setPredicate(null);
		filteredTasks.setPredicate(task -> {
			if (task.getStatus().toString().contains("un")) {
				return true;
			} else {
				return false;
			}
		});

		setCurrentToggleStatus("ALL");
	}

	// @@author A0140072X
	public void updateFilteredTaskListByArchived() {
		updateFilteredListToShowAll();
		filteredTasks.setPredicate(task -> {
			if (!task.getStatus().toString().contains("undone")) {
				return true;
			} else {
				return false;
			}
		});
	}

	// @@author A0139509X
	@Override
	public void updateFilteredTaskListByKeywords(Set<String> keywords) {
		updateFilteredTaskListByKeywords(new PredicateExpression(new NameQualifier(keywords)));
	}

	private void updateFilteredTaskListByKeywords(Expression expression) {
		filteredTasks.setPredicate(expression::satisfies);
	}

	@Override
	public void updateFilteredTaskListByEvent() {
		filteredTasks.setPredicate(task -> {
			if ((!(task.getStartTime().toString().equalsIgnoreCase("\n")) 
					&& !(task.getEndTime().toString().equalsIgnoreCase("\n"))
					&& (task.getStatus().toString().contains("un")))) {
				return true;
			} else {
				return false;
			}
		});
		setCurrentToggleStatus("EVENT");
	}

	@Override
	public void updateFilteredTaskListByTask() {
		filteredTasks.setPredicate(task -> {
			if (((task.getStartTime().toString().equalsIgnoreCase("\n")) 
					&& !(task.getEndTime().toString().equalsIgnoreCase("\n"))
					&& (task.getStatus().toString().contains("un")))) {
				return true;
			} else {
				return false;
			}
		});
		setCurrentToggleStatus("TASK");
		
	}

	@Override
	public void updateFilteredTaskListByFloatingTask() {
		filteredTasks.setPredicate(task -> {
			if (((task.getStartTime().toString().equalsIgnoreCase("\n")) 
					&& (task.getEndTime().toString().equalsIgnoreCase("\n"))
					&& (task.getStatus().toString().contains("un")))) {
				return true;
			} else {
				return false;
			}
		});
		setCurrentToggleStatus("FLOATING_TASK");
		
	}
	
	// @@author A0139509X
	@Override
	public void updateFilteredTaskListByHighPriority() {
		filteredTasks.setPredicate(task -> {
			if ((task.getPriority().toString().equals("h")) 
					&& (task.getStatus().toString().equals("undone"))) {
				return true;
			} else {
				return false;
			}
		});
	}

	// @@author A0139509X
	@Override
	public void updateFilteredTaskListByMediumPriority() {
		filteredTasks.setPredicate(task -> {
			if ((task.getPriority().toString().equals("m")) 
					&& (task.getStatus().toString().equals("undone"))) {
				return true;
			} else {
				return false;
			}
		});
	}

	@Override
	public void updateFilteredTaskListByLowPriority() {
		filteredTasks.setPredicate(task -> {
			if ((task.getPriority().toString().equals("l")) 
					&& (task.getStatus().toString().equals("undone"))) {
				return true;
			} else {
				return false;
			}
		});
	}

	// @@author A0139509X
	@Override
	public void updateFilteredTaskListByDoneStatus() {
		filteredTasks.setPredicate(task -> {
			if (task.getStatus().toString().equals("done")) {
				return true;
			} else {
				return false;
			}
		});
	}
	// @@author A0139509X
	
	// @@author A0139509X
	@Override
	public void updateFilteredTaskListByUnDoneStatus() {
		filteredTasks.setPredicate(task -> {
			if (task.getStatus().toString().equals("undone")) {
				return true;
			} else {
				return false;
			}
		});
	}
	
	// @@author A0139509X
	@Override
	public void updateArchivedFilteredTaskListByKeyword(String archive) {
		filteredTasks.setPredicate(task -> {
			if (((StringUtil.containsWordIgnoreCase(task.getName().fullName, archive))
					|| (StringUtil.containsWordIgnoreCase(task.getDescription().description, archive))
					|| (StringUtil.containsTagIgnoreCase(task.getTags(), archive)))
					&& (task.getStatus().toString().equalsIgnoreCase("done")))
				return true;
			else {
				return false;
			}
		});
	}
	// ========== Inner classes/interfaces used for filtering
	// =================================================

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

		// @@author A0139509X
		@Override
		public boolean run(ReadOnlyTask task) {
			for (String keyword : nameKeyWords) {
				if (((StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword))
						|| (StringUtil.containsWordIgnoreCase(task.getDescription().description, keyword))
						|| (StringUtil.containsTagIgnoreCase(task.getTags(), keyword)))
						&& (task.getStatus().toString().equalsIgnoreCase("undone")))
					return true;
			}
			return false;

		}

		@Override
		public String toString() {
			return "name=" + String.join(", ", nameKeyWords);
		}
	}

}
