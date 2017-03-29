package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
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
