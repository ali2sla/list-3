package org.sla;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    }

    // Create a model that is restored from saved data
    Model(BufferedReader input) {
        System.out.println("Model(BufferedReader input)");
        try {
            topLabelText = input.readLine();
            topTextFieldText = input.readLine();
            bottomTextFieldText = input.readLine();
            sideListViewTexts = new ArrayList();
            String newSideListText = input.readLine();
            while (newSideListText != null) {
                sideListViewTexts.add(newSideListText);
                newSideListText = input.readLine();
            }
        } catch (Exception e) {
            System.out.println("Model reading failed!!!");
        }
    }

    // write the model to a file
    void save(BufferedWriter output) {
        try {
            if (topLabelText != null) {
                output.write(topLabelText);
            } else {
                output.write("");
            }
            output.newLine();
            if (topTextFieldText != null) {
                output.write(topTextFieldText);
            } else {
                output.write("");
            }
            output.newLine();
            if (bottomTextFieldText != null) {
                output.write(bottomTextFieldText);
            } else {
                output.write("");
            }
            output.newLine();
            int length = sideListViewTexts.size();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    output.write(sideListViewTexts.get(i));
                    output.newLine();
                }
            } else {
                output.write("");
                output.newLine();
            }
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
