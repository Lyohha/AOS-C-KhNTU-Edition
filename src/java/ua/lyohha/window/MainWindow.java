package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.menucontrols.MainMenuControls;

public class MainWindow extends Application {

    public Label universityNameLabel;
    public GridPane frameGridPane;
    public GridPane mainGridPane;
    private Navigation navigation;

    public MainWindow() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainWindowLoader = new FXMLLoader();
        Parent root = mainWindowLoader.load(getClass().getResource("/window/MainWindow.fxml").openStream());
        MainWindow mainWindow = mainWindowLoader.getController();
        navigation = new Navigation(mainWindow.frameGridPane);
        mainWindow.mainGridPane.setStyle("-fx-background-color: \"#3C3F41\"");

        mainWindow.universityNameLabel.setStyle("-fx-text-fill: white");

        MainMenuControls mainMenuControls = new MainMenuControls((MenuPage) navigation.navigateTo(MenuPage.class));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("ATS-C KhNTU Edition");
        primaryStage.show();
    }
}
