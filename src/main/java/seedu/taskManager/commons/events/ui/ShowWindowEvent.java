package seedu.taskManager.commons.events.ui;

import seedu.taskManager.commons.events.BaseEvent;
//@@author A0140072X
/**
 * An event to show Window.
 */
public class ShowWindowEvent extends BaseEvent {


    public ShowWindowEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
