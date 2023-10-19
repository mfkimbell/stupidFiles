package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {
    public String optionSelection = "";
    @FXML private ComboBox OptionsMenu;
    @FXML private TextArea MessagesField;

    // Initializes the Options dropdown
    @FXML
    public void initialize() {
        OptionsMenu.getItems().addAll("Option 1", "Option 2");
        OptionsMenu.setOnAction(selectOption);
    }

    // Handles the Selection Event on ComboBox 'OptionsMenu'
    EventHandler<ActionEvent> selectOption = new EventHandler<ActionEvent>() {
        // Gets Selected Item from ComboBox
        @Override
        public void handle(ActionEvent actionEvent) {
            optionSelection = OptionsMenu.getValue().toString();
        }
    };

    @FXML
    void onDelete(ActionEvent action){MessagesField.appendText(optionSelection + " deleted\n");}

    @FXML
    void onSelect(ActionEvent action){
        MessagesField.appendText(optionSelection + " selected\n");
    }

}