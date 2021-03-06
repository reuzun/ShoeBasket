package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.main.Main;
import ceng.estu.model.User;
import ceng.estu.model.UserType;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
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
        //User.setUser("klc4123", "parola123", UserType.User, "Sefa", "Bozdag", "klc4123@hotmail.com", new ArrayList<>(), new ArrayList<>());

        try {
            DBHandler.logIn(inputUsername.getText(), inputPassword.getText());
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "Wrong Username or Password");
            return;
        }



        if(User.user.getType().equals(UserType.User))
            PageSystem.getPage(btn_LogIn, "UserPage");
        else
            PageSystem.getPage(btn_LogIn, "AdminPage");

        /*if(User.user.getType() == UserType.User)
            PageSystem.getPage(btn_LogIn, "UserPage");
        else
            PageSystem.getPage(btn_LogIn, "AdminPage");*/
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
