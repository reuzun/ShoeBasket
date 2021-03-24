package ceng.estu.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author reuzun
 */
public class AlertSystem {

    public static boolean getAlert(String sytemKey){ // systemKey is a joke. :)
        switch (sytemKey){
            case "error" : {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Büyük başarısızlıklar sözkonusu.");
                alert.show();
                return true;
            }
            case "confirm" : {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Büyük başarısızlıklar sözkonusu.");
                alert.showAndWait();
                return alert.getResult() == ButtonType.OK;
            }
            default:
                return true;
        }
    }

}
