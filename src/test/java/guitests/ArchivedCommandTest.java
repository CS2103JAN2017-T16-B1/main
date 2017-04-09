package guitests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.taskManager.commons.core.Messages;

//@@author A0139375W
public class ArchivedCommandTest extends TaskManagerGuiTest {

    @Test
    public void archived() {

        commandBox.runCommand("arch");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);

        TestTask[] currentList = td.getTypicalTasks();
        TestTask[] expectedList = {td.task1};
        currentList = TestUtil.archiveTaskFromList(currentList, 1);

        commandBox.runCommand("archive 1");
        commandBox.runCommand("archived");
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

    private static <T> List<T> asList(T[] objs) {
        List<T> list = new ArrayList<>();
        for (T obj : objs) {
            list.add(obj);
        }
        return list;
    }


}

//@@author