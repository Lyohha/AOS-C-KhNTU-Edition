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

    public enum Operator {
        ADDITION,
        DIFFERENCE,
        MULTIPLICATION,
        DIVISION
    }

    public enum CompareOperator {
        MORE,
        LESS,
        MOREOREQUEL,
        LESSOREQUEL,
        EQUEL,
        NOTEQUEL
    }

    public static Label createPart(String text, CodeType codeType) {
        Label label = new Label(text);

        switch (codeType) {
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

    public static int exeOperator(int a, int b, CodeGenerator.Operator op) {
        switch (op) {
            case MULTIPLICATION:
                return a * b;
            case DIFFERENCE:
                return a - b;
            case DIVISION:
                return a / b;
            case ADDITION:
                return a + b;
            default:
                return 0;
        }
    }

    public static int compare(int a, int b, CodeGenerator.CompareOperator operator) {
        switch (operator) {
            case EQUEL:
                return a == b ? 1 : 0;
            case NOTEQUEL:
                return a != b ? 1 : 0;
            case MOREOREQUEL:
                return a >= b ? 1 : 0;
            case LESSOREQUEL:
                return a <= b ? 1 : 0;
            case MORE:
                return a > b ? 1 : 0;
            case LESS:
                return a < b ? 1 : 0;
            default:
                return 0;
        }
    }

    public static String getOperator(CodeGenerator.Operator operator) {
        switch (operator) {
            case ADDITION:
                return "+";
            case DIVISION:
                return "/";
            case DIFFERENCE:
                return "-";
            case MULTIPLICATION:
                return "*";
            default:
                return "";
        }
    }

    public static String getOperator(CodeGenerator.CompareOperator operator) {
        switch (operator) {
            case LESS:
                return "<";
            case MORE:
                return ">";
            case EQUEL:
                return "==";
            case NOTEQUEL:
                return "!=";
            case LESSOREQUEL:
                return "<=";
            case MOREOREQUEL:
                return ">=";
            default:
                return "";
        }
    }

}
