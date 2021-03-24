package ceng.estu.utilities;

import javafx.scene.control.Alert;

/**
 * @author reuzun
 */
public class AlertSystem {

    public static void getAlert(String sytemKey){ // systemKey is a joke. :)
        switch (sytemKey){
            case "error" : {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Büyük başarısızlıklar sözkonusu.");
                alert.show();
            }
        }
    }

}
