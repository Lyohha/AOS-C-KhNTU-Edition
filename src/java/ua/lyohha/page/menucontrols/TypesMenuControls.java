package ua.lyohha.page.menucontrols;

//Простые типы и строки

import ua.lyohha.language.Language;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.testing.TestingPage;
import ua.lyohha.tasks.types.cast1.Cast1Task;
import ua.lyohha.tasks.types.cast2.Cast2Task;
import ua.lyohha.tasks.types.stringassignment.StringAssignmentTask;
import ua.lyohha.tasks.types.stringconvert.StringConvertTask;
import ua.lyohha.tasks.types.stringindexing.StringIndexingTask;

public class TypesMenuControls extends MenuControl {

    public TypesMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new Cast1Task());
                break;
            case 1:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new Cast2Task());
                break;
            case 2:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new StringAssignmentTask());
                break;
            case 3:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new StringIndexingTask());
                break;
            case 4:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new StringConvertTask());
                break;
            case 5:
                menuPage.navigation.navigateBack();
                break;
        }
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String[] getItems() {
        return new String[]{
                Language.getLocalized("types_menu.item1.name"),
                Language.getLocalized("types_menu.item2.name"),
                Language.getLocalized("types_menu.item3.name"),
                Language.getLocalized("types_menu.item4.name"),
                Language.getLocalized("types_menu.item5.name"),
                Language.getLocalized("types_menu.item6.name")
        };
    }

    @Override
    public String getMenuName() {
        return Language.getLocalized("types_menu.name");
    }
}
