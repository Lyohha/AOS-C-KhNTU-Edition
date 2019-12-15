package ua.lyohha.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ua.lyohha.language.Language;
import ua.lyohha.language.LanguageChangeEvent;
import ua.lyohha.options.Options;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.menucontrols.MainMenuControls;
import ua.lyohha.themes.ThemeChangeEvent;
import ua.lyohha.themes.Themes;

public class MainWindow extends Application implements LanguageChangeEvent, ThemeChangeEvent {

    public Label universityNameLabel;
    public GridPane frameGridPane;
    public GridPane mainGridPane;
    private Navigation navigation;

    public MainWindow() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Options.load();
        Language.load();
        Language.loadLanguageList();
        Language.setLanguage(Options.get("language"));
        Themes.setTheme(Options.get("theme"));

        FXMLLoader mainWindowLoader = new FXMLLoader();
        Parent root = mainWindowLoader.load(getClass().getResource("/assets/window/MainWindow.fxml").openStream());
        MainWindow mainWindow = mainWindowLoader.getController();
        Language.addEvent(mainWindow);
        Themes.addEvent(mainWindow);
        mainWindow.universityNameLabel.setText(Language.getLocalized("university.name"));
        navigation = new Navigation(mainWindow.frameGridPane);
        onThemeChange(mainWindow);

        MainMenuControls mainMenuControls = new MainMenuControls((MenuPage) navigation.navigateTo(MenuPage.class));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("ATS-C KhNTU Edition");
        primaryStage.show();
    }

    @Override
    public void onLanguageChange() {
        universityNameLabel.setText(Language.getLocalized("university.name"));
    }

    @Override
    public void onThemeChange() {
        onThemeChange(this);
    }

    private void onThemeChange(MainWindow mainWindow) {

        switch (Themes.getTheme()) {
            case LIGHT:
            case ANIME:
                mainWindow.mainGridPane.setStyle("-fx-background-color: \"#EAEAEA\"");
                mainWindow.universityNameLabel.setStyle("-fx-text-fill: black");
                break;
            case DARK:
            default:
                mainWindow.mainGridPane.setStyle("-fx-background-color: \"#3C3F41\"");
                mainWindow.universityNameLabel.setStyle("-fx-text-fill: white");
                break;
        }
    }
}
