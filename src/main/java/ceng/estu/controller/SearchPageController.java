package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.model.Model;
import ceng.estu.utilities.SortType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class SearchPageController implements Initializable {

    @FXML
    private ComboBox sizeComboBox;
    @FXML
    private VBox vBox;
    @FXML
    private TextField upperBoundTxt;
    @FXML
    private TextField lowerBoundTxt;
    @FXML
    private ComboBox sortTypeBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 31 ; i < 45 ; i++){
            sizeComboBox.getItems().add(i);
        }
        for(SortType s : SortType.values())
            sortTypeBox.getItems().add(s);


        // This process should be done automatically via methods!
        /*HBox hbox = new HBox();
        hbox.setSpacing(15);
        for(int i = 0 ; i < 5 ; i++) {
            AnchorPane container = new AnchorPane();

            Label label = new Label();
            label.setGraphic(new ImageView(new Image("file:\\\\\\C:\\Users\\Efe\\Documents\\IntelliJProjects\\ShoeBasket\\src\\main\\resources\\images\\shoe.jpg")));
            label.setText(new Model().toString());

            Button btn = new Button("buy");
            btn.setLayoutX(155);
            btn.setLayoutY(120);

            container.getChildren().addAll(label, btn);


            hbox.getChildren().add(container);
        }
        vBox.getChildren().add(hbox);*/
        try {
            randomPrintShoes();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    private void printShoes(String modelName, int size, int priceLowerBound, int priceUpperBound){

    }

    private void randomPrintShoes() throws MalformedURLException {
        HBox hbox = new HBox();
        hbox.setSpacing(15);
        for(int i = 0 ; i < 25 ; i++) {
            AnchorPane container = new AnchorPane();

            Model model = new Model();

            Label label = new Label();

            //adjusting path for intelliJ and jar file
            String path = new File("").getAbsolutePath().contains("target") ?
                    new File("").getAbsolutePath()+"\\classes\\images\\"+"shoe.jpg"
                    : new File("").getAbsolutePath()+"\\target\\classes\\images\\"+"shoe.jpg";

            File img = new File(path);

            label.setGraphic(new ImageView(new Image(String.valueOf(img.toURI().toURL()))));
            label.setText(model.toString());

            Button btn = new Button("buy");
            btn.setLayoutX(155);
            btn.setLayoutY(120);

            btn.setOnAction( e -> {
                System.out.println(model.shoeId + " is sold!");
            });

            container.getChildren().addAll(label, btn);

            if(hbox.getChildren().size() == 5){
                vBox.getChildren().add(hbox);
                hbox = new HBox();
                hbox.setSpacing(15);
            }

            hbox.getChildren().add(container);
        }
        vBox.getChildren().add(hbox);
    }

}
