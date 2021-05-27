package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.main.Main;
import ceng.estu.model.Model;
import ceng.estu.model.ModelType;
import ceng.estu.model.Shoe;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author reuzun
 */
public class AdminManagePageController implements Initializable {
    @FXML
    public ChoiceBox<String> shoeColorChoiceBox;
    @FXML
    public ChoiceBox<Integer> shoeSizeChoiceBox;
    @FXML
    public TextField shoeCount;
    @FXML
    public ChoiceBox<String> modelType;
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

        shoeColorChoiceBox.getItems().addAll("Yellow", "Green", "Gray", "Black", "Red", "Blue");
        modelType.getItems().addAll("Sneaker", "Heel", "Boot");

        for(int i = 33 ; i < 45 ; i++)
            shoeSizeChoiceBox.getItems().add(i);


    }

    @javafx.fxml.FXML
    public void updateShoe() throws IOException, URISyntaxException, SQLException {
        //updatePane.getChildren().add(txt); can be edited dynamically
        //Shoe shoe = new Shoe(4,7,34,"Black",123);
        if(updateRemoveShoeID.getText().length() == 0) {
            AlertSystem.getAlert(ErrorType.ERROR, "Please enter a valid input!");
            return;
        }

        Shoe shoe = DBHandler.getShoeByShoeId( updateRemoveShoeID.getText() );

        Scene scene = new Scene(Main.loadFXML("UpdateShoePage"));
        ((UpdateShoePageController)Main.getLastLoader()).modelID.setText(String.valueOf(shoe.getModelID()));
        ((UpdateShoePageController)Main.getLastLoader()).shoeID.setText(String.valueOf(shoe.getShoeID()));
        ((UpdateShoePageController)Main.getLastLoader()).size.setText(String.valueOf(shoe.getSize()));
        ((UpdateShoePageController)Main.getLastLoader()).color.setText(String.valueOf(shoe.getColor()));
        ((UpdateShoePageController)Main.getLastLoader()).count.setText(String.valueOf(shoe.getCount()));

        int modelId = DBHandler.getModelIdByShoeId( shoe.getShoeID() );
        Model model = DBHandler.getModelByModelId(modelId);

        //String path2 = Utilities.getImagePath( model.getType() );

        //File file = new File(path2);
        ((UpdateShoePageController)Main.getLastLoader()).imageView.setImage(new Image(Utilities.getImagePath( model.getType() )));

        updateStage.setScene(scene);

        updateStage.show();

    }

    @javafx.fxml.FXML
    public void updateModel() throws IOException {
        //get model from modelID

        if(updateRemoveModelID.getText().length() == 0) {
            AlertSystem.getAlert(ErrorType.ERROR, "Please enter a valid input!");
            return;
        }
        Model model = DBHandler.getModelByModelId( Integer.parseInt(updateRemoveModelID.getText()) );


        Scene scene = new Scene(Main.loadFXML("UpdateModelPage"));
        ((UpdateModelPageController)Main.getLastLoader()).modelID.setText(String.valueOf(model.getModelID()));
        ((UpdateModelPageController)Main.getLastLoader()).modelName.setText(String.valueOf(model.getModelName()));
        ((UpdateModelPageController)Main.getLastLoader()).brandName.setText(String.valueOf(model.getBrandName()));
        ((UpdateModelPageController)Main.getLastLoader()).type.setText(String.valueOf(model.getType()));
        ((UpdateModelPageController)Main.getLastLoader()).price.setText(String.valueOf(model.getPrice()));
        ((UpdateModelPageController)Main.getLastLoader()).rating.setText(String.valueOf(model.getCustomerRating()));
        //String path2 = Utilities.getImagePath( model.getType() );
        //File file = new File(path2);
        ((UpdateModelPageController)Main.getLastLoader()).imageView.setImage(new Image(Utilities.getImagePath( model.getType() ) ) );

        updateStage.setScene(scene);

        updateStage.show();
    }

    @javafx.fxml.FXML
    public void searchModel(ActionEvent actionEvent) throws SQLException {
        searchPane.getChildren().clear();
        TableView<Model> tv = new TableView<>();
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.prefHeightProperty().bind(searchStage.heightProperty().subtract(17));
        tv.prefWidthProperty().bind(searchStage.widthProperty().subtract(17));

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


        tv.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                updateRemoveModelID.setText(String.valueOf(tv.getSelectionModel().getSelectedItem().getModelID()));
                try {
                    updateModel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        List<Model> list = null;
        try {
            list = DBHandler.getModelsByKeyValues("str", "BrandName", brandName.getText(),
                    "strlike", "ModelName", modelName.getText(),
                    "str", "Type", String.valueOf(modelType.getSelectionModel().getSelectedItem()),
                    "int", "modelid", updateRemoveModelID.getText()
            );
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR,"Please enter at least 1 paramater. If another error occurs we dont know why :D");
        }


        tv.getItems().addAll( list );

        ScrollPane sp = new ScrollPane();
        sp.setContent(tv);

        searchPane.getChildren().add(sp);

        searchStage.show();
        searchStage.toFront();
    }
    @FXML
    public void searchShoe(ActionEvent actionEvent) throws SQLException {
        searchPane.getChildren().clear();
        TableView<Shoe> tv = new TableView<>();
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.prefHeightProperty().bind(searchStage.heightProperty().subtract(17));
        tv.prefWidthProperty().bind(searchStage.widthProperty().subtract(17));
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

        tv.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                updateRemoveShoeID.setText(String.valueOf(tv.getSelectionModel().getSelectedItem().getShoeID()));
                try {
                    updateShoe();
                } catch (IOException | URISyntaxException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        List<Shoe> list = null;
        try {
             list = DBHandler.getShoesByKeyValues("int", "modelId", addShoeModelID.getText(),
                    "str", "Color", shoeColorChoiceBox.getSelectionModel().getSelectedItem(),
                    "int", "Size", String.valueOf(shoeSizeChoiceBox.getSelectionModel().getSelectedItem()),
                    "int", "shoeid", updateRemoveShoeID.getText()
            );
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR,"Please enter at least 1 paramater. If another error occurs we dont know why :D");
        }

        tv.getItems().addAll( list );

        ScrollPane sp = new ScrollPane();
        sp.setContent(tv);

        searchPane.getChildren().add(sp);

        searchStage.show();
        searchStage.toFront();
    }

    @javafx.fxml.FXML
    public void calculateQueries(ActionEvent actionEvent) throws SQLException {
        int userCount = DBHandler.userCount();

        String[] bestSellerModelId = DBHandler.findBestSellerModelId();
        String[] bestBuyerOfShop = DBHandler.findBest("shoe_sold", "username");
        String[] bestMoneyGiver = DBHandler.bestMoneySpender();
        String bestBrand = DBHandler.getMostSuccessfulBrand();

        Stage stage = new Stage();

        stage.setTitle("Query Results");

        Pane pane = new Pane();

        stage.setResizable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("   \n   User count is : " + userCount);
        sb.append("   \n   Best seller model ID is : " + Arrays.toString(bestSellerModelId));
        sb.append("   \n   Most product buyer is : " + Arrays.toString(bestBuyerOfShop));
        sb.append("   \n   Most money spender is : " + Arrays.toString(bestMoneyGiver));
        sb.append("   \n   Best brand is : " + bestBrand);
        Text text = new Text(sb.toString());

        text.setFont(Font.font(22));

        pane.getChildren().add(text);

        stage.setScene(new Scene(pane));

        stage.show();

    }


    @javafx.fxml.FXML
    public void removeModel(ActionEvent actionEvent) {
        try {
            DBHandler.deleteModelByModelId(
                    Integer.parseInt( updateRemoveModelID.getText() )
            );

            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");

        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "This model has shoes that is in relation with some shoes \n" +
                    "so that it is not allowed to delete it!");
        }
    }

    @javafx.fxml.FXML
    public void removeShoe(ActionEvent actionEvent) {
        try {
            DBHandler.deleteShoeByShoeId(
                    updateRemoveShoeID.getText()
            );

            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");

        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "This shoe is sold before. To let Database have datas which is belong to past\n" +
                    "you are unable to delete it!");
        }
    }


    @FXML
    public void addShoe(ActionEvent actionEvent) throws SQLException {
        try {
            DBHandler.insertShoe(
                    Integer.parseInt(addShoeModelID.getText()),
                    shoeSizeChoiceBox.getSelectionModel().getSelectedItem(),
                    shoeColorChoiceBox.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(shoeCount.getText())
            );

            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");

        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "An error has ocurred!");
        }
    }

    @javafx.fxml.FXML
    public void addModel(ActionEvent actionEvent) throws SQLException {
        try {
            DBHandler.insertModel(
                    modelName.getText(),
                    brandName.getText(),
                    modelType.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(price.getText().replace(",", "."))
            );

            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");

        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "An error has ocurred!");
        }
    }


}
