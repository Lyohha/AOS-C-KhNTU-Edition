package ua.lyohha.tasks;


import javafx.scene.control.Label;

public class CodeGenerator {

    public enum CodeType {
        OPERATOR,
        TEXT,
        STRING,
        NUMBER,
        DIRECTIVE,
        LIBRARY,
        DEFINE1,
        DEFINE2
    }

    public enum Operator
    {
        ADDITION,
        DIFFERENCE,
        MULTIPLICATION,
        DIVISION
    }

    public enum CompareOperator
    {
        MORE,
        LESS,
        MOREOREQUEL,
        LESSOREQUEL,
        EQUEL,
        NOTEQUEL
    }

    public static Label createPart(String text, CodeType codeType)
    {
        Label label = new Label(text);

        switch (codeType)
        {
            case TEXT:
                label.getStyleClass().add("text");
                break;
            case STRING:
                label.getStyleClass().add("string");
                break;
            case NUMBER:
                label.getStyleClass().add("numbers");
                break;
            case DIRECTIVE:
                label.getStyleClass().add("preprocessor-directive");
                break;
            case LIBRARY:
                label.getStyleClass().add("library");
                break;
            case OPERATOR:
                label.getStyleClass().add("operator");
                break;
            case DEFINE1:
                label.getStyleClass().add("define-operand1");
                break;
            case DEFINE2:
                label.getStyleClass().add("define-operand2");
                break;
        }

        return label;
    }

}
