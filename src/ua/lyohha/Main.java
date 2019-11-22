package ua.lyohha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.lyohha.mainmenupage.MainMenuPage;
import ua.lyohha.window.MainWindow;

public class Main /*extends Application*/ {

    /*@Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("mainmenupage\\MainMenuPage.fxml").openStream());
        MainMenuPage controller = loader.getController();


        //Parent root = FXMLLoader.load(getClass().getResource("mainmenupage\\MainMenuPage.fxml"));
        //scene.getStylesheets().add
        // (HelloWorld.class.getResource("HelloWorld.css").toExternalForm());

        primaryStage.setTitle("AOS-C KhNTU Edition");
        Scene scene = new Scene(root, 600, 480);

        scene.getStylesheets().add(getClass().getResource("mainmenupage\\MainMenuPage.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(480);
        primaryStage.show();
    }*/


    public static void main(String[] args) {

        //launch(args);
        Application.launch(MainWindow.class, args);
    }
}
