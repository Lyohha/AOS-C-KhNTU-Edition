package ua.lyohha.tasks;

import javafx.scene.layout.Pane;

public interface Task {
    void create();
    int getCountAnswers();
    String[] getAnswers();
    String[] checkAnswers(String[] answers);
    String getTaskName();
    void setTaskCode(Pane pane);
}
