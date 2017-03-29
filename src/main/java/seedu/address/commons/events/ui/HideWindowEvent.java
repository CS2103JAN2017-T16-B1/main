package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
//@@author A0140072X
/**
 * An event to hide Window.
 */
public class HideWindowEvent extends BaseEvent {


    public HideWindowEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
