package ceng.estu.controller;

import ceng.estu.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class UserPageController implements Initializable {

    @FXML
    private BorderPane GUIPane;

    static Scene searchPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setSearchPane(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setSearchPane(ActionEvent actionEvent) throws IOException {
        searchPage = new Scene(Main.loadFXML("SearchPage"));
        GUIPane.setCenter(searchPage.getRoot());
    }


    @FXML
    public void lastBoughtsBtn(ActionEvent actionEvent) throws IOException {
        searchPage = new Scene(Main.loadFXML("LastBoughts"));
        GUIPane.setCenter(searchPage.getRoot());
    }

    public static Stage basketStage;
    @FXML // Can cause bugs since it changes main.lastController!
    public void setBasketPane(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(Main.loadFXMLA(("UserBasket")));
        Stage stage = new Stage();
        basketStage = stage;
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setUpdatePage(ActionEvent actionEvent) throws IOException {
        searchPage = new Scene(Main.loadFXML("UpdateUserDataPage"));
        GUIPane.setCenter(searchPage.getRoot());
    }
}
