package seedu.address.ui;

import javafx.stage.Stage;
import seedu.address.logic.Logic;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Stops the UI. */
    void stop();

    void refresh();

    void loadData(Logic logic);

    void show();

    void hide();
}
