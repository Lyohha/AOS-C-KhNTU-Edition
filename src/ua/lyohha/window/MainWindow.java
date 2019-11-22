package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.lyohha.page.menu.MenuPage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends Application {

    public MainWindow()
    {

    }

    public VBox mainVBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("page\\menu\\MenuPage.fxml").openStream());
        MenuPage controller = loader.getController();
        controller.setMenuName("Main Menu");
        String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Exit"};
        controller.setItems(Arrays.asList(items));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(controller.getStyleClass()).toExternalForm());
    }
}
