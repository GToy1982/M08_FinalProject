package com.finalproject;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainAppGUI extends Application {
    private TextField currentTimeZoneInput;
    private TextField localTimeMillisInput;
    private TextField selectedLocationInput;
    private TextArea outputTextArea;

    private static final Map<String, String> timeZoneMappings = new HashMap<>();

    static {
        timeZoneMappings.put("GMT", "GMT");
        timeZoneMappings.put("EST", "America/New_York");
        timeZoneMappings.put("PST", "America/Los_Angeles");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Zone Converter");

        // Create UI elements
        currentTimeZoneInput = new TextField();
        localTimeMillisInput = new TextField();
        selectedLocationInput = new TextField();
        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        Button convertButton = new Button("Convert Time");
        convertButton.setOnAction(e -> handleConvertButton());

        Button clearButton = new Button("Clear All");
        clearButton.setOnAction(e -> handleClearButton());

        // Create layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Add UI elements to the layout
        grid.add(new Label("Current Time Zone (GMT, EST, PST):"), 0, 0);
        grid.add(currentTimeZoneInput, 1, 0);
        grid.add(new Label("Local Time (milliseconds since epoch):"), 0, 1);
        grid.add(localTimeMillisInput, 1, 1);
        grid.add(new Label("Selected Location (GMT, EST, PST):"), 0, 2);
        grid.add(selectedLocationInput, 1, 2);
        grid.add(convertButton, 1, 3);
        grid.add(clearButton, 2, 3);
        grid.add(new Label("Converted Time:"), 0, 4);
        grid.add(outputTextArea, 1, 4);

        // Set up the scene
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void handleConvertButton() {
        try {
            String currentTimeZone = currentTimeZoneInput.getText().toUpperCase();
            String selectedLocation = selectedLocationInput.getText().toUpperCase();

            if (!isValidTimeZone(currentTimeZone)) {
                showError("Invalid current time zone. Please enter GMT, EST, or PST.");
                return;
            }

            long localTimeMillis = parseLocalTimeMillis();
            if (localTimeMillis == -1) {
                showError("Invalid local time. Please enter a valid long value.");
                return;
            }

            if (!isValidTimeZone(selectedLocation)) {
                showError("Invalid selected location. Please enter GMT, EST, or PST.");
                return;
            }

            MainApp mainApp = new MainApp(currentTimeZone, localTimeMillis, selectedLocation);
            TimeZoneConverter timeZoneConverter = new TimeZoneConverter();
            String convertedTime = timeZoneConverter.convertTime(mainApp);

            outputTextArea.setText(convertedTime);
        } catch (Exception e) {
            showError("Error during conversion. Please check your inputs.");
        }
    }

    private boolean isValidTimeZone(String timeZone) {
        return timeZoneMappings.containsKey(timeZone);
    }

    private long parseLocalTimeMillis() {
        try {
            return Long.parseLong(localTimeMillisInput.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleClearButton() {
        currentTimeZoneInput.clear();
        localTimeMillisInput.clear();
        selectedLocationInput.clear();
        outputTextArea.clear();
    }

    private void showError(String message) {
        outputTextArea.setText("Error: " + message);
    }
}

