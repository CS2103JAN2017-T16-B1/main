package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
//@@author A0140072X
/**
 * An event to change which directory the data will be saved to.
 */
public class SaveRequestEvent extends BaseEvent {
    public String filePath;

    public SaveRequestEvent(String filePath) {
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
