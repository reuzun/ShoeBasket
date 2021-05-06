package ceng.estu.controller;

import ceng.estu.main.Main;
import ceng.estu.model.Model;
import ceng.estu.model.ModelType;
import ceng.estu.model.Shoe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class AdminManagePageController implements Initializable {
    @FXML
    public ChoiceBox shoeColorChoiceBox;
    @FXML
    public ChoiceBox shoeSizeChoiceBox;
    @FXML
    public TextField shoeCount;
    @FXML
    public ChoiceBox modelType;
    @FXML
    public TextField price;
    @FXML
    public TextField modelName;
    @FXML
    public TextField brandName;
    @FXML
    public TextField updateRemoveModelID;
    @FXML
    public TextField updateRemoveShoeID;
    @FXML
    public TextField addShoeModelID;

    Stage updateStage = new Stage();
    AnchorPane updatePane = new AnchorPane();

    Stage searchStage = new Stage();
    AnchorPane searchPane = new AnchorPane();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scene scene = new Scene(updatePane);
        updateStage.setScene(scene);
        updateStage.setTitle("Update Item");

        Scene scene2 = new Scene(searchPane);
        searchStage.setScene(scene2);
        searchStage.setTitle("SearchDB");
    }

    @javafx.fxml.FXML
    public void updateShoe(ActionEvent actionEvent) throws IOException, URISyntaxException {
        //updatePane.getChildren().add(txt); can be edited dynamically
        Shoe shoe = new Shoe(4,7,34,"Black",123);
        Scene scene = new Scene(Main.loadFXML("UpdateShoePage"));
        ((UpdateShoePageController)Main.getLastLoader()).modelID.setText(String.valueOf(shoe.getModelID()));
        ((UpdateShoePageController)Main.getLastLoader()).shoeID.setText(String.valueOf(shoe.getShoeID()));
        ((UpdateShoePageController)Main.getLastLoader()).size.setText(String.valueOf(shoe.getSize()));
        ((UpdateShoePageController)Main.getLastLoader()).color.setText(String.valueOf(shoe.getColor()));
        ((UpdateShoePageController)Main.getLastLoader()).count.setText(String.valueOf(shoe.getCount()));
        String path2 = new File("").getAbsolutePath().contains("target") ?
                new File("").getAbsolutePath()+"\\classes\\images\\"+"shoe.jpg"
                : new File("").getAbsolutePath()+"\\target\\classes\\images\\"+"shoe.jpg";
        File file = new File(path2);
        ((UpdateShoePageController)Main.getLastLoader()).imageView.setImage(new Image(String.valueOf(file.toURI().toURL())));

        updateStage.setScene(scene);

        updateStage.show();

    }

    static Object[] updateModelValues = new Object[6];
    @javafx.fxml.FXML
    public void updateModel(ActionEvent actionEvent) throws IOException {
        //get model from modelID


        Model model = new Model(4,"asdasd","asdasd",ModelType.Boot,148.57,-1.0);
        Scene scene = new Scene(Main.loadFXML("UpdateModelPage"));
        ((UpdateModelPageController)Main.getLastLoader()).modelID.setText(String.valueOf(model.getModelID()));
        ((UpdateModelPageController)Main.getLastLoader()).modelName.setText(String.valueOf(model.getModelName()));
        ((UpdateModelPageController)Main.getLastLoader()).brandName.setText(String.valueOf(model.getBrandName()));
        ((UpdateModelPageController)Main.getLastLoader()).type.setText(String.valueOf(model.getType()));
        ((UpdateModelPageController)Main.getLastLoader()).price.setText(String.valueOf(model.getPrice()));
        ((UpdateModelPageController)Main.getLastLoader()).rating.setText(String.valueOf(model.getCustomerRating()));
        String path2 = new File("").getAbsolutePath().contains("target") ?
                new File("").getAbsolutePath()+"\\classes\\images\\"+"shoe.jpg"
                : new File("").getAbsolutePath()+"\\target\\classes\\images\\"+"shoe.jpg";
        File file = new File(path2);
        ((UpdateModelPageController)Main.getLastLoader()).imageView.setImage(new Image(String.valueOf(file.toURI().toURL())));

        updateStage.setScene(scene);

        updateStage.show();
    }

    @javafx.fxml.FXML
    public void searchModel(ActionEvent actionEvent) {
        searchPane.getChildren().clear();
        TableView<Model> tv = new TableView<>();
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.prefHeightProperty().bind(searchStage.heightProperty());
        tv.prefWidthProperty().bind(searchStage.widthProperty());

        searchPane.setMinWidth(750);
        searchPane.setMinHeight(400);

        TableColumn<Model, Integer> column1 = new TableColumn<>("ModelID");
        column1.setCellValueFactory(new PropertyValueFactory<>("modelID"));

        TableColumn<Model, String> column2 = new TableColumn<>("Model Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("modelName"));

        TableColumn<Model, String> column3 = new TableColumn<>("Brand Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("brandName"));

        TableColumn<Model, ModelType> column4 = new TableColumn<>("Type");
        column4.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Model, Double> column5 = new TableColumn<>("Price");
        column5.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Model, Double> column6 = new TableColumn<>("Customer Rating");
        column6.setCellValueFactory(new PropertyValueFactory<>("customerRating"));

        tv.getColumns().addAll(column1,column2,column3,column4,column5,column6);

        //get datas from db
        tv.getItems().add(new Model(4,"asdasd","asdasd",ModelType.Boot,148.57,-1.0));
        tv.getItems().add(new Model(42,"asdasasdd","asdaasdsd",ModelType.Boot,12348.57,-123.0));

        searchPane.getChildren().add(tv);

        searchStage.show();
    }
    @FXML
    public void searchShoe(ActionEvent actionEvent) {
        searchPane.getChildren().clear();
        TableView<Shoe> tv = new TableView<>();
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.prefHeightProperty().bind(searchStage.heightProperty());
        tv.prefWidthProperty().bind(searchStage.widthProperty());
        /*
        tv.setMinHeight(300);
        tv.setMinWidth(500);*/
        searchPane.setMinWidth(750);
        searchPane.setMinHeight(400);

        TableColumn<Shoe, Integer> column1 = new TableColumn<>("ModelID");
        column1.setCellValueFactory(new PropertyValueFactory<>("modelID"));

        TableColumn<Shoe, Integer> column2 = new TableColumn<>("shoeID");
        column2.setCellValueFactory(new PropertyValueFactory<>("shoeID"));

        TableColumn<Shoe, Integer> column3 = new TableColumn<>("Size");
        column3.setCellValueFactory(new PropertyValueFactory<>("Size"));

        TableColumn<Shoe, String> column4 = new TableColumn<>("Color");
        column4.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Shoe, Integer> column5 = new TableColumn<>("Count");
        column5.setCellValueFactory(new PropertyValueFactory<>("count"));

        tv.getColumns().addAll(column1,column2,column3,column4,column5);

        //get datas from db
        tv.getItems().add(new Shoe(4,7,34,"Black",123));
        tv.getItems().add(new Shoe(42,7,36,"White",126));

        searchPane.getChildren().add(tv);

        searchStage.show();
    }

    @javafx.fxml.FXML
    public void calculateQueries(ActionEvent actionEvent) {
    }




    @javafx.fxml.FXML
    public void removeModel(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeShoe(ActionEvent actionEvent) {
    }


    @FXML
    public void addShoe(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addModel(ActionEvent actionEvent) {
    }


}
