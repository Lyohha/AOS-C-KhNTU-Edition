package ua.lyohha.page.menucontrols;

//Управление

import ua.lyohha.language.Language;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.testing.TestingPage;
import ua.lyohha.tasks.management.ifeleseconditional.IfElseConditionalTask;
import ua.lyohha.tasks.management.loopoperatorfor.LoopOperatorForTask;
import ua.lyohha.tasks.management.loopwhileanddowhile.LoopWhileAndDoWhileTask;
import ua.lyohha.tasks.management.nestingofoperators.NestingOfOperatorsTask;
import ua.lyohha.tasks.management.operatorswitch.OperatorSwitchTask;

public class ManagementMenuControls extends MenuControl {
    public ManagementMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new IfElseConditionalTask());
                break;
            case 1:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LoopWhileAndDoWhileTask());
                break;
            case 2:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LoopOperatorForTask());
                break;
            case 3:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new OperatorSwitchTask());
                break;
            case 4:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new NestingOfOperatorsTask());
                break;
            case 5:
                Language.removeEvent(this.menuPage);
                menuPage.navigation.navigateBack();
                break;
        }
    }

    @Override
    public String[] getItems() {
        return new String[]{
                Language.getLocalized("management_menu.item1.name"),
                Language.getLocalized("management_menu.item2.name"),
                Language.getLocalized("management_menu.item3.name"),
                Language.getLocalized("management_menu.item4.name"),
                Language.getLocalized("management_menu.item5.name"),
                Language.getLocalized("management_menu.item6.name"),
        };
    }

    @Override
    public String getMenuName() {
        return Language.getLocalized("management_menu.name");
    }
}
