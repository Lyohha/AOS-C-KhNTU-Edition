package ua.lyohha.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import ua.lyohha.page.Page;

import java.util.ArrayList;
import java.util.List;

public class Navigation {

    private List<Parent> pages = new ArrayList<>();
    private Pane pane;

    public Navigation(Pane pane) {
        this.pane = pane;
    }

    public void navigateTo(Class c) {
        /* FXMLLoader mainWindowloader = new FXMLLoader();
        FXMLLoader menuPageloader = new FXMLLoader();
        Parent content = menuPageloader.load(MenuPage.class.getResource("MenuPage.fxml").openStream());
        Parent root = mainWindowloader.load(getClass().getResource("MainWindow.fxml").openStream());

        MenuPage controller = menuPageloader.getController();
        controller.setMenuName("Main Menu");
        String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Exit"};
        controller.setItems(Arrays.asList(items));

        ((MainWindow)mainWindowloader.getController()).mainVBox.getChildren().add(content);*/
        Object o = null;
        try {
            o = c.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();

        Parent pageContent = null;
        if (o != null) {
            try {
                if (((Page) o).getPage() != null)
                    pageContent = loader.load(c.getResource(((Page) o).getPage()).openStream());
                else
                    pageContent = ((Page) o).getParent();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (pageContent != null) {
            if (((Page) o).getStyleClass() != null)
                pageContent.getStylesheets().add(c.getResource(((Page) o).getStyleClass()).toExternalForm());
            pages.add(pageContent);
            ((Page)loader.getController()).navigation = this;
            updateView();
        }

    }

    private void updateView() {
        if (pages.size() != 0) {
            pane.getChildren().clear();
            pane.getChildren().add(pages.get(pages.size() - 1));
        }
    }

    public void navigateBack() {
        if (pages.size() > 1) {
            pages.remove(pages.size() - 1);
            updateView();
        }
    }

    public void clearNavigationStack() {
        if(pages.size() != 0)
        {
            Parent parent = pages.get(pages.size()-1);
            pages.clear();
            pages.add(parent);
        }
    }
}
