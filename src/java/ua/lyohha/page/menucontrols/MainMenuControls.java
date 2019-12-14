package ua.lyohha.page.menucontrols;

import javafx.application.Platform;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;

public class MainMenuControls extends MenuControl {

    public MainMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                new OperationMenuControls((MenuPage) menuPage.navigation.navigateTo(MenuPage.class));
                break;
            case 1:
                new ManagementMenuControls((MenuPage) menuPage.navigation.navigateTo(MenuPage.class));
                break;
            case 2:
                new FunctionsMenuControls((MenuPage) menuPage.navigation.navigateTo(MenuPage.class));
                break;
            case 3:
                new TypesMenuControls((MenuPage) menuPage.navigation.navigateTo(MenuPage.class));
                break;
            case 4:
                Platform.exit();
                break;
        }
    }

    @Override
    public String[] getItems() {
        return new String[]{"Операции", "Управление", "Блоки и функции", "Простые типы и строки", "Выход"};
    }

    @Override
    public String getMenuName() {
        return "Выбор темы";
    }
}
