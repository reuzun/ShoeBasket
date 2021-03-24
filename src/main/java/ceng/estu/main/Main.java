package ceng.estu.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author reuzun
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public Stage logInStage = new Stage();
    public Stage application = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(loadFXML("LogInPage"));
        logInStage.setResizable(false);
        logInStage.setTitle("Welcome !");
        logInStage.setScene(scene);
        logInStage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void getApplication(){

    }

}
