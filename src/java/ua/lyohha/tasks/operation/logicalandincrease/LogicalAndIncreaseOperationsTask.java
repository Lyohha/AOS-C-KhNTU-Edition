package ua.lyohha.tasks.operation.logicalandincrease;

import javafx.scene.layout.Pane;
import ua.lyohha.tasks.Task;

public class LogicalAndIncreaseOperationsTask implements Task {
    private String taskName = "Операции: логические и увеличения";
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
}
