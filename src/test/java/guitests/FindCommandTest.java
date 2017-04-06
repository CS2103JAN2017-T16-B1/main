package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.testutil.TestTask;

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
        assertFindResult("find School", td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
        assertFindResult("find @School"); //no results

        //find after deleting one result
        commandBox.runCommand("delete 1");
        assertFindResult("find Midterms4", td.task4);
        assertFindResult("find Midterms");

        //find after editing one result
        commandBox.runCommand("archive 2");
        assertFindResult("find #h"); //no result because task became done
        assertFindResult("find @School", td.task10); //1result found in archived folder
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
    }

    private void assertFindResult(String command, TestTask... expectedHits) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
