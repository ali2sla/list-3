package org.sla;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
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

        // Try restoring saved text from file
        try {
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));
                model = new Model(input);
                input.close();
            } else {
                model = new Model();
            }
        } catch (Exception e) {
            System.out.println("Controller initialize EXCEPTION");
            model = new Model();
        }

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

        // Update the model with final text typed in View
        model.setTopLabelText(topLabel.getText());
        model.setTopTextFieldText(topTextField.getText());
        model.setBottomTextFieldText(bottomTextField.getText());
        int length = sideListView.getItems().size();
        model.getSideListViewTexts().clear();
        for (int i = 0; i < length; i++) {
            model.addToSideListViewTexts(sideListView.getItems().get(i).getText());
        }

        // Write the final model to a saved file
        try {
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                model.save(writer);
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Controller save EXCEPTION");
        }
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
