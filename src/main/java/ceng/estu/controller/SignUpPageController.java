package ceng.estu.controller;

import ceng.estu.utilities.PageSystem;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @author reuzun
 */
public class SignUpPageController {
    @javafx.fxml.FXML
    private Button btn_Complete;

    @javafx.fxml.FXML
    public void completeSignUp(ActionEvent actionEvent) {
        PageSystem.getPage(btn_Complete,"LogInPage");
    }
}
