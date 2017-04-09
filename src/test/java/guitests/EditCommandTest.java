package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TestTask;
import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.EditCommand;
import seedu.taskManager.model.Task.EndTime;
import seedu.taskManager.model.Task.Name;
import seedu.taskManager.model.Task.Priority;
import seedu.taskManager.model.Task.StartTime;
import seedu.taskManager.model.tag.Tag;

// TODO: reduce GUI tests by transferring some tests to be covered by lower level tests.
public class EditCommandTest extends TaskManagerGuiTest {

    // The list of tasks in the task list panel is expected to match this list.
    // This list is updated with every successful call to assertEditSuccess().
    TestTask[] expectedTaskList = td.getTypicalTasks();

    @Test
    public void edit_allFieldsSpecified_success() throws Exception {

        String detailsToEdit = "Study s/2017-03-03-2100 e/2017-10-10-2100 t/school";
        int taskManagerIndex = 1;

        TestTask editedTask = new TaskBuilder().withName("Study").withStartTime("2017-03-03-2100").withDescription("")
                .withEndTime("2017-10-10-2100").withPriority("m").build();

        assertEditSuccess(taskManagerIndex, taskManagerIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_notAllFieldsSpecified_success() throws Exception {
        String detailsToEdit = "t/sweetie t/bestie";
        int taskManagerIndex = 2;

        TestTask taskToEdit = expectedTaskList[taskManagerIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withTags("school", "sweetie", "bestie").withPriority("l")
                .build();

        assertEditSuccess(taskManagerIndex, taskManagerIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_clearTags_success() throws Exception {
        String detailsToEdit = "t/school";
        int taskManagerIndex = 2;

        TestTask taskToEdit = expectedTaskList[taskManagerIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withTags().withPriority("l").build();

        System.out.println("this shit starts here");
        assertEditSuccess(taskManagerIndex, taskManagerIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_findThenEdit_success() throws Exception {

        commandBox.runCommand("find midterm");

        String detailsToEdit = "Belle";
        int filteredTaskListIndex = 1;
        int taskManagerIndex = 1;

        TestTask taskToEdit = expectedTaskList[taskManagerIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withName("Belle").withPriority("m").build();

        assertEditSuccess(filteredTaskListIndex, taskManagerIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_missingTaskIndex_failure() {
        commandBox.runCommand("edit Bobby");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void edit_invalidTaskIndex_failure() {
        commandBox.runCommand("edit 8 Bobby");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void edit_noFieldsSpecified_failure() {
        commandBox.runCommand("edit 1");
        assertResultMessage(EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void edit_invalidValues_failure() {
        commandBox.runCommand("edit 1 *&");
        assertResultMessage(Name.MESSAGE_NAME_CONSTRAINTS);

        commandBox.runCommand("edit 1 p/abcd");
        assertResultMessage(Priority.MESSAGE_NAME_CONSTRAINTS);

        commandBox.runCommand("edit 1 s/yahoo!!!");
        assertResultMessage(StartTime.MESSAGE_DATETIME_CONSTRAINTS);

        commandBox.runCommand("edit 1 e/google");
        assertResultMessage(EndTime.MESSAGE_DATETIME_CONSTRAINTS);

        commandBox.runCommand("edit 1 t/*&");
        assertResultMessage(Tag.MESSAGE_TAG_CONSTRAINTS);
    }

    @Test
    public void edit_duplicateTask_failure() {
        commandBox.runCommand("edit 3 Study for Midterm2 p/l e/2017-04-06-2100");
        assertResultMessage(EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    // /**
    // * Checks whether the edited task has the correct updated details.
    // *
    // * @param filteredTaskListIndex
    // * index of task to edit in filtered list
    // * @param taskManagerIndex
    // * index of task to edit in the task manager. Must refer to the
    // * same task as {@code filteredTaskListIndex}
    // * @param detailsToEdit
    // * details to edit the task with as input to the edit command
    // * @param editedTask
    // * the expected task after editing the task's details
    // */
    private void assertEditSuccess(int filteredTaskListIndex, int taskManagerIndex, String detailsToEdit,
            TestTask editedTask) {

        commandBox.runCommand("edit " + filteredTaskListIndex + " " + detailsToEdit);
        assertResultMessage(String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask));
        // confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(editedTask.getName().fullName);
        assertMatching(editedTask, editedCard);

        // confirm the list now contains all previous tasks plus the task with
        // updated details
        expectedTaskList[taskManagerIndex - 1] = editedTask;
        assertTrue(taskListPanel.isListMatching(expectedTaskList));
    }
}
