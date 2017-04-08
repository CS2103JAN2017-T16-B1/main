package guitests;
//@@author A0139509X
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.TestTask;

public class ToggleCommandTest extends TaskManagerGuiTest {

    @Test
    public void toggleUsingCommandBox() {
        runToggleCommandByTyping("toggle");
        assertToggleCommandBoxEventSuccess(td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
        runToggleCommandByTyping("toggle");
        assertToggleCommandBoxTaskSuccess();
        runToggleCommandByTyping("toggle");
        assertToggleCommandBoxFloatingSuccess();
        runToggleCommandByTyping("toggle");
        assertToggleCommandBoxAllSuccess(td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
    }

    @Test
    public void toggleUsingHotkey() {
        pressHotKeyForToggle();
        assertToggleCommandBoxEventSuccess(td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
        pressHotKeyForToggle();
        assertToggleCommandBoxTaskSuccess();
        pressHotKeyForToggle();
        assertToggleCommandBoxFloatingSuccess();
        pressHotKeyForToggle();
        assertToggleCommandBoxAllSuccess(td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
    }

    @Test
    public void toggleWithOtherCommands() {
        //check for toggle to work after executing find function
        commandBox.runCommand("find Midterm");
        pressHotKeyForToggle();
        assertToggleCommandBoxRevertToListAllSuccess(td.task1, td.task2, td.task3, td.task4, td.task5, td.task6, td.task7);
    }
    
    private void assertToggleCommandBoxRevertToListAllSuccess(TestTask ...expectedHits) {
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }

    private void pressHotKeyForToggle() {
        mainMenu.useTabKey();
    }

    private void assertToggleCommandBoxAllSuccess(TestTask ...expectedHits) {
        assertListSize(expectedHits.length);
        assertResultMessage("Toggled to list ALL\n" + expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
    
    private void assertToggleCommandBoxFloatingSuccess(TestTask ...expectedHits) {
        assertListSize(expectedHits.length);
        assertResultMessage("Toggled to list FLOATING_TASK\n" + expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
    
    private void assertToggleCommandBoxTaskSuccess(TestTask ...expectedHits) {
        assertListSize(expectedHits.length);
        assertResultMessage("Toggled to list TASK\n" + expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
    
    private void assertToggleCommandBoxEventSuccess(TestTask ...expectedHits) {
        assertListSize(expectedHits.length);
        assertResultMessage("Toggled to list EVENT\n" + expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
    
    private void runToggleCommandByTyping(String string) {
        commandBox.runCommand("toggle");
    }
}
