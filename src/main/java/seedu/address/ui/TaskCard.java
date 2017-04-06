package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Task.ReadOnlyTask;
import seedu.address.model.Task.TaskStringReference;


public class TaskCard extends UiPart<Region> {

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
    //@FXML
    //private ImageView pinimage;
    //@FXML
    //private ImageView icon;

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        //@@author A0139509X
        setTextForDescription(task);
        setTextForStartTime(task);
        setTextForEndTime(task);
        setTextForRecurPeriod(task);
        initTags(task);
        //setPriorityIcons(task);
        setColours(task);
    }
    private void setTextForRecurPeriod(ReadOnlyTask task) {
    	if(task.getRecurPeriod().period != null){
			recurPeriod.setText("Recur Period : " +task.getRecurPeriod().period);
        } else if(task.getRecurPeriod().period == null){
        	recurPeriod.setManaged(false);
        }
    	
    	if(task.getRecurEndDate().endDate != null){
			recurEndDate.setText("Recur End Date : " +task.getRecurEndDate().endDate);
        } else if(task.getRecurEndDate().endDate == null){
        	recurEndDate.setManaged(false);
        }
    	
	}
	//@@author A0139509X
    private void setColours(ReadOnlyTask task) {
		if(task.getPriority().toString().equals(TaskStringReference.PRIORITY_HIGH)) {
			cardPane.setStyle(HIGH_PRIORITY_COLOR);
			//cardPane.setStyle(".label-red");
		} else if(task.getPriority().toString().equals(TaskStringReference.PRIORITY_MEDIUM)){
			cardPane.setStyle(MEDIUM_PRIORITY_COLOR);
		} else if(task.getPriority().toString().equals(TaskStringReference.PRIORITY_LOW)){
			cardPane.setStyle(LOW_PRIORITY_COLOR);
		}
		
	}

	/*private void setPriorityIcons(ReadOnlyTask task) {
		pinimage.setImage(new Image("/images/clock.png"));
	}*/

	private void setTextForEndTime(ReadOnlyTask task) {
		if(!(task.getEndTime().endTime.equals(TaskStringReference.EMPTY_TIME))){
			endTime.setText("End Time : " +task.getEndTime().endTime);
        } else if(task.getEndTime().endTime.equals(TaskStringReference.EMPTY_TIME)){
        	endTime.setManaged(false);
        }
	}

	private void setTextForStartTime(ReadOnlyTask task) {
		if(!(task.getStartTime().startTime.equals(TaskStringReference.EMPTY_TIME))){
        	startTime.setText("Start Time : " + task.getStartTime().startTime);
        } else if(task.getStartTime().startTime.equals(TaskStringReference.EMPTY_TIME)){
        	startTime.setManaged(false);
        }
	}

	private void setTextForDescription(ReadOnlyTask task) {
		if(!(task.getDescription().description.equals(TaskStringReference.EMPTY_DESCRIPTION))){
        	description.setText("Description : " + task.getDescription().description);
        } else if(task.getDescription().description.equals(TaskStringReference.EMPTY_DESCRIPTION)){
        	description.setManaged(false);
        }
	}

	private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
