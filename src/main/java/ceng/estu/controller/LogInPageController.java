package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.model.User;
import ceng.estu.model.UserType;
import ceng.estu.utilities.PageSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class LogInPageController implements Controllable, Initializable {

    @FXML
    private TextField inputUsername;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private Button btn_LogIn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void logIn(ActionEvent actionEvent){
        //handle authentication from database
        PageSystem.getPage(btn_LogIn, "UserPage");
        User.setUser("klc4123", UserType.User, "Sefa", "Bozdag", "klc4123@hotmail.com", new ArrayList<>(), new ArrayList<>());
    }

    @FXML
    public void signUp(ActionEvent actionEvent) throws InterruptedException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        PageSystem.getPage(btn_LogIn,"SignUpPage");
        Main.getLastLoader().fillAreas(username, password);
        Main.setTitle("Sign Up !");
    }

    public void fillAreas(String username, String password){
        inputUsername.setText(username);
        inputPassword.setText(password);
        inputUsername.toFront(); //To get next free text area.
    }

    @Override
    public PasswordField getInputPassword() {
        return inputPassword;
    }
}
