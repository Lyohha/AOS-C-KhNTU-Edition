package ua.lyohha.page.menucontrols;

//Простые типы и строки

import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;

public class TypesMenuControls extends MenuControl {

    public TypesMenuControls(MenuPage menuPage) {
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
                break;
            case 5:
                menuPage.navigation.navigateBack();
                break;
        }
    }

    @Override
    public String[] getItems() {
        return new String[]{
                "Приведение типов (1)",
                "Приведение типов (2)",
                "Строки: strcat, strcmp, присваивание",
                "Строки: strlen, strstr, индексация",
                "Строки: itoa, strrev, atoi",
                "Назад"};
    }

    @Override
    public String getMenuName() {
        return "Простые типы и строки";
    }
}
