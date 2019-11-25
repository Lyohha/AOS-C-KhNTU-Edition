package ua.lyohha.tasks;


import javafx.scene.control.Label;

public class CodeGenerator {

    public enum CodeType {
        OPERATOR,
        TEXT,
        DIRECTIVE,
        LIBRARY,
        DEFINE1,
        DEFINE2
    }

    public static Label CreatePart(String text, CodeType codeType)
    {
        Label label = new Label(text);

        switch (codeType)
        {
            case TEXT:
                label.getStyleClass().add("text");
                break;
            case DIRECTIVE:
                label.getStyleClass().add("preprocessor-directive");
                break;
            case LIBRARY:
                label.getStyleClass().add("library");
                break;
            case OPERATOR:
                label.getStyleClass().add("operator");
            case DEFINE1:
                label.getStyleClass().add("define-operand1");
            case DEFINE2:
                label.getStyleClass().add("define-operand2");
        }

        return label;
    }

}
