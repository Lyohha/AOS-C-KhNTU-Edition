package ua.lyohha.page.menucontrols;

//Операции

import ua.lyohha.language.Language;
import ua.lyohha.page.MenuControl;
import ua.lyohha.page.menu.MenuPage;
import ua.lyohha.page.testing.TestingPage;
import ua.lyohha.tasks.operation.assignmentoperation.AssignmentOperationsTask;
import ua.lyohha.tasks.operation.logicalandincrease.LogicalAndIncreaseOperationsTask;
import ua.lyohha.tasks.operation.logicalexpressions.LogicalExpressionsTask;
import ua.lyohha.tasks.operation.relationsandconditions.RelationsAndConditionsOperationsTask;

public class OperationMenuControls extends MenuControl {
    public OperationMenuControls(MenuPage menuPage) {
        super(menuPage);
    }

    @Override
    public void onItemSelected(int item) {
        switch (item) {
            case 0:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new AssignmentOperationsTask());
                break;
            case 1:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LogicalAndIncreaseOperationsTask());
                break;
            case 2:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new RelationsAndConditionsOperationsTask());
                break;
            case 3:
                ((TestingPage) menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LogicalExpressionsTask());
                break;
            case 4:
                menuPage.navigation.navigateBack();
                break;
        }
    }

    @Override
    public String getImage() {
        return "img3.jpg";
    }

    @Override
    public String[] getItems() {
        return new String[]{
                Language.getLocalized("operation_menu.item1.name"),
                Language.getLocalized("operation_menu.item2.name"),
                Language.getLocalized("operation_menu.item3.name"),
                Language.getLocalized("operation_menu.item4.name"),
                Language.getLocalized("operation_menu.item5.name"),
        };
    }

    @Override
    public String getMenuName() {
        return Language.getLocalized("operation_menu.name");
    }
}
