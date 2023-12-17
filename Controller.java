package com.firstjavaapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Label welcomeLabel;
    @FXML
    public ChoiceBox <String>choiceBox;
    @FXML
    public TextField textField;
    @FXML
    public Button button;
    private static final String C_TO_F_TEXT="Celcius to Fahrenheit";
    private static final String F_TO_C_TEXT="Fahrenheit to Celcius";
    private boolean isC_TO_F_TEXT=true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll( C_TO_F_TEXT);
        choiceBox.getItems().addAll(F_TO_C_TEXT);
        choiceBox.setValue(C_TO_F_TEXT);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(C_TO_F_TEXT)) {
                isC_TO_F_TEXT=true;
            }else {
                isC_TO_F_TEXT=false;

            }

        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                convert();

            }
        });

    }


    private void convert() {
        String input=textField.getText();
        float enteredTemperature=0.0f;
        try {
          enteredTemperature=Float.parseFloat(input);
        }catch (Exception exception){
            warnUser();
            return;

        }
        float newTemperature=0.0f;
        if (isC_TO_F_TEXT){
            newTemperature=(enteredTemperature*9/5)+32;
        }else {
            newTemperature=(enteredTemperature-32)*5/9;
        }
        display(newTemperature);
    }

    private void warnUser() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured ");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Enter a valid input");
        alert.show();
    }

    private void display(float newTemperature) {
        String unit=isC_TO_F_TEXT?"F":"C";
        System.out.println("The new temperature is "+newTemperature+unit);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new temperature is "+newTemperature+unit);
        alert.show();
    }

}
