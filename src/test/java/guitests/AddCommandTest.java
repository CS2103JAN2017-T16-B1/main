package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.AddCommand;

//@@author A0140072X
public class AddCommandTest extends TaskManagerGuiTest {

    @Test
    public void add() {
        //add one task
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.task8;

        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToListandSort(currentList, taskToAdd);

        //add another task
        taskToAdd = td.task9;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToListandSort(currentList, taskToAdd);

        //add duplicate task
        commandBox.runCommand(td.task8.getAddCommand());
        assertResultMessage(AddCommand.MESSAGE_DUPLICATE_TASK);
        assertTrue(taskListPanel.isListMatching(currentList));

        //add to empty list
        commandBox.runCommand("clear");
        assertAddSuccess(td.task1);

        //invalid command
        commandBox.runCommand("adds Johnny");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertAddSuccess(TestTask taskToAdd, TestTask... currentList) {



        commandBox.runCommand(taskToAdd.getAddCommand());

        //confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertMatching(taskToAdd, addedCard);

        //confirm the list now contains all previous tasks plus the new task
        TestTask[] expectedList = TestUtil.addTasksToListandSort(currentList, taskToAdd);
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

}

