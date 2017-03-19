package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Task.EndTime;
import seedu.address.model.Task.ID;
import seedu.address.model.Task.StartTime;
import seedu.address.model.Task.Status;
import seedu.address.model.Task.Name;
import seedu.address.model.Task.Priority;
import seedu.address.model.Task.Task;
import seedu.address.model.Task.Description;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.UniqueTaskList;
import seedu.address.model.tag.UniqueTagList;

/**
 * Edits the details of an existing task in the task manager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last task listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [NAME] [beg/STARTTIME] [end/ENDTIME] [des/DESCRIPTION ] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 st/2017-3-1-2359 des/go to ntuc";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited:\n %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager.";

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param filteredTaskListIndex the index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;

        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        try {
            model.updateTask(filteredTaskListIndex, editedTask);
        } catch (UniqueTaskList.DuplicatetaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */

    private static Task createEditedTask(ReadOnlyTask taskToEdit,
                                             EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElseGet(taskToEdit::getName);
        Description updatedDescription = editTaskDescriptor.getDescription().orElseGet(taskToEdit::getDescription);
        StartTime updatedStartTime = editTaskDescriptor.getStartTime().orElseGet(taskToEdit::getStartTime);
        EndTime updatedEndTime = editTaskDescriptor.getEndTime().orElseGet(taskToEdit::getEndTime);
        Priority updatedPriority=editTaskDescriptor.getPriority().orElseGet(taskToEdit::getPriority);
        Status updatedStatus=editTaskDescriptor.getStatus().orElseGet(taskToEdit::getStatus);
        ID updatedID= taskToEdit.getId();
        UniqueTagList updatedTags =new UniqueTagList(taskToEdit,editTaskDescriptor);//editTaskDescriptor.getTags().orElseGet(taskToEdit::getTags);
        System.out.print(updatedTags);
        
        return new Task(updatedName, updatedDescription, updatedStartTime,
        		updatedEndTime, updatedID, updatedPriority, updatedStatus,updatedTags);
        
        }


    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Optional<Name> name = Optional.empty();
        private Optional<Description> description = Optional.empty();
        private Optional<StartTime> startTime = Optional.empty();
        private Optional<EndTime> endTime = Optional.empty();
        private Optional<Priority> priority = Optional.empty();

        private Optional<Status> status = Optional.empty();

        private Optional<UniqueTagList> tags = Optional.empty();

        public EditTaskDescriptor() {}


       
		public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.name = toCopy.getName();
            this.description = toCopy.getDescription();
            this.startTime = toCopy.getStartTime();
            this.endTime = toCopy.getEndTime();
            this.priority= toCopy.getPriority();
            this.status= toCopy.getStatus();
            this.tags = toCopy.getTags();
        }

		/**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.name, this.description, this.startTime, this.endTime, this.priority, this.status, this.tags);
        }

        public void setName(Optional<Name> name) {
            assert name != null;
            this.name = name;
        }

        public Optional<Name> getName() {
            return name;
        }
        

        public void setStatus(Optional<Status> status) {
            assert status != null;
            this.status = status;
        }
        public Optional<Status> getStatus() {
            return status;
        }

        public void setDescription(Optional<Description> description) {
            assert description != null;
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return description;
        }

        public void setStartTime(Optional<StartTime> startTime) {
            assert startTime != null;
            this.startTime = startTime;
        }

        public Optional<StartTime> getStartTime() {
            return startTime;
        }

        public void setEndTime(Optional<EndTime> endTime) {
            assert endTime != null;
            this.endTime = endTime;
        }

        public Optional<EndTime> getEndTime() {
            return endTime;
        }
        
        public void setTags(Optional<UniqueTagList> tags) {
            assert tags != null;
            this.tags = tags;
        }

        public Optional<UniqueTagList> getTags() {
            return tags;
        }
        
        private Optional<Priority> getPriority() {
			return priority;
		}
        
        public void setPriority(Optional<Priority> priority){
        	this.priority = priority;
        }
    }
}
