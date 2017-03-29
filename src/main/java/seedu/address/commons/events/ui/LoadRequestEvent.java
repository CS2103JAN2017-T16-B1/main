package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
//@@author A0140072X
/**
 * An event to change which file data will be loaded from.
 */
public class LoadRequestEvent extends BaseEvent {
    public String filePath;

    public LoadRequestEvent(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    public String getFilePath() {
        return this.filePath;
    }

}
