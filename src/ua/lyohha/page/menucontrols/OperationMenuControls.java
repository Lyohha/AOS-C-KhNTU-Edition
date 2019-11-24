package ua.lyohha.page.menucontrols;

//Операции

import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;

public class OperationMenuControls extends MenuControl {
    public OperationMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                menuPage.navigation.navigateBack();
                break;
        }
    }

    @Override
    public String[] getItems() {
        return new String[]{
                "Операции присваивания",
                "Операции: логические и увеличения",
                "Операции отношения и условия",
                "Логические выражения",
                "Назад"};
    }

    @Override
    public String getMenuName() {
        return "Операции:";
    }
}
