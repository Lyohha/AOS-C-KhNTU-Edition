package ua.lyohha.tasks.operation.logicalandincrease;

import javafx.scene.layout.Pane;
import ua.lyohha.language.Language;
import ua.lyohha.tasks.Task;

public class LogicalAndIncreaseOperationsTask implements Task {
    private String taskName = Language.getLocalized("operation_menu.item2.name");
    private TemplateGenerator templateGenerator;

    @Override
    public void create() {
        templateGenerator = new TemplateGenerator();
    }

    @Override
    public int getCountAnswers() {
        return 4;
    }

    @Override
    public String[] getAnswers() {
        return templateGenerator.getAnswers();
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void setTaskCode(Pane pane) {
        templateGenerator.setTemplate(pane);
    }

    @Override
    public String getImgage() {
        return "img23.jpg";
    }
}
