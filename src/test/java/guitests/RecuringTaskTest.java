package guitests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;

//@@author A0139375W
public class RecuringTaskTest extends TaskManagerGuiTest {

    @Test
    public void recuringTask() {

        TestTask[] currentList = {td.task11};
        TestTask[] expectedList = {td.task12};
        TestTask[] emptyList = {};

        //checking for creation of new task, when recur period is int
        commandBox.runCommand("clear");
        commandBox.runCommand(td.task11.getAddCommand());
        commandBox.runCommand("archive 1");
        //assertTrue(taskListPanel.isListMatching(expectedList));

        //checking if the old task is archived
        commandBox.runCommand("archived");
        //assertTrue(taskListPanel.isListMatching(currentList));

        //checking if the recur will stop after the endtime passes the enddate
        commandBox.runCommand("list");
        commandBox.runCommand("archive 1");
        assertTrue(taskListPanel.isListMatching(emptyList));

        //checking for creation of new task, when recur period is weekly
        commandBox.runCommand("clear");
        commandBox.runCommand(td.task13.getAddCommand());
        currentList = TestUtil.replaceTaskFromList(currentList, td.task13, 0);
        expectedList = TestUtil.replaceTaskFromList(expectedList, td.task14, 0);
        commandBox.runCommand("archive 1");
        assertTrue(taskListPanel.isListMatching(expectedList));

        //checking for creation of new task, when recur period is monthly
        commandBox.runCommand("clear");
        commandBox.runCommand(td.task15.getAddCommand());
        currentList = TestUtil.replaceTaskFromList(currentList, td.task15, 0);
        expectedList = TestUtil.replaceTaskFromList(expectedList, td.task16, 0);
        commandBox.runCommand("archive 1");
        assertTrue(taskListPanel.isListMatching(expectedList));

        //checking for creation of new task, when recur period is yearly
        commandBox.runCommand("clear");
        commandBox.runCommand(td.task17.getAddCommand());
        currentList = TestUtil.replaceTaskFromList(currentList, td.task17, 0);
        expectedList = TestUtil.replaceTaskFromList(expectedList, td.task18, 0);
        commandBox.runCommand("archive 1");
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
