package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.taskManager.commons.core.Messages;
import seedu.taskManager.logic.commands.UndoCommand;

//@@author A0140072X
public class UndoCommandTest extends TaskManagerGuiTest {

    @Test
    public void undo() {
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAILED);
        // assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("un");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);

        TestTask[] currentList = td.getTypicalTasks();
        //delete the first in the list
        TestTask[] newcurrentList = TestUtil.removeTaskFromList(currentList, 1);
        commandBox.runCommand("delete 1");
        assertTrue(taskListPanel.isListMatching(newcurrentList));
        commandBox.runCommand("undo");
        assertTrue(taskListPanel.isListMatching(currentList));

        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);


    }


}
