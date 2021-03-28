package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.PageSystem;
import ceng.estu.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reuzun
 */
public class SignUpPageController implements Controllable {
    @javafx.fxml.FXML
    private Button btn_Complete;
    @javafx.fxml.FXML
    private TextField input_signUpUsername;
    @javafx.fxml.FXML
    private PasswordField input_signUpPassword;
    @javafx.fxml.FXML
    private TextField input_SignUpEmail;
    @javafx.fxml.FXML
    private TextArea input_SignUpAdress;
    @javafx.fxml.FXML
    private TextField input_signUpPhoneNumber;
    @javafx.fxml.FXML
    private TextField input_SignUpName;
    @javafx.fxml.FXML
    private TextField input_SignUpSurname;

    @javafx.fxml.FXML
    public void completeSignUp(ActionEvent actionEvent) {
        if( validateInputs() ) {
            String username = input_signUpUsername.getText();
            PageSystem.getPage(btn_Complete, "LogInPage");
            Main.getLastLoader().fillAreas(username, "");
            Main.setTitle("Welcome !");
        }
    }

    public void fillAreas(String username, String password){
        input_signUpUsername.setText(username);
        input_signUpPassword.setText(password);
        input_signUpUsername.toFront(); //To get next free text area.
    }

    private boolean validateInputs(){
        StringBuilder sb = new StringBuilder();
        if(input_signUpUsername.getText().length() < 4){
            sb.append("Your username length must be bigger than 4.\n");
        }
        if(input_signUpPassword.getText().length() < 5){
            sb.append("Your password length must be bigger than 5.\n");
        }
        if(!Validator.isValidEmailAddress(input_SignUpEmail.getText())){
            sb.append("Your email address is not valid.\n");
        }
        if(input_SignUpName.getText().length() < 1){
            sb.append("Your name length must be bigger than 0.\n");
        }
        if(input_SignUpSurname.getText().length() < 1){
            sb.append("Your surname length must be bigger than 0.\n");
        }
        if(input_signUpPhoneNumber.getText().length() < 7){
            sb.append("Your phone number length must be bigger than 7.\n");
        }
        if(input_SignUpAdress.getText().length() < 15){
            sb.append("Your address length must be bigger than 20.\n");
        }

        if(sb.toString().length() == 0)return true;
        else{
            AlertSystem.getAlert(ErrorType.ERROR,sb.toString());
            return false;
        }
    }

}
