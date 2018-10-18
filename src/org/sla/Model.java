package org.sla;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

// the Model contains all of the View's text so that it can be read and written to a file
public class Model {
    // These fields are the mutable text from the View
    private String topLabelText;
    private String topTextFieldText;
    private String bottomTextFieldText;
    private ArrayList<String> sideListViewTexts;

    // Create a model that is generic (did NOT get restored from saved data)
    Model() {
        topLabelText = "";
        topTextFieldText = "";
        bottomTextFieldText = "";
        sideListViewTexts = new ArrayList();

        // Try restoring saved text from file
        try {
            // Open file to read saved text
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));

                topLabelText = input.readLine();
                topTextFieldText = input.readLine();
                bottomTextFieldText = input.readLine();
                String newSideListText = input.readLine();
                while (newSideListText != null) {
                    sideListViewTexts.add(newSideListText);
                    newSideListText = input.readLine();
                }

                // Close file
                input.close();
            }
        } catch (Exception e) {
            System.out.println("Controller initialize EXCEPTION");
        }
    }

    // write the model to a file
    void save() {
        try {

            // Write the final model to a saved file
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                if (topLabelText != null) {
                    writer.write(topLabelText);
                } else {
                    writer.write("");
                }
                writer.newLine();
                if (topTextFieldText != null) {
                    writer.write(topTextFieldText);
                } else {
                    writer.write("");
                }
                writer.newLine();
                if (bottomTextFieldText != null) {
                    writer.write(bottomTextFieldText);
                } else {
                    writer.write("");
                }
                writer.newLine();
                int length = sideListViewTexts.size();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        writer.write(sideListViewTexts.get(i));
                        writer.newLine();
                    }
                } else {
                    writer.write("");
                    writer.newLine();
                }
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Model.save() failed!!!");
        }

    }

    // getter and setter methods for all of the Model's fields
    String getTopLabelText() {
        return topLabelText;
    }

    String getTopTextFieldText() {
        return topTextFieldText;
    }

    String getBottomTextFieldText() {
        return bottomTextFieldText;
    }

    ArrayList<String> getSideListViewTexts() {
        return sideListViewTexts;
    }

    void setAllData(String updatedTopLabelText, String updatedTopTextFieldText,
                    String updatedBottomTextFieldText, List<Label> updatedSideListView) {
        // Update the model with all text currently seen in View
        topLabelText = updatedTopLabelText;
        topTextFieldText = updatedTopTextFieldText;
        bottomTextFieldText = updatedBottomTextFieldText;

        int length = updatedSideListView.size();
        sideListViewTexts.clear();
        for (int i = 0; i < length; i++) {
            sideListViewTexts.add(updatedSideListView.get(i).getText());
        }
    }
}
