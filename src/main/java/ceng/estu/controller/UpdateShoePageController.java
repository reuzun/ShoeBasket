package ceng.estu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author reuzun
 */
public class UpdateShoePageController implements Controllable{
    @FXML
    public TextField modelID;
    @FXML
    public TextField shoeID;
    @FXML
    public TextField size;
    @FXML
    public TextField color;
    @FXML
    public TextField count;
    @FXML
    public ImageView imageView;

    @FXML
    public void updateShoe(ActionEvent actionEvent) {
    }
}
