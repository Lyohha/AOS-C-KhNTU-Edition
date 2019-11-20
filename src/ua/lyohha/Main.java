package ua.lyohha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainmenupage\\MainMenuPage.fxml"));
        //scene.getStylesheets().add
        // (HelloWorld.class.getResource("HelloWorld.css").toExternalForm());

        primaryStage.setTitle("AOS-C KhNTU Edition");
        Scene scene = new Scene(root, 600, 480);
        scene.getStylesheets().add(getClass().getResource("mainmenupage\\MainMenuPage.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
