package ceng.estu.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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


    }
}
