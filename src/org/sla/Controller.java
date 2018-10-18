package org.sla;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

// the Controller keeps track of the View's GUI controls AND has code to handle their events
public class Controller {

    // these fields identify each GUI control drawn in View
    public Label topLabel;
    public TextField topTextField;
    public ListView<Label> sideListView;
    public TextField bottomTextField;

    // the Model field keeps track of previously-saved and updated text in GUI that needs to be saved
    private Model model;

    public void initialize() {
        System.out.println("Controller initialize");

        // Create the model for the saved data
        model = new Model();

        // Now that model has been initialized from a file, update View with saved values from Model
        topLabel.setText(model.getTopLabelText());
        topTextField.setText(model.getTopTextFieldText());
        bottomTextField.setText(model.getBottomTextFieldText());
        ArrayList sideListViewTexts = model.getSideListViewTexts();
        for (int i = 0; i < sideListViewTexts.size(); i++) {
            sideListView.getItems().add(new Label((String)sideListViewTexts.get(i)));
        }
    }

    void save() {
        System.out.println("Controller save");

        // push the latest GUI text into the model
        model.setAllData(topLabel.getText(), topTextField.getText(),
                         bottomTextField.getText(), sideListView.getItems());
        model.save();
    }

    // these methods are event handler methods that are called when each GUI control is used
    public void topTextFieldClear() {
        System.out.println("topTextFieldClear: " + topTextField.getText());

        // Keep the top label intact but clear the text field
        topLabel.setText(topTextField.getText());
        topTextField.setText("");
    }

    public void topTextFieldUpdated() {
        System.out.println("topTextFieldUpdated: " + topTextField.getText());

        // Update the top label when the top text field is updated.
        if (topTextField.getText().length() > 0) {
            topLabel.setText(topTextField.getText());
        }
    }

    public void bottomTextFieldReady() {
        System.out.println("bottomTextFieldReady: " + bottomTextField.getText());

        // Update the list view with the text from the bottom text field
        sideListView.getItems().add(new Label(bottomTextField.getText()));
        // Clear the bottom text field because it has been used.
        bottomTextField.setText("");
    }

}
