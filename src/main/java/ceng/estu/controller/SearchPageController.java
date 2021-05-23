package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.model.Model;
import ceng.estu.model.ModelType;
import ceng.estu.model.Shoe;
import ceng.estu.model.User;
import ceng.estu.utilities.SortType;
import ceng.estu.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author reuzun
 */
public class SearchPageController implements Initializable {

    /*@FXML
    private ComboBox<Integer> sizeComboBox;*/
    @FXML
    private VBox vBox;
    @FXML
    private TextField upperBoundTxt;
    @FXML
    private TextField lowerBoundTxt;
    @FXML
    private ComboBox<SortType> sortTypeBox;
    @FXML
    private TextField modelNameSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*for(int i = 31 ; i < 45 ; i++){
            sizeComboBox.getItems().add(i);
        }*/
        for(SortType s : SortType.values())
            sortTypeBox.getItems().add(s);


        // This process should be done automatically via methods!
        /*HBox hbox = new HBox();
        hbox.setSpacing(15);
        for(int i = 0 ; i < 5 ; i++) {
            AnchorPane container = new AnchorPane();

            Label label = new Label();
            label.setGraphic(new ImageView(new Image("file:\\\\\\C:\\Users\\Efe\\Documents\\IntelliJProjects\\ShoeBasket\\src\\main\\resources\\images\\sneaker.jpg")));
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
        } catch (MalformedURLException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void randomPrintShoes() throws MalformedURLException, SQLException {
        List<Model> modelList = DBHandler.randomModels();
        printShoes(modelList);
    }

    @FXML
    public void searchShoes(ActionEvent actionEvent) throws SQLException, MalformedURLException {
        String modelName = modelNameSearch.getText();
        int size;
        /*try{
            size = sizeComboBox.getSelectionModel().getSelectedItem();
        }catch (Exception e){
            size = 0;
        }*/
        double lowerBound = lowerBoundTxt.getText().length() > 0 ? Double.parseDouble( lowerBoundTxt.getText() ) : 0;
        double upperBound = upperBoundTxt.getText().length() > 0 ? Double.parseDouble( upperBoundTxt.getText() ) : 0;
        SortType type = sortTypeBox.getSelectionModel().getSelectedItem();

        List<Model> modelList = DBHandler.searchModelsAccordingToParams(
                modelName,
                lowerBound,
                upperBound,
                type
        );

        printShoes(modelList);
    }



    private void printShoes(List<Model> modelList) throws MalformedURLException {

        vBox.getChildren().clear();

        HBox hbox = new HBox();
        hbox.setSpacing(15);
        for (Model value : modelList) {
            AnchorPane container = new AnchorPane();
            container.setMinWidth(290);
            container.setMinHeight(200);
            container.setStyle("-fx-border-color: gray;-fx-padding: 4px;-fx-background-color: #ecdede;-fx-border-radius: 40px;");

            /* Test case for images of printing random shoes.
            int a = (int) (Math.random()*3);
            ModelType b = null;
            switch (a){
                case 0: b = ModelType.Boot;break;
                case 1: b = ModelType.Heel;break;
                case 2: b = ModelType.Sneaker;break;
                default: break;
            }
            */

            Model model = value;

            Label label = new Label();

            //adjusting path for intelliJ and jar file
            String path = Utilities.getImagePath(model.getType());
            //System.out.println("type : " + ModelType.Boot.toString().toLowerCase());

            File img = new File(path);

            ImageView iv = new ImageView(new Image(String.valueOf(img.toURI().toURL())));
            iv.setFitHeight(150);
            iv.setFitWidth(150);
            iv.setClip(new Ellipse(70, 70, 70, 70));
            label.setGraphic(iv);
            label.setText(model.toString());

            //JFXButton btn = new JFXButton("Buy!");
            Button btn = new Button("Buy!");
            //btn.setStyle("-fx-background-color: #99adb5;-fx-min-width: 75px;-fx-text-alignment: center");

            btn.setLayoutX(180);
            btn.setLayoutY(155);


            btn.setOnAction(e -> {
                BorderPane bPane = new BorderPane();
                AnchorPane aPane = new AnchorPane();
                VBox vbox2 = new VBox();
                HBox hBox2 = new HBox();

                vbox2.setSpacing(75);
                hBox2.setSpacing(50);
                hBox2.setAlignment(Pos.CENTER);
                vbox2.setAlignment(Pos.CENTER);

                Stage buyStage = new Stage();
                ComboBox<Integer> sizeBox = new ComboBox();
                for (int j = 30; j < 45; j++) {
                    sizeBox.getItems().add(j);
                }

                AtomicReference<Shoe> willAddedShoe = new AtomicReference<>();

                ComboBox<String> colorBox = new ComboBox();
                sizeBox.setOnAction((ev) -> {
                    //Get colors from db
                    for (int j = 30; j < 45; j++) {
                        colorBox.getItems().add(String.valueOf((char) j));
                    }
                    if (!hBox2.getChildren().contains(colorBox))
                        hBox2.getChildren().addAll(colorBox);
                    else {
                        colorBox.getItems().clear();
                        colorBox.getItems().add("new Colors ADDED!");
                    }
                });

                colorBox.setOnAction((evvv) -> {
                    int size = sizeBox.getSelectionModel().getSelectedItem();
                    String color = colorBox.getSelectionModel().getSelectedItem();
                    System.out.println("Size : " + size + " Color : " + color + "Model is : " + model.toString() + " Shoe is ...");
                    willAddedShoe.set(new Shoe(4, 4, 44, "Black", 44));
                });


                hBox2.getChildren().addAll(sizeBox);

                Button addBasketBtn = new Button("Add To Basket");

                addBasketBtn.setOnAction((evv) -> {
                    User.user.getBasket().add(willAddedShoe.get());
                    System.out.println(User.user.getBasket().toString());
                });

                vbox2.getChildren().addAll(new Text(model.toString()), hBox2, addBasketBtn);


                String path2 = Utilities.getImagePath(model.getType());

                File img2 = new File(path2);
                ImageView iv2 = null;

                try {
                    iv2 = new ImageView(new Image(String.valueOf(img2.toURI().toURL())));
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }


                bPane.setTop(new Text("MODELİN ADI"));
                bPane.setLeft(iv2);
                bPane.setCenter(vbox2);

                Scene scene = new Scene(bPane);
                buyStage.setScene(scene);
                buyStage.setResizable(false);
                buyStage.setMinWidth(600);
                buyStage.show();
                System.out.println(model + " is sold!");
            });

            label.setStyle("-fx-padding: 25px 0px 0px 25px;");

            container.getChildren().addAll(label, btn);

            if (hbox.getChildren().size() == 4) {
                vBox.getChildren().add(hbox);
                hbox = new HBox();
                hbox.setSpacing(15);
            }

            hbox.getChildren().add(container);
        }
        vBox.getChildren().add(hbox);
    }

}
