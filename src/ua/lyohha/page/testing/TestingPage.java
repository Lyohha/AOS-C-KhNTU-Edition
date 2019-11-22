package ua.lyohha.page.testing;

import javafx.scene.Parent;
import ua.lyohha.page.Page;

public class TestingPage extends Page {

    private String styleClass = "TestingPage.css";
    private String page = "TestingPage.fxml";

    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void initializeComponent() {

    }
}
