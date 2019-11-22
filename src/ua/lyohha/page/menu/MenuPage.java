package ua.lyohha.page.menu;

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
    private String styleClass = "MenuPage.css";
    private ControlSelected controlSelected;

    public MenuPage()
    {

    }

    public void setItems(List<String> items)
    {
        this.items = items;
        UpdateView();
    }

    public void setMenuName(String name)
    {
        menuNameLabel.setText(name);
    }

    private void UpdateView()
    {
        for(int i=0;i<items.size();i++){
            MenuItem menuItem = new MenuItem();

            menuItem.number = i;
            menuItem.button = new Button();
            menuItem.button.setPrefWidth(200);

            menuItems.add(menuItem);
        }
    }

    public void setControlSelected(ControlSelected controlSelected)
    {
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

    private class MenuItem
    {
        public int number;
        public Button button;
    }
}
