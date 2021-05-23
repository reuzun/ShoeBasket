package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
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
    public void updateModel(ActionEvent actionEvent) throws SQLException {
        //update database!
        try {
            DBHandler.updateModelByModelId(price.getText(), modelID.getText());
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "An error is ocurred!");
        }
    }

}
