package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.lyohha.page.menu.MenuPage;

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
        //FXMLLoader menuPageloader = new FXMLLoader();
        //Parent content = menuPageloader.load(MenuPage.class.getResource("MenuPage.fxml").openStream());
        Parent root = mainWindowloader.load(getClass().getResource("MainWindow.fxml").openStream());

        /*MenuPage controller = menuPageloader.getController();
        controller.setMenuName("Main Menu");
        String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Exit"};
        controller.setItems(Arrays.asList(items));*/

        //((MainWindow)mainWindowloader.getController()).mainVBox.getChildren().add(content);
        navigation = new Navigation(((MainWindow)mainWindowloader.getController()).mainVBox);
        MenuPage menuPage = (MenuPage)navigation.navigateTo(MenuPage.class);
        menuPage.setMenuName("Main Menu");
        String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Exit"};
        menuPage.setItems(Arrays.asList(items));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(MenuPage.class.getResource(controller.getStyleClass()).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(480);
        primaryStage.show();
    }
}
