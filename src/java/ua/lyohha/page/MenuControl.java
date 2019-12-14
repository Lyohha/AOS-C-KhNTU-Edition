package ua.lyohha.page;

import ua.lyohha.language.Language;
import ua.lyohha.page.menu.MenuPage;

public abstract class MenuControl {

    protected MenuPage menuPage;

    public MenuControl(MenuPage menuPage) {
        menuPage.setControlSelected(this);
        this.menuPage = menuPage;
        Language.addEvent(menuPage);
    }

    public abstract void onItemSelected(int item);

    public String[] getItems() {
        return null;
    }

    public String getMenuName() {
        return null;
    }
}
