package seedu.taskManager.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.taskManager.commons.core.LogsCenter;
import seedu.taskManager.commons.events.ui.NewResultAvailableEvent;
import seedu.taskManager.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.taskManager.commons.util.FxViewUtil;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(ResultDisplay.class);
    private static final String FXML = "ResultDisplay.fxml";

    private final StringProperty displayed = new SimpleStringProperty("");

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay(AnchorPane placeHolder) {
        super(FXML);
        resultDisplay.textProperty().bind(displayed);
        FxViewUtil.applyAnchorBoundaryParameters(resultDisplay, 0.0, 0.0, 0.0, 0.0);
        FxViewUtil.applyAnchorBoundaryParameters(mainPane, 0.0, 0.0, 0.0, 0.0);
        placeHolder.getChildren().add(mainPane);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        displayed.setValue(event.message);
    }
    //@@author A0139509X
    @Subscribe
    private void handleTaskPanelSelectionChangedEvent(TaskPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        displayed.setValue(event.getNewSelection().getAsText());
    }
}
