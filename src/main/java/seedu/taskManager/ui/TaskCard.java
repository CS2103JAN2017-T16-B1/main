package seedu.taskManager.ui;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

//@@author A0139509X

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taskManager.commons.core.LogsCenter;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.model.Task.TaskStringReference;

public class TaskCard extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(TaskCard.class);

    private static final String LOW_PRIORITY_COLOR = "-fx-background-color: #EEFEED;";

    private static final String MEDIUM_PRIORITY_COLOR = "-fx-background-color: #EDF5FE;";

    private static final String HIGH_PRIORITY_COLOR = "-fx-background-color: #FEF0ED;";

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label recurPeriod;
    @FXML
    private Label recurEndDate;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView pinimage;
    //@FXML
    //private ImageView icon;

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        initValuesForNodes(task, displayedIndex);
    }

    public void initValuesForNodes(ReadOnlyTask task, int displayedIndex) {
        setTextForName(task);
        setTextForId(displayedIndex);
        setTextForDescription(task);
        setTextForStartTime(task);
        setTextForEndTime(task);
        setTextForRecurPeriod(task);
        setTextForRecurEndDate(task);
        initTags(task);
        setColours(task);
        setOverdueIcons(task);
    }

    public void setTextForId(int displayedIndex) {
        id.setText(displayedIndex + ". ");
    }

    public void setTextForName(ReadOnlyTask task) {
        name.setText(task.getName().fullName);
    }

    private void setTextForRecurEndDate(ReadOnlyTask task) {
        if (task.getRecurEndDate().endDate != TaskStringReference.EMPTY_RECUR_END_DATE) {
            recurEndDate.setText("Recur End Date : " + task.getRecurEndDate().endDate);
        } else if (task.getRecurEndDate().endDate == TaskStringReference.EMPTY_RECUR_END_DATE) {
            dontShowLabel(recurEndDate);
        }

    }

    private void setTextForRecurPeriod(ReadOnlyTask task) {
        if (!task.getRecurPeriod().period.equals(TaskStringReference.EMPTY_PERIOD)) {
            setRecurPeriod(task);
        } else if (task.getRecurPeriod().period.equals(TaskStringReference.EMPTY_PERIOD)) {
            dontShowLabel(recurPeriod);
        }

    }

    public void setRecurPeriod(ReadOnlyTask task) {
        if (task.getRecurPeriod().period.matches("[0-9]+")) {
            recurPeriod.setText("Recur Period : " + task.getRecurPeriod().period + " days");
        } else {
            recurPeriod.setText("Recur Period : " + task.getRecurPeriod().period);
        }
    }

    private void setColours(ReadOnlyTask task) {
        if (task.getPriority().toString().equals(TaskStringReference.PRIORITY_HIGH)) {
            cardPane.setStyle(HIGH_PRIORITY_COLOR);
            //cardPane.setStyle(".label-red");
        } else if (task.getPriority().toString().equals(TaskStringReference.PRIORITY_MEDIUM)) {
            cardPane.setStyle(MEDIUM_PRIORITY_COLOR);
        } else if (task.getPriority().toString().equals(TaskStringReference.PRIORITY_LOW)) {
            cardPane.setStyle(LOW_PRIORITY_COLOR);
        }

    }

    private void setOverdueIcons(ReadOnlyTask task) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmm");
        Date currentTime = new Date();
        String taskEndTimeString = task.getEndTime().endTime;
        Date taskEndTime = new Date();
        try {
            taskEndTime = dateFormat.parse(taskEndTimeString);
        } catch (ParseException e) {
            logger.info("parsing taskEndTime failed due to exception");
        }
        if (currentTime.after(taskEndTime)) {
            pinimage.setImage(new Image("/images/overdue.png"));
        }
    }

    private void setTextForEndTime(ReadOnlyTask task) {
        if (!(task.getEndTime().endTime.equals(TaskStringReference.EMPTY_TIME))) {
            endTime.setText("End Time : " + task.getEndTime().endTime);
        } else if (task.getEndTime().endTime.equals(TaskStringReference.EMPTY_TIME)) {
            dontShowLabel(endTime);
        }
    }

    private void setTextForStartTime(ReadOnlyTask task) {
        if (!(task.getStartTime().startTime.equals(TaskStringReference.EMPTY_TIME))) {
            startTime.setText("Start Time : " + task.getStartTime().startTime);
        } else if (task.getStartTime().startTime.equals(TaskStringReference.EMPTY_TIME)) {
            dontShowLabel(startTime);
        }
    }

    private void setTextForDescription(ReadOnlyTask task) {
        if (!(task.getDescription().description.equals(TaskStringReference.EMPTY_DESCRIPTION))) {
            description.setText("Description : " + task.getDescription().description);
        } else if (task.getDescription().description.equals(TaskStringReference.EMPTY_DESCRIPTION)) {
            dontShowLabel(description);
        }
    }

    private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void dontShowLabel(Label label) {
        label.setManaged(false);
    }
}
