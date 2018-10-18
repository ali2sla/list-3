package org.sla;

import java.io.*;
import java.util.ArrayList;

// the Model contains all of the View's text so that it can be read and written to a file
public class Model {
    // These fields are the mutable text from the View
    private String topLabelText;
    private String topTextFieldText;
    private String bottomTextFieldText;
    private ArrayList<String> sideListViewTexts;

    // Create a model that is generic (did NOT get restored from saved data)
    Model() {
        System.out.println("Model()");
        topLabelText = "";
        topTextFieldText = "";
        bottomTextFieldText = "";
        sideListViewTexts = new ArrayList();

        // Try restoring saved text from file
        try {
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));
                topLabelText = input.readLine();
                topTextFieldText = input.readLine();
                bottomTextFieldText = input.readLine();
                sideListViewTexts = new ArrayList();
                String newSideListText = input.readLine();
                while (newSideListText != null) {
                    sideListViewTexts.add(newSideListText);
                    newSideListText = input.readLine();
                }
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

            System.out.println("Model writing failed!!!");
            e.printStackTrace();
        }

    }

    // getter and setter methods for all of the Model's fields
    String getTopLabelText() {
        return topLabelText;
    }
    void setTopLabelText(String updatedText) {
        topLabelText = updatedText;
    }

    String getTopTextFieldText() {
        return topTextFieldText;
    }
    void setTopTextFieldText(String updatedText) {
        topTextFieldText = updatedText;
    }

    String getBottomTextFieldText() {
        return bottomTextFieldText;
    }
    void setBottomTextFieldText(String updatedText) {
        bottomTextFieldText = updatedText;
    }

    ArrayList getSideListViewTexts() {
        return sideListViewTexts;
    }
    void addToSideListViewTexts(String updatedText) {
        sideListViewTexts.add(updatedText);
    }
}
