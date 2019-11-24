package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.menucontrols.MainMenuControls;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends Application {

    public Label universityNameLabel;
    public GridPane frameGridPane;
    public GridPane mainGridPane;
    private Navigation navigation;

    public MainWindow() {

    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainWindowloader = new FXMLLoader();
        Parent root = mainWindowloader.load(getClass().getResource("MainWindow.fxml").openStream());
        MainWindow mainWindow = mainWindowloader.getController();
        navigation = new Navigation(mainWindow.frameGridPane);
        mainWindow.mainGridPane.setStyle("-fx-background-color: \"#3C3F41\"");

        mainWindow.universityNameLabel.setStyle("-fx-text-fill: white");

        MainMenuControls mainMenuControls = new MainMenuControls((MenuPage) navigation.navigateTo(MenuPage.class));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(630);
        primaryStage.setTitle("AOS C");
        primaryStage.show();
    }
}
