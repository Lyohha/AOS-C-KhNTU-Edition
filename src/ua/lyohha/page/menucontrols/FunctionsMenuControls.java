package ua.lyohha.page.menucontrols;

//Блоки и функции

import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.testing.TestingPage;
import ua.lyohha.tasks.functions.nestingblocks.NestingBlocksTask;

public class FunctionsMenuControls extends MenuControl {

    public FunctionsMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new NestingBlocksTask());
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
                "Классы памяти: вложенность блоков",
                "Классы памяти: функции (часть 1)",
                "Классы памяти: функции (часть 2)",
                "Классы памяти: Файлы",
                "Функция Squeeze",
                "Назад"};
    }

    @Override
    public String getMenuName() {
        return "Блоки и функции";
    }
}
