package com.example.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Division;

    @FXML
    private Button Multiplication;

    @FXML
    private Button Subtraction;

    @FXML
    private TextField TextField;

    @FXML
    private Button addition;

    @FXML
    private Button btn0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button equals;

    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @FXML
   public void handleNumberButton(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (start) {
            TextField.setText(value);
            start = false;
        } else {
            TextField.setText(TextField.getText() + value);
        }
    }

    @FXML
   public void handleOperationButton(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty()) return;
            operator = value;
            num1 = Double.parseDouble(TextField.getText());
            start = true;
        } else {
            handleEqualsButton(event);
        }
    }

    @FXML
    public void handleEqualsButton(ActionEvent event) {
        double num2 = Double.parseDouble(TextField.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    TextField.setText("Ошибка!");
                    operator = "";
                    return;
                }
                break;
        }
        TextField.setText(String.valueOf(result));
        operator = "";
        start = true;
    }

    @FXML
    void initialize() {
    }
}