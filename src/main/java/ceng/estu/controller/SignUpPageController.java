package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.PageSystem;
import ceng.estu.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class SignUpPageController implements Controllable, Initializable {
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

            try {
                DBHandler.signUp(
                        input_signUpUsername.getText(),
                        input_signUpPassword.getText(),
                        input_SignUpEmail.getText(),
                        input_SignUpName.getText(),
                        input_SignUpSurname.getText(),
                        input_SignUpAdress.getText(),
                        input_signUpPhoneNumber.getText()
                );
            }catch (Exception e){
                AlertSystem.getAlert(ErrorType.ERROR, "Couldnt sign Up!");
                return;
            }
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");


        }
    }

    public void fillAreas(String username, String password){
        input_signUpUsername.setText(username);
        input_signUpPassword.setText(password);
        input_signUpUsername.toFront(); //To get next free text area.
    }

    private boolean validateInputs(){
        StringBuilder sb = new StringBuilder();
        if(input_signUpUsername.getText().length() < 1){
            sb.append("Your username length must be bigger than 4.\n");
        }
        if(input_signUpPassword.getText().length() < 1){
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
        /*if(input_signUpPhoneNumber.getText().length() < 7){
            sb.append("Your phone number length must be bigger than 7.\n");
        }*/
        /*if(input_SignUpAdress.getText().length() < 15){
            sb.append("Your address length must be bigger than 20.\n");
        }*/

        if(sb.toString().length() == 0)return true;
        else{
            AlertSystem.getAlert(ErrorType.ERROR,sb.toString());
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip tt = new Tooltip("0(xxx)xxx xxx xxx");
        tt.setShowDelay(Duration.ONE);
        input_signUpPhoneNumber.setTooltip(tt);
    }
}
