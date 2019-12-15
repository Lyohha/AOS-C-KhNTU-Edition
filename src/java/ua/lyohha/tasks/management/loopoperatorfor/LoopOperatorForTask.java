package ua.lyohha.tasks.management.loopoperatorfor;

import com.sun.istack.internal.NotNull;
import javafx.scene.layout.Pane;
import ua.lyohha.language.Language;
import ua.lyohha.tasks.Task;

public class LoopOperatorForTask implements Task {
    private String taskName = Language.getLocalized("management_menu.item3.name");
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
    public void setTaskCode(@NotNull Pane pane) {
        templateGenerator.setTemplate(pane);
    }
}
