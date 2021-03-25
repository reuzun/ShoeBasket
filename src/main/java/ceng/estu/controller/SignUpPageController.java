package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.PageSystem;
import ceng.estu.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void completeSignUp(ActionEvent actionEvent) {
        if(Validator.isValidEmailAddress(input_SignUpEmail.getText())) {
            String username = input_signUpUsername.getText();
            PageSystem.getPage(btn_Complete, "LogInPage");
            Main.getLastLoader().fillAreas(username, "");
            Main.setTitle("Welcome !");
        }else{
            AlertSystem.getAlert(ErrorType.ERROR,"E-mail is not valid.");
        }
    }

    public void fillAreas(String username, String password){
        input_signUpUsername.setText(username);
        input_signUpPassword.setText(password);
        input_signUpUsername.toFront(); //To get next free text area.
    }

}
