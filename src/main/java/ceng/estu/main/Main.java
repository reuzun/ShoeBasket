package ceng.estu.main;

import ceng.estu.controller.Controllable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    private static Stage stagePointer;
    private static FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stagePointer = logInStage;

        Scene scene = new Scene(loadFXML("LogInPage"));
        logInStage.setResizable(false);
        logInStage.setTitle("Welcome !");
        logInStage.setScene(scene);
        logInStage.show();

    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        loader = fxmlLoader;
        return fxmlLoader.load();
    }

    public static Parent loadFXMLA(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        //loader = fxmlLoader;
        return fxmlLoader.load();
    }

    public static Controllable getLastLoader(){
        return Main.loader.getController();
    }

    public static void setTitle(String title){
        stagePointer.setTitle(title);
    }

}
