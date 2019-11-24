package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Navigation navigation;

    public MainWindow()
    {

    }

    public VBox mainVBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainWindowloader = new FXMLLoader();
        Parent root = mainWindowloader.load(getClass().getResource("MainWindow.fxml").openStream());

         navigation = new Navigation(((MainWindow)mainWindowloader.getController()).mainVBox);

        MainMenuControls mainMenuControls = new MainMenuControls((MenuPage)navigation.navigateTo(MenuPage.class));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
}
