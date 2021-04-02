package com.joshrootdev.tempconvert.controllers;

import com.joshrootdev.tempconvert.Main;
import com.joshrootdev.tempconvert.util.ConversionUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindowController implements Initializable {
    @FXML public TextField fromField;
    @FXML public TextField toField;
    @FXML public ComboBox<String> conversionTypeBox;

    DecimalFormat formatter = new DecimalFormat("#.##");

    char degree = '\u00B0';

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFieldText(fromField, newValue);
            }
        });

        fromField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    MainWindowController.this.convert();
                }
            }
        });
    }

    public void reset() {
        fromField.clear();
        toField.clear();
        conversionTypeBox.getSelectionModel().select(-1);
    }

    public void convert() {
        int type = conversionTypeBox.getSelectionModel().getSelectedIndex();

        double fromDegrees = type > -1 ? Double.parseDouble(fromField.getText()) : 0;

        switch(type) {
            case 0:
                toField.setText(formatter.format(ConversionUtil.convertFahrenheitToCelsius(fromDegrees)) + "" + degree + "C");
                break;
            case 1:
                toField.setText(formatter.format(ConversionUtil.convertFahrenheitToKelvin(fromDegrees)) + "" + degree + "K");
                break;
            case 2:
                toField.setText(formatter.format(ConversionUtil.convertCelsiusToFahrenheit(fromDegrees)) + "" + degree + "F");
                break;
            case 3:
                toField.setText(formatter.format(ConversionUtil.convertCelsiusToKelvin(fromDegrees)) + "" + degree + "K");
                break;
            case 4:
                toField.setText(formatter.format(ConversionUtil.convertKelvinToFahrenheit(fromDegrees)) + "" + degree + "F");
                break;
            case 5:
                toField.setText(formatter.format(ConversionUtil.convertKelvinToCelsius(fromDegrees)) + "" + degree + "C");
                break;
            case -1:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error Occurred");
                alert.setHeaderText("No Conversion Type Selected");
                alert.setContentText("Error: You must select a conversion type!");
                alert.getButtonTypes().add(ButtonType.CLOSE);

                alert.showAndWait();
        }
    }

    public void close() {
        Main.mainWindow.close();
    }

    private void updateFieldText(TextField field, String newValue) {
        String editedText = "";

        int decimals = 0;

        Pattern numberPattern = Pattern.compile("[0-9]+");
        Pattern dotPattern = Pattern.compile("\\.");

        for (String character : newValue.split("")) {
            Matcher numberMatcher = numberPattern.matcher(character);
            Matcher dotMatcher = dotPattern.matcher(character);

            if (numberMatcher.matches()) {
                editedText += character;
            }

            if (dotMatcher.matches() && decimals < 1) {
                editedText += character;

                decimals++;
            }
        }

        field.setText(editedText);
    }
}
