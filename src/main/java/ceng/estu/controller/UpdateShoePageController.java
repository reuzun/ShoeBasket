package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

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
    public void updateShoe(ActionEvent actionEvent) throws SQLException {
        try {
            DBHandler.updateShoeByShoeId(count.getText(), shoeID.getText());
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "An error is ocurred!");
        }
    }
}
