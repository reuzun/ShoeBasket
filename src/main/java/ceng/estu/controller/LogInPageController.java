package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.PageSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * @author reuzun
 */
public class LogInPageController {
    @FXML
    private PasswordField inputPassword;
    @FXML
    private TextField inputUsername;
    @FXML
    private Button btn_LogIn;

    @FXML
    public void logIn(ActionEvent actionEvent){

    }

    @FXML
    public void signUp(ActionEvent actionEvent) throws InterruptedException {
        PageSystem.getPage(btn_LogIn,"SignUpPage");
    }
}
