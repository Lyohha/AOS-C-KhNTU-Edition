package ua.lyohha.page.menucontrols;

//Блоки и функции

import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.testing.TestingPage;
import ua.lyohha.tasks.functions.files.FilesTask;
import ua.lyohha.tasks.functions.functionspart1.FunctionsPart1Task;
import ua.lyohha.tasks.functions.functionspart2.FunctionsPart2Task;
import ua.lyohha.tasks.functions.nestingblocks.NestingBlocksTask;
import ua.lyohha.tasks.functions.squeeze.SqueezeTask;

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
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new FunctionsPart1Task());
                break;
            case 2:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new FunctionsPart2Task());
                break;
            case 3:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new FilesTask());
                break;
            case 4:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new SqueezeTask());
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
                "Функция squeeze",
                "Назад"};
    }

    @Override
    public String getMenuName() {
        return "Блоки и функции";
    }
}
