package guitests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.HelpWindowHandle;
import seedu.taskManager.logic.commands.HelpCommand;

public class HelpWindowTest extends TaskManagerGuiTest {

    @Test
    public void openHelpWindow() {
        //use accelerator
        commandBox.clickOnTextField();
        assertHelpWindowOpen(mainMenu.openHelpWindowUsingAccelerator());

        resultDisplay.clickOnTextArea();
        assertHelpWindowOpen(mainMenu.openHelpWindowUsingAccelerator());

        taskListPanel.clickOnListView();
        assertHelpWindowOpen(mainMenu.openHelpWindowUsingAccelerator());

        //use menu button
        //assertHelpWindowOpen(mainMenu.openHelpWindowUsingMenu());

        //use command
        assertHelpWindowOpen(commandBox.runHelpCommand());

        //A0139509X
        //checks output message
        assertResultMessage(String.format(HelpCommand.SHOWING_HELP_MESSAGE));
    }

    private void assertHelpWindowOpen(HelpWindowHandle helpWindowHandle) {
        assertTrue(helpWindowHandle.isWindowOpen());
        helpWindowHandle.closeWindow();
    }

    private void assertHelpWindowNotOpen(HelpWindowHandle helpWindowHandle) {
        assertFalse(helpWindowHandle.isWindowOpen());
    }

}
