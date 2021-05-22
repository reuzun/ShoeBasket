package ceng.estu.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author reuzun
 */
public class AlertSystem {

    public static boolean getAlert(ErrorType sytemKey, String... arg) { // systemKey is a joke. :)
        String errorText = "Büyük başarısızlıklar sözkonusu.";
        if(arg.length != 0) {
            if (arg.length > 1) {
                StringBuilder sb = new StringBuilder();
                for (String s : arg)
                    sb.append(s);
                errorText = sb.toString();
            } else {
                errorText = arg[0];
            }
        }
        switch (sytemKey){
            case ERROR: {
                Alert alert = new Alert(Alert.AlertType.ERROR,errorText);
                alert.show();
                return true;
            }
            case CONFIRMATION: {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,errorText);
                alert.showAndWait();
                return alert.getResult() == ButtonType.OK;
            }
            case INFORMATION:{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,errorText);
                alert.showAndWait();
                return true;
            }
            default:
                return true;
        }
    }

}
