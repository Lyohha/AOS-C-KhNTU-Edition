package ua.lyohha.page;

import ua.lyohha.page.menu.MenuPage;

public abstract class MenuControl {

    protected MenuPage menuPage;

    public MenuControl(MenuPage menuPage) {
        this.menuPage = menuPage;
        this.menuPage.setControlSelected(this);
    }

    public abstract void onItemSelected(int item);

    public abstract String getImage();

    public String[] getItems() {
        return null;
    }

    public String getMenuName() {
        return null;
    }
}
