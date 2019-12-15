package ua.lyohha.page.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ua.lyohha.language.LanguageChangeEvent;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.Page;
import ua.lyohha.themes.Themes;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends Page implements LanguageChangeEvent {

    private String[] items;
    private List<MenuItem> menuItems = new ArrayList<>();
    private String styleClassDark = "/assets/page/menu/MenuPageDark.css";
    private String styleClassLight = "/assets/page/menu/MenuPageLight.css";
    private String styleClassAnime = "/assets/page/menu/MenuPageAnime.css";
    private String page = "/assets/page/menu/MenuPage.fxml";
    private MenuControl menuControl = null;

    public VBox mainVBox;
    public Label menuNameLabel;

    public MenuPage() {

    }

    private void setItems(String[] items) {
        if (items != null) {
            this.items = items;
            updateView();
        }
    }

    private void setMenuName(String name) {
        if (name != null)
            menuNameLabel.setText(name);
    }

    private void updateView() {
        clearView();
        for (int i = 0; i < items.length; i++) {
            MenuItem menuItem = new MenuItem();

            menuItem.number = i;
            menuItem.button = new Button();
            menuItem.button.setPrefWidth(300);
            menuItem.button.setOnAction(menuItem);
            VBox.setMargin(menuItem.button, new Insets(10, 0, 0, 0));
            menuItem.button.setText(items[i]);
            menuItem.button.getStyleClass().add("button-view");

            mainVBox.getChildren().add(menuItem.button);
            menuItems.add(menuItem);
        }
    }

    private void clearView() {
        while (menuItems.size() != 0) {
            mainVBox.getChildren().remove(menuItems.get(0).button);
            menuItems.remove(0);
        }
    }

    public void setControlSelected(MenuControl menuControl) {

        this.menuControl = menuControl;
        setItems(menuControl.getItems());
        setMenuName(menuControl.getMenuName());
        navigation.setImage(this.menuControl.getImage());
    }


    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {

        switch (Themes.getTheme()) {
            case LIGHT:
                return styleClassLight;
            case ANIME:
                return styleClassAnime;
            case DARK:
            default:
                return styleClassDark;
        }
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public String getImage() {
        return menuControl == null ? null : menuControl.getImage();
    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void onLanguageChange() {
        setMenuName(menuControl.getMenuName());
        setItems(menuControl.getItems());
    }

    private class MenuItem implements EventHandler<ActionEvent> {
        public int number;
        public Button button;

        @Override
        public void handle(ActionEvent event) {
            if (menuControl != null)
                menuControl.onItemSelected(number);
        }
    }
}
