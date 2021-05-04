package ceng.estu.controller;

import ceng.estu.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class AdminPageController implements Initializable {

    @FXML
    public BorderPane GUIPane;

    @FXML
    public void setAddProductPane(ActionEvent actionEvent) throws IOException {
        adminPage = new Scene(Main.loadFXML("AdminManagePage"));
        GUIPane.setCenter(adminPage.getRoot());
    }

    static Scene adminPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            adminPage = new Scene(Main.loadFXML("AdminMainPage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUIPane.setCenter(adminPage.getRoot());
    }
}
