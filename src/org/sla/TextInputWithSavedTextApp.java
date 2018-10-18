package org.sla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextInputWithSavedTextApp extends Application {
    private Controller myController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load View from xml description
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = loader.load();
        myController = loader.getController();

        // Display the scene
        primaryStage.setTitle("Text Input with Saved Text");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    @Override
    public void stop() {
        // When the Application stops, save all the text seen in GUI View
        myController.save();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
