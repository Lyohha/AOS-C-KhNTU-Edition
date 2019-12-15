package ua.lyohha.page.menucontrols;

import javafx.application.Platform;
import ua.lyohha.language.Language;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.settings.SettingsPage;
import ua.lyohha.themes.Themes;

public class MainMenuControls extends MenuControl {

    public MainMenuControls(MenuPage menuPage) {
        super(menuPage);
        Language.addEvent(menuPage);
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
                menuPage.navigation.navigateTo(SettingsPage.class);
                break;
            case 5:
                Platform.exit();
                break;
        }
    }

    @Override
    public String getImage() {
        return Themes.getTheme() == Themes.Theme.ANIME ? "img1.jpg" : null;
    }

    @Override
    public String[] getItems() {
        return new String[]{
                Language.getLocalized("main_menu.item1.name"),
                Language.getLocalized("main_menu.item2.name"),
                Language.getLocalized("main_menu.item3.name"),
                Language.getLocalized("main_menu.item4.name"),
                Language.getLocalized("main_menu.item5.name"),
                Language.getLocalized("main_menu.item6.name"),
        };
    }

    @Override
    public String getMenuName() {
        return Language.getLocalized("main_menu.name");
    }
}
