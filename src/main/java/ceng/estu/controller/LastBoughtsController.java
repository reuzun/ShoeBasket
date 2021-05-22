package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.model.Model;
import ceng.estu.model.ModelType;
import ceng.estu.model.Shoe;
import ceng.estu.model.User;
import ceng.estu.utilities.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author reuzun
 */
public class LastBoughtsController implements Initializable {

    @FXML
    private TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setStyle("-fx-focus-color: transparent;");

        /*
        * http://tutorials.jenkov.com/javafx/tableview.html
        *  TableView tableView = new TableView();

    TableColumn<Person, String> column1 = new TableColumn<>("First Name");
    column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));


    TableColumn<Person, String> column2 = new TableColumn<>("Last Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));


    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);

    tableView.getItems().add(new Person("John", "Doe"));
    tableView.getItems().add(new Person("Jane", "Deer"));
        * */


        TableView<Shoe> tv = tableView;
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /*TableColumn<Shoe, Integer> column1 = new TableColumn<>("ShoeId");
        column1.setCellValueFactory(new PropertyValueFactory<>("shoeID"));

        TableColumn<Shoe, Integer> column2 = new TableColumn<>("ModelId");
        column2.setCellValueFactory(new PropertyValueFactory<>("modelID"));*/

        TableColumn<Shoe, String> column3 = new TableColumn<>("Size");
        column3.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<Shoe, String> column4 = new TableColumn<>("Color");
        column4.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Shoe, String> column5 = new TableColumn<>("Brand");
        column5.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Shoe, String> column6 = new TableColumn<>("Type");
        column6.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Shoe, Double> column7 = new TableColumn<>("Bought Price");
        column7.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));

        TableColumn<Shoe, Date> column8 = new TableColumn<>("Date");
        column8.setCellValueFactory(new PropertyValueFactory<>("date"));


        tv.getColumns().addAll(column5 ,column4, column3, column6, column7, column8);

        //get datas from db
        List<Shoe> LastBoughtShoeList = null;
        try {
            LastBoughtShoeList = DBHandler.getLastBoughtShoes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        tv.getItems().addAll( LastBoughtShoeList );


        tv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(tv.getSelectionModel().getSelectedItem());
            }
            /*Stage stage = new Stage();
            BorderPane pane = new BorderPane();
            Scene scene = new Scene(pane);

            pane.setLeft(new TextArea(tv.getSelectionModel().getSelectedItem().toString() ) );
            pane.setCenter(new ChoiceBox<>());

            stage.setScene(scene);
            stage.show();*/



            BorderPane bPane = new BorderPane();
            VBox vbox2 = new VBox();
            HBox hBox2 = new HBox();

            vbox2.setSpacing(75);
            hBox2.setSpacing(50);
            hBox2.setAlignment(Pos.CENTER);
            vbox2.setAlignment(Pos.CENTER);

            Stage buyStage = new Stage();

            Model model = DBHandler.getModelUsingModelID ( tv.getSelectionModel().getSelectedItem().getModelID() );

            ComboBox<Integer> starBox = new ComboBox();
            starBox.getItems().addAll(0,1,2,3,4,5);

            hBox2.getChildren().addAll(starBox);

            Button addBasketBtn = new Button("Give Star");

            addBasketBtn.setOnAction( (evv)->{
                //Write the given star to DataBase!
                DBHandler.saveStar(starBox.getSelectionModel().getSelectedItem(), model.getModelID());
            } );

            vbox2.getChildren().addAll(new Text(model.toString()), hBox2, addBasketBtn);


            String path2 = Utilities.getImagePath( model.getType() );

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








    }
}
