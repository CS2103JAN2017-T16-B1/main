package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.ArchiveCommand;

//@@author A0140072X
public class ArchiveCommandTest extends TaskManagerGuiTest {

    @Test
    public void archive() {
        commandBox.runCommand("archive");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE));

        commandBox.runCommand("arc");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);

        TestTask[] currentList = td.getTypicalTasks();

        currentList = TestUtil.removeTaskFromList(currentList, 1);
        currentList = TestUtil.sortByEndTime(currentList);
        commandBox.runCommand("archive 1");
        assertTrue(taskListPanel.isListMatching(currentList));



    }

    private static <T> List<T> asList(T[] objs) {
        List<T> list = new ArrayList<>();
        for (T obj : objs) {
            list.add(obj);
        }
        return list;
    }


}
