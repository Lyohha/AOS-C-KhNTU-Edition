package ua.lyohha.tasks.functions.squeeze;

import com.sun.istack.internal.NotNull;
import javafx.scene.layout.Pane;
import ua.lyohha.language.Language;
import ua.lyohha.tasks.Task;

public class SqueezeTask implements Task {
    private String taskName = Language.getLocalized("functions_menu.item5.name");
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

    @Override
    public String getImgage() {
        return "img16.jpg";
    }
}
