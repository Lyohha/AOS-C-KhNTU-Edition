package ua.lyohha.page.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ua.lyohha.page.ControlSelected;
import ua.lyohha.page.Page;

import java.util.ArrayList;
import java.util.List;

public class MenuPage implements Page {

    private List<String> items = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private String styleClass = "page\\menu\\MenuPage.css";
    private ControlSelected controlSelected;

    public MenuPage() {

    }

    public void setItems(List<String> items) {
        this.items = items;
        updateView();
    }

    public void setMenuName(String name) {
        menuNameLabel.setText(name);
    }

    private void updateView() {
        clearView();
        for (int i = 0; i < items.size(); i++) {
            MenuItem menuItem = new MenuItem();

            menuItem.number = i;
            menuItem.button = new Button();
            menuItem.button.setPrefWidth(200);
            menuItem.button.setOnAction(menuItem);
            VBox.setMargin(menuItem.button, new Insets(10, 0, 0, 0));
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

    public void setControlSelected(ControlSelected controlSelected) {
        this.controlSelected = controlSelected;
    }

    public VBox mainVBox;
    public Label menuNameLabel;

    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    private class MenuItem implements EventHandler<ActionEvent> {
        public int number;
        public Button button;

        @Override
        public void handle(ActionEvent event) {
            if (controlSelected != null)
                controlSelected.onItemSelected(number);
        }
    }
}
