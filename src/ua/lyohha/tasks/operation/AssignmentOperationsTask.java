package ua.lyohha.tasks.operation;

import javafx.scene.layout.Pane;
import ua.lyohha.tasks.Task;

public class AssignmentOperationsTask implements Task {
    @Override
    public void create() {

    }

    @Override
    public int getCountAnswers() {
        return 0;
    }

    @Override
    public String[] getAnswers() {
        return new String[0];
    }

    @Override
    public String[] checkAnswers(String[] answers) {
        return new String[0];
    }

    @Override
    public String getTaskName() {
        return null;
    }

    @Override
    public void setTaskCode(Pane pane) {

    }
}
