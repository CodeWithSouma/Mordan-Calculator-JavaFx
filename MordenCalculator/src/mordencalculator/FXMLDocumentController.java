/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mordencalculator;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author souma
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField display;

    private double numberOne=0, numberTwo=0;
    private String operator=null;
    private DecimalFormat numberFormat;
    private boolean resultCalculated = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void offButtonAction(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void allClearButtonAction(ActionEvent event) {
        display.setText("");
        numberOne = 0;
        numberTwo = 0;
        operator = "";
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

        if (display.getText().length() != 0) {
            display.setText(display.getText().substring(0, (display.getText().length() - 1)));
        }

    }

    @FXML
    private void operatorsAction(ActionEvent event) {
        if (display.getText().length() != 0) {
            operator = ((Button) event.getSource()).getText();
            if(!isNumeric(display.getText())){
            display.setText("Invalid input.");
            resultCalculated=true;
           }
            else{
                numberOne = Double.parseDouble(display.getText());
                display.setText("");
            } 
           
        }

    }

    @FXML
    private void numberButtonAction(ActionEvent event) {

        if (resultCalculated) {
            display.setText("");
            resultCalculated = false;
        }

        display.setText(display.getText() + ((Button) event.getSource()).getText());

    }

    @FXML
    private void percentageButtonAction(ActionEvent event) {
        if (display.getText().length() != 0) {
            display.setText(Double.toString(Double.parseDouble(display.getText()) / 100));
            resultCalculated = true;
        }
    }

    @FXML
    private void resultButtonAction(ActionEvent event) {
        if(!isNumeric(display.getText())){
            display.setText("Invalid input.");
        }
        
       else if ((display.getText().length() != 0)&&(!(operator==null))) {

            numberTwo = Double.parseDouble(display.getText());
            if (operator.equals("+")) {
                display.setText(new DecimalFormat().format((numberOne + numberTwo)));

            } else if (operator.equals("-")) {
                display.setText(new DecimalFormat().format((numberOne - numberTwo)));

            } else if (operator.equals("X")) {
                display.setText(new DecimalFormat().format((numberOne * numberTwo)));

            } else if (operator.equals("/")) {
                display.setText(new DecimalFormat().format((numberOne / numberTwo)));
            }

            
        }
        resultCalculated = true;

    }

    @FXML
    private void dotButtonAction(ActionEvent event) {

        if (!(display.getText().contains("."))) {
            display.setText(display.getText() + ".");
        }

    }
    
    
    
    public static boolean isNumeric(String string) {
      return string.matches("^[-+]?\\d+(\\.\\d+)?$");
  }

}
