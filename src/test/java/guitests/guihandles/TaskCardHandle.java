package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.model.Task.TaskStringReference;
import seedu.taskManager.model.tag.UniqueTagList;

/**
 * Provides a handle to a task card in the task list panel.
 */
public class TaskCardHandle extends GuiHandle {
    private static final String NAME_FIELD_ID = "#name";
    private static final String ENDTIME_FIELD_ID = "#endTime";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String STARTTIME_FIELD_ID = "#startTime";
    private static final String TAGS_FIELD_ID = "#tags";
    private static final String RECURPERIOD_FIELD_ID = "#recurPeriod";
    private static final String RECURENDDATE_FIELD_ID = "#recurEndDate";

    private Node node;

    public TaskCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node) {
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getFullName() {
        return getTextFromLabel(NAME_FIELD_ID);
    }

    public String getEndTime() {
        return getTextFromLabel(ENDTIME_FIELD_ID);
    }

    public String getDescription() {
        return getTextFromLabel(DESCRIPTION_FIELD_ID);
    }

    public String getStartTime() {
        return getTextFromLabel(STARTTIME_FIELD_ID);
    }

    public String getRecurPeriod() {
        return getTextFromLabel(RECURPERIOD_FIELD_ID);
    }

    public String getRecurEndDate() {
        return getTextFromLabel(RECURENDDATE_FIELD_ID);
    }

    public List<String> getTags() {
        return getTags(getTagsContainer());
    }

    private List<String> getTags(Region tagsContainer) {
        return tagsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(node -> ((Labeled) node).getText())
                .collect(Collectors.toList());
    }

    private List<String> getTags(UniqueTagList tags) {
        return tags
                .asObservableList()
                .stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());
    }

    private Region getTagsContainer() {
        return guiRobot.from(node).lookup(TAGS_FIELD_ID).query();
    }

    public boolean isSameTask(ReadOnlyTask task) {

        System.out.println("fullname :" + getFullName());
        System.out.println("description :" + getDescription());
        System.out.println("startTime :" + getStartTime());
        System.out.println("EndTime :" + getEndTime());
        System.out.println("RecurPeriod :" + getRecurPeriod());
        System.out.println("RecurEndDate :" + getRecurEndDate());

        System.out.println("fullnameTask :" + task.getName().fullName);
        System.out.println("descriptionTask :" + task.getDescription().description);
        System.out.println("startTimeTask :" + task.getStartTime().startTime);
        System.out.println("EndTimeTask :" + task.getEndTime().endTime);
        System.out.println("RecurPeriodTask :" + task.getRecurPeriod().period);
        System.out.println("RecurEndDateTask :" + task.getRecurEndDate().endDate);
        return //task != null ||
                getFullName().equals(task.getName().fullName)
                && getTags().equals(getTags(task.getTags()))
                && checkForDescription(task)
                && checkForStartTime(task)
                && checkForEndTime(task)
                && checkForRecurPeriod(task)
                && checkForRecurEndDate(task);
    }

    //@@author A0139509X
    private boolean checkForRecurEndDate(ReadOnlyTask task) {
        if (getRecurEndDate().equals("$recurEndDate")) {
            if (task.getRecurEndDate().endDate.equals(TaskStringReference.EMPTY_RECUR_END_DATE)) {
                System.out.println("I'm here! and my recurEndDate works!\n");
                return true;
            }
        } else {
            if (getRecurEndDate().equals("Recur End Date : " + task.getRecurEndDate().toString())) {
                System.out.println("I'm here! and my recurEndDate works!\n");
                return true;
            }
        }
        return false;
    }

    private boolean checkForRecurPeriod(ReadOnlyTask task) {
        if (getRecurPeriod().equals("$recurPeriod")) {
            if (task.getRecurPeriod().period.equals(TaskStringReference.EMPTY_PERIOD)) {
                System.out.println("I'm here! and my recurPeriod works!\n");
                return true;
            }
        } else {
            if (task.getRecurPeriod().period.matches("[0-9]+")) {
                return getRecurPeriod().equals("Recur Period : " + task.getRecurPeriod().period + " days");
            } else {
                return getRecurPeriod().equals("Recur Period : " + task.getRecurPeriod().period);
            }
        }
        return false;
    }

    private boolean checkForEndTime(ReadOnlyTask task) {
        if (getEndTime().equals("$endTime")) {
            if (task.getEndTime().endTime.equals(TaskStringReference.EMPTY_TIME)) {
                System.out.println("I'm here! and my endTime works!\n");
                return true;
            }
        } else {
            if (getEndTime().equals("End Time : " + task.getEndTime().endTime)) {
                System.out.println("I'm here! and my endTime works!\n");
                return true;
            }
        }
        return false;
    }

    private boolean checkForStartTime(ReadOnlyTask task) {
        if (getStartTime().equals("$startTime")) {
            if (task.getStartTime().startTime.equals(TaskStringReference.EMPTY_TIME)) {
                System.out.println("I'm here! and my startTime works!\n");
                return true;
            }
        } else {
            if (getStartTime().equals("Start Time : " + task.getStartTime().startTime)) {
                System.out.println("I'm here! and my startTime works!\n");
                return true;
            }
        }
        return false;
    }

    private boolean checkForDescription(ReadOnlyTask task) {
        if (getDescription().equals("$description")) {
            if (task.getDescription().description.equals(TaskStringReference.EMPTY_DESCRIPTION)) {
                System.out.println("I'm here! and my description works!\n");
                return true;
            }
        } else {
            if (getDescription().equals("Description : " + task.getDescription().description)) {
                System.out.println("I'm here! and my description works!\n");
                return true;
            }
        }
        return false;
    }

    //@@author
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskCardHandle) {
            TaskCardHandle handle = (TaskCardHandle) obj;
            return getFullName().equals(handle.getFullName())
                    && getDescription().equals(handle.getDescription())
                    && getStartTime().equals(handle.getStartTime())
                    && getEndTime().equals(handle.getEndTime())
                    && getTags().equals(handle.getTags());
                    /*&& getRecurPeriod().equals(handle.getRecurPeriod())
                    && getRecurEndDate().equals(handle.getRecurEndDate())*/
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getFullName() + " " + getEndTime();
    }
}
