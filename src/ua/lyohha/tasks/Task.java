package ua.lyohha.tasks;

import com.sun.istack.internal.NotNull;
import javafx.scene.layout.Pane;

public interface Task {
    void create();

    int getCountAnswers();

    String[] getAnswers();

    String[] checkAnswers(@NotNull String[] answers);

    String getTaskName();

    void setTaskCode(@NotNull Pane pane);
}
