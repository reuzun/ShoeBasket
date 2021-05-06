package ceng.estu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class UpdateModelPageController implements Controllable{
    @FXML
    public TextField modelID;
    @FXML
    public TextField modelName;
    @FXML
    public TextField brandName;
    @FXML
    public TextField type;
    @FXML
    public TextField price;
    @FXML
    public TextField rating;
    @FXML
    public ImageView imageView;

    @FXML
    public void updateModel(ActionEvent actionEvent) {
        //update database!
    }

}
