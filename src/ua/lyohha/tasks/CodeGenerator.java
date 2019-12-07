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
        DEFINE2,
        COMMENT
    }

    public enum Operator {
        ADDITION,
        DIFFERENCE,
        MULTIPLICATION,
        DIVISION
    }

    public enum IDOperator {
        PREFIXINCREMENT,
        PREFIXDECREMENT,
        SUFIXINCREMENT,
        SUFIXDECREMENT
    }

    public enum CompareOperator {
        MORE,
        LESS,
        MOREOREQUEL,
        LESSOREQUEL,
        EQUEL,
        NOTEQUEL
    }

    public enum LogicalOperator {
        AND,
        OR
    }

    public static Label createPart(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("text");
        return label;
    }

    public static Label createPart(int number) {
        Label label = new Label(Integer.toString(number));
        label.getStyleClass().add("numbers");
        return label;
    }

    public static Label createPart(CodeGenerator.IDOperator operator, String variable) {
        switch (operator) {
            case SUFIXDECREMENT:
                return createPart(variable + "--");
            case PREFIXDECREMENT:
                return createPart("--" + variable);
            case SUFIXINCREMENT:
                return createPart(variable + "++");
            case PREFIXINCREMENT:
                return createPart("++" + variable);
            default:
                return null;
        }
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
            case COMMENT:
                label.getStyleClass().add("comment");
                break;
        }

        return label;
    }

    public static int exeOperator(int a, int b, Operator op) {
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

    public static int compare(int a, int b, CompareOperator operator) {
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

    public static String getOperator(Operator operator) {
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

    public static String getOperator(LogicalOperator operator) {
        switch (operator) {
            case AND:
                return "&&";
            case OR:
                return "||";
            default:
                return "";
        }
    }

    public static String getOperator(IDOperator operator) {
        if (operator == IDOperator.PREFIXDECREMENT || operator == IDOperator.SUFIXDECREMENT)
            return "--";
        else
            return "++";
    }

    public static int exeOperator(int param, IDOperator operator) {
        if (operator == IDOperator.PREFIXDECREMENT || operator == IDOperator.SUFIXDECREMENT)
            param--;
        else
            param++;
        return param;
    }

    public static String getOperator(CompareOperator operator) {
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


    public static boolean compare(boolean a, boolean b, LogicalOperator operator) {
        switch (operator) {
            case OR:
                return a || b;
            case AND:
                return a && b;
            default:
                return false;
        }

    }
}
