// This class represents the JavaFXUI for the AI Code Commenter application
// It allows users to input code and generate AI comments for it

package com.aicodecommenter.ui;

import com.aicodecommenter.service.AIService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFXUI extends Application {

    @Override
    public void start(Stage stage) {

        // Creating ComboBox to select programming language
        ComboBox<String> languageBox = new ComboBox<>();
        languageBox.getItems().addAll("Java", "Python", "C++");
        languageBox.setValue("Java");

        // Creating TextArea for code input
        TextArea codeInput = new TextArea();
        codeInput.setPromptText("Paste your code here...");
        codeInput.setPrefHeight(300);

        // Creating TextArea for AI comments output
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPromptText("AI comments will appear here...");
        outputArea.setPrefHeight(300);

        // Creating Button to generate comments
        Button generateBtn = new Button("Generate Comments");
        generateBtn.setOnAction(e -> {
            String lang = languageBox.getValue();
            String code = codeInput.getText();

            if (code.isBlank()) {
                outputArea.setText("Please enter some code.");
                return;
            }

            outputArea.setText("Generating comments...");
            String result = AIService.generateComments(lang, code);
            outputArea.setText(result);
        });

        // Creating layout for left and right panes
        VBox left = new VBox(10, new Label("Code"), codeInput);
        VBox right = new VBox(10, new Label("AI Comments"), outputArea);

        // Combining left and right panes horizontally
        HBox split = new HBox(10, left, right);
        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        // Creating root layout with language selection, code input, comments output, and generate button
        VBox root = new VBox(15, languageBox, split, generateBtn);
        root.setPadding(new Insets(15));

        // Creating scene and setting the stage
        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("AI Code Commenter");
        stage.setScene(scene);
        stage.show();
    }
}