package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public MainWindow()
    {

    }

    public VBox mainVBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainmenupage\\MainMenuPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainmenupage\\MainMenuPage.css").toExternalForm());
    }
}
