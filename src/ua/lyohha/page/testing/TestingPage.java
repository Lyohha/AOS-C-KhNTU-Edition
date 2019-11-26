package ua.lyohha.page.testing;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import ua.lyohha.page.Page;
import ua.lyohha.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TestingPage extends Page {
    private String styleClass = "TestingPage.css";
    private String page = "TestingPage.fxml";
    private Task task;
    private List<AnswerField> answerFields = new ArrayList<>();

    public Label taskNameLabel;
    public VBox taskVBox;
    public VBox answersVBox;
    public Button checkButton;
    public Button rightAnswersButton;
    public Button menuButton;
    public Button nextButton;

    public void checkButtonClick(ActionEvent actionEvent) {

        String[] answers = new String[task.getCountAnswers()];
        for (int i = 0; i < answerFields.size(); i++) {
            if (task.getAnswers()[i].equals(answerFields.get(i).textField.getText().trim())) {
                answers[i] = "верно";
            } else {
                answers[i] = "неверно";
                for (i++; i < answerFields.size(); i++) {
                    answers[i] = "";
                }
            }
        }
        setAnswersInField(answers);
        rightAnswersButton.setDisable(false);
    }

    public void rightAnswersButtonClick(ActionEvent actionEvent) {
        checkButton.setDisable(true);
        setAnswersInField(task.getAnswers());
    }

    private void setAnswersInField(String[] answers) {
        for (int i = 0; i < answerFields.size(); i++)
            answerFields.get(i).label.setText(answers[i]);
    }

    public void menuButtonClick(ActionEvent actionEvent) {
        navigation.navigateBack();
    }

    public void nextButtonClick(ActionEvent actionEvent) {
        rightAnswersButton.setDisable(true);
        checkButton.setDisable(false);
        updateView();
    }

    public void setTask(Task task) {
        this.task = task;
        taskNameLabel.setText(task.getTaskName());
        updateView();
    }

    private void updateView() {
        this.task.create();
        taskVBox.getChildren().clear();
        task.setTaskCode(taskVBox);
        createAnswerFields();
    }

    private void createAnswerFields() {
        answerFields.clear();
        answersVBox.getChildren().clear();
        String[] answers = task.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            //поле ввода
            TextField textField = new TextField();
            textField.prefWidth(140);
            HBox.setMargin(textField, new Insets(0, 5, 0, 5));
            textField.getStyleClass().add("text-field-view");

            //поле вывода информации
            Label label = new Label();
            label.prefWidth(140);
            label.setMinWidth(140);
            HBox.setMargin(label, new Insets(4, 5, 0, 5));
            label.getStyleClass().add("label-view");
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);

            //групировка
            HBox hBox = new HBox(textField, label);
            VBox.setMargin(hBox, new Insets(20, 0, 0, 0));

            //сохранение и отображение
            answersVBox.getChildren().add(hBox);
            answerFields.add(new AnswerField(textField, label));

        }
    }


    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void initializeComponent() {
        rightAnswersButton.setDisable(true);
    }

    private class AnswerField {
        public TextField textField;
        public Label label;

        public AnswerField(TextField textField, Label label) {
            this.textField = textField;
            this.label = label;
        }
    }


}
