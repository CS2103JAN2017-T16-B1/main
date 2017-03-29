package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.Task.ReadOnlyTask;


public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;
    //@FXML
    //private ImageView pinimage;
    //@FXML
    //private ImageView icon;

    public PersonCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        setTextForDescription(task);
        setTextForStartTime(task);
        setTextForEndTime(task);
        initTags(task);
        //setPriorityIcons(task);
        setColours(task);
    }

    private void setColours(ReadOnlyTask task) {
		if(task.getPriority().toString().equals("h")) {
			cardPane.setStyle("-fx-background-color: #FEF0ED;");
		} else if(task.getPriority().toString().equals("m")){
			cardPane.setStyle("-fx-background-color: #EDF5FE;");
		} else if(task.getPriority().toString().equals("l")){
			cardPane.setStyle("-fx-background-color: #EEFEED;");
		}
		
	}

	/*private void setPriorityIcons(ReadOnlyTask task) {
		pinimage.setImage(new Image("/images/clock.png"));
	}*/

	private void setTextForEndTime(ReadOnlyTask task) {
		if(!(task.getEndTime().endTime.equals(""))){
			address.setText("End Time : " +task.getEndTime().endTime);
        } else if(task.getStartTime().startTime.equals("")){
        	address.setManaged(false);
        }
	}

	private void setTextForStartTime(ReadOnlyTask task) {
		if(!(task.getStartTime().startTime.equals(""))){
        	email.setText("Start Time : " + task.getStartTime().startTime);
        } else if(task.getStartTime().startTime.equals("")){
        	email.setManaged(false);
        }
	}

	private void setTextForDescription(ReadOnlyTask task) {
		if(!(task.getDescription().description.equals("\n"))){
        	phone.setText("Description : " + task.getDescription().description);
        } else if(task.getDescription().description.equals("\n")){
        	phone.setManaged(false);
        }
	}

	private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
