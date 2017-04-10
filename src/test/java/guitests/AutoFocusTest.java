package guitests;
//@@author A0139509X
import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class AutoFocusTest extends TaskManagerGuiTest {

    @Test
    public void autoFocusWhileOutOfCommandBox() {
        mainMenu.useTabKey();
        assertEquals("", commandBox.getCommandInput());
        mainMenu.useKeyWordA();
        assertEquals("a", commandBox.getCommandInput());
    }

    @Test
    public void autoFocusFailWithHelpWindow() {
        mainMenu.openHelpWindowUsingAccelerator();
        assertEquals("", commandBox.getCommandInput());
        mainMenu.useKeyWordA();
        assertEquals("", commandBox.getCommandInput());
    }
}
