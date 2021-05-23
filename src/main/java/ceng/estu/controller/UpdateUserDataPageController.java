package ceng.estu.controller;

import ceng.estu.database.DBHandler;
import ceng.estu.main.Main;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static ceng.estu.model.User.user;

/**
 * @author reuzun
 */
public class UpdateUserDataPageController implements Initializable, Controllable {
    @javafx.fxml.FXML
    private ComboBox<Integer> adressChoiceBox;
    @javafx.fxml.FXML
    private ComboBox<Integer> phoneChoiceBox;
    @javafx.fxml.FXML
    private TextField phoneTextField;
    @javafx.fxml.FXML
    private TextArea adressTextArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void refresh(){

        adressChoiceBox.getItems().clear();
        phoneChoiceBox.getItems().clear();

        for(int i = 0 ; i <= user.getAddresses().size() ; i++)
            adressChoiceBox.getItems().add(i);

        for(int i = 0 ; i <= user.getPhoneNos().size() ; i++)
            phoneChoiceBox.getItems().add(i);

        adressChoiceBox.getSelectionModel().select(new Integer(0));
        phoneChoiceBox.getSelectionModel().select(new Integer(0));


        /*System.out.println(user.getAddresses().toString());
        System.out.println();
        System.out.println(user.getPhoneNos().toString());
*/

        adressChoiceBox.setOnAction(e->{
            if( adressChoiceBox.getSelectionModel().getSelectedItem() != null && (adressChoiceBox.getSelectionModel().getSelectedItem() - 1 ) >= 0 )
                adressTextArea.setText( user.getAddresses().get( adressChoiceBox.getSelectionModel().getSelectedItem() - 1 ) );
            else
                adressTextArea.setText("");
        });

        phoneChoiceBox.setOnAction(e->{
            if( adressChoiceBox.getSelectionModel().getSelectedItem() != null && (phoneChoiceBox.getSelectionModel().getSelectedItem() - 1 ) >= 0 )
                phoneTextField.setText( String.valueOf(user.getPhoneNos().get( phoneChoiceBox.getSelectionModel().getSelectedItem() - 1 )));
            else
                phoneTextField.setText("");
        });
    }

    @javafx.fxml.FXML
    public void addAdress(ActionEvent actionEvent) {
        try {
            DBHandler.addAdress(adressTextArea.getText(), adressChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }
    @javafx.fxml.FXML
    public void addPhone(ActionEvent actionEvent) {
        try {
            DBHandler.addPhoneNo(phoneTextField.getText(), phoneChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }

    @javafx.fxml.FXML
    public void updatePhone(ActionEvent actionEvent) {
        try {
            DBHandler.updatePhoneNo(phoneTextField.getText(), phoneChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }

    @javafx.fxml.FXML
    public void updateAdress(ActionEvent actionEvent) {
        try {
            DBHandler.updateAdress(adressTextArea.getText(), adressChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }

    @javafx.fxml.FXML
    public void deletePhone(ActionEvent actionEvent) {
        try {
            DBHandler.deletePhone(phoneTextField.getText(), phoneChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }

    @javafx.fxml.FXML
    public void deleteAdress(ActionEvent actionEvent) {
        try {
            DBHandler.deleteAdress(adressTextArea.getText(), adressChoiceBox.getSelectionModel().getSelectedItem() - 1);
            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            ( (UpdateUserDataPageController)Main.getLastLoader() ).refresh();
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "An error is occured!");
        }
    }


}
