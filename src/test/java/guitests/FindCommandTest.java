package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.FindCommand;
import seedu.taskManager.model.Task.Priority;

public class FindCommandTest extends TaskManagerGuiTest {
    //@@author A0139509X
    @Test
    public void find_nonEmptyList() {

        assertFindResult("find Final"); // no results
        assertFindResult("find Midterm", td.task1); //, td.task4); // multiple results
        assertFindResult("find #h", td.task3); //one result
        assertFindResult("find #m", td.task1, td.task4, td.task5, td.task6, td.task7); //5 results
        assertFindResult("find #l", td.task2); //no results
        //find by tags
        assertFindResult("find school", td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
        assertFindResult("find @school"); //no results

        //find done and undone list
        assertFindResult("find #undone", td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
        assertFindResult("find #done");

        //find after deleting one task
        commandBox.runCommand("list");
        commandBox.runCommand("delete 1");
        assertFindResult("find Midterm4", td.task4);
        assertFindResult("find Midterms");

        //find after archiving one task
        commandBox.runCommand("list");
        commandBox.runCommand("archive 2");
        assertFindResult("find #h"); //no result because task became done
        assertFindResult("find @school", td.task10); //1result found in archived folder
    }

    @Test
    public void find_emptyList() {
        commandBox.runCommand("clear");
        assertFindResult("find Study"); // no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("findStudy");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("find");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        commandBox.runCommand("find @");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        commandBox.runCommand("find #");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        commandBox.runCommand("find #H");
        assertResultMessage(Priority.MESSAGE_NAME_CONSTRAINTS);
    }

    private void assertFindResult(String command, TestTask... expectedHits) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
