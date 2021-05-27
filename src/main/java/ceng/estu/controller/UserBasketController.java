package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.model.ModelType;
import ceng.estu.model.Shoe;
import ceng.estu.model.User;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class UserBasketController implements Initializable {
    @javafx.fxml.FXML
    private ScrollPane scrollPane;
    @javafx.fxml.FXML
    private ChoiceBox<String> adressBox;


    @javafx.fxml.FXML
    public void buyItemsOnBasket(ActionEvent actionEvent) throws SQLException {

        try {
            List<Shoe> list = DBHandler.getUserBasket();

            DBHandler.buyUserBasket(list, adressBox.getSelectionModel().getSelectedItem());

            AlertSystem.getAlert(ErrorType.INFORMATION,"Done!");

        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR,"Error occurred!");
        }

        UserPageController.basketStage.close();
        //initialize(null, null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adressBox.getItems().clear();

        adressBox.getItems().addAll( User.user.getAddresses() );

        VBox vBox = new VBox();

        vBox.setSpacing(5);

        try {
            List<Shoe> shobasket = DBHandler.getUserBasket();

            for (Shoe s : shobasket){
                HBox hbox = new HBox();
                hbox.setSpacing(20);

                //String path2 = Utilities.getImagePath( ModelType.valueOf(s.getType()) );

                //File img2 = new File(path2);
                ImageView iv2 = null;

                try {
                    iv2 = new ImageView( new Image(Utilities.getImagePath( ModelType.valueOf(s.getType()) ) ) );
                } catch (Exception malformedURLException) {
                    malformedURLException.printStackTrace();
                }

                iv2.setFitHeight(150);
                iv2.setFitWidth(150);

                Text info = new Text(
                        "Model Id : " + s.modelName +
                        "\nBrand : " + s.getBrand() +
                        "\nColor : " + s.getColor() +
                        "\nSize : " + s.getSize()
                );
                Text price = new Text( "Price is : \n" + s.getBoughtPrice() );
                Button btn_sub = new Button("Remove");

                btn_sub.setOnAction(event -> {
                    try {
                        DBHandler.removeFromUserBasket(s.getShoeID());
                        AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
                    } catch (SQLException throwables) {
                        AlertSystem.getAlert(ErrorType.ERROR, "Unknown Error is occurred!");
                    }
                    this.initialize(null, null);
                });


                hbox.getChildren().addAll(iv2, info, price, btn_sub);

                hbox.setAlignment(Pos.CENTER_LEFT);

                vBox.getChildren().add(hbox);
            }

            scrollPane.setContent(vBox);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
