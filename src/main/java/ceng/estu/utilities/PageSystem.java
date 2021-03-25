package ceng.estu.utilities;

import ceng.estu.controller.LogInPageController;
import ceng.estu.controller.SignUpPageController;
import ceng.estu.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author reuzun
 */
public class PageSystem {

    public static void getPage(Node node, String pageId){
        Window window = node.getScene().getWindow();
        if(window instanceof Stage){
            ((Stage) window).close();
            try {
                Scene scene = new Scene(Main.loadFXML(pageId));
                ((Stage) window).setScene(scene);
                ((Stage) window).show();
            }catch (Exception e){
                ((Stage) window).show();
                AlertSystem.getAlert(ErrorType.ERROR);
            }
        }
    }

}
