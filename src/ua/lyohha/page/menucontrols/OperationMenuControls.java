package ua.lyohha.page.menucontrols;

//Операции

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
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new AssignmentOperationsTask());
                break;
            case 1:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LogicalAndIncreaseOperationsTask());
                break;
            case 2:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new RelationsAndConditionsOperationsTask());
                break;
            case 3:
                ((TestingPage)menuPage.navigation.navigateTo(TestingPage.class)).setTask(new LogicalExpressionsTask());
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
        return "Операции";
    }
}
