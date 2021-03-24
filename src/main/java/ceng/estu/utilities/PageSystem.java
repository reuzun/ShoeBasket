package ceng.estu.utilities;

import ceng.estu.main.Main;
import javafx.scene.Node;
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
                ((Stage) window).setScene(new Scene(Main.loadFXML(pageId)));
                ((Stage) window).show();
            }catch (Exception e){
                ((Stage) window).show();
                AlertSystem.getAlert("error");
            }
        }
    }

}
