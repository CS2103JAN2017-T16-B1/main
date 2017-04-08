package guitests.guihandles;

import guitests.GuiRobot;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * A handle to the Command Box in the GUI.
 */
public class CommandBoxHandle extends GuiHandle {

    private static final String COMMAND_INPUT_FIELD_ID = "#commandTextField";

    public CommandBoxHandle(GuiRobot guiRobot, Stage primaryStage, String stageTitle) {
        super(guiRobot, primaryStage, stageTitle);
    }

    /**
     * Clicks on the TextField.
     */
    public void clickOnTextField() {
        guiRobot.clickOn(COMMAND_INPUT_FIELD_ID);
    }

    public void enterCommand(String command) {
        setTextField(COMMAND_INPUT_FIELD_ID, command);
    }

    public String getCommandInput() {
        return getTextFieldText(COMMAND_INPUT_FIELD_ID);
    }

    /**
     * Enters the given command in the Command Box and presses enter.
     */
    public void runCommand(String command) {
        //to solve the problem of not clicking commandTextField when executing enter
        guiRobot.type(KeyCode.B);
        guiRobot.type(KeyCode.BACK_SPACE);
        
        enterCommand(command);
        pressEnter();
        guiRobot.sleep(200); //Give time for the command to take effect
    }
    public void enterKey() {
        focusOnMainApp();
        guiRobot.press(KeyCode.ENTER);
        guiRobot.sleep(200);
    }

    public HelpWindowHandle runHelpCommand() {
        guiRobot.type(KeyCode.H);
        guiRobot.type(KeyCode.E);
        guiRobot.type(KeyCode.L);
        guiRobot.type(KeyCode.P);
        //enterCommand("help");
        pressEnter();
        return new HelpWindowHandle(guiRobot, primaryStage);
    }

    public ObservableList<String> getStyleClass() {
        return getNode(COMMAND_INPUT_FIELD_ID).getStyleClass();
    }
}
