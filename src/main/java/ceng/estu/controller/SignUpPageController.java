package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.PageSystem;
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
    public void completeSignUp(ActionEvent actionEvent) {
        String username = input_signUpUsername.getText();
        PageSystem.getPage(btn_Complete,"LogInPage");
        Main.getLastLoader().fillAreas(username, "");
        try {
            Main.getLastLoader().getInputPassword().requestFocus();
        }catch (Exception e){
             AlertSystem.getAlert("error");
        }
        Main.setTitle("Welcome !");
    }

    public void fillAreas(String username, String password){
        input_signUpUsername.setText(username);
        input_signUpPassword.setText(password);
        input_signUpUsername.toFront(); //To get next free text area.
    }

}
