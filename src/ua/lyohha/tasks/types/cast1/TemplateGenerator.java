package ua.lyohha.tasks.types.cast1;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateGenerator {
    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[4];
    private Random random;

    public TemplateGenerator() {
        createTemplate();
    }

    public void setTemplate(Pane pane) {
        for (int i = 0; i < lines.size(); i++) pane.getChildren().add(lines.get(i));
    }

    public String[] getAnswers() {
        return answers;
    }

    private void createTemplate() {
        random = new Random(System.currentTimeMillis());
        createFirstsLine();
        Variable[] variables = createVariables();
        createFirstExpression(variables[0]);
        createSecondExpression(variables[1]);
        createThirdExpression(variables[2]);
        createFourthExpression(variables[3]);

        createLastLine();
    }

    private void createFirstExpression(Variable variable) {
        int
                n1 = random.nextInt(20) + 80,
                n2 = random.nextInt(8) + 2;

        lines.add(new HBox(
                CodeGenerator.createPart("\ti=l=f=d="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("/"),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variable)),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[0] = Integer.toString(n1 / n2);
    }

    private void createSecondExpression(Variable variable) {
        int
                n1 = random.nextInt(20) + 80,
                n2 = random.nextInt(8) + 2;

        lines.add(new HBox(
                CodeGenerator.createPart("\td=f=l=i="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("/"),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variable)),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[1] = Integer.toString(n1 / n2);
    }

    private void createThirdExpression(Variable variable) {
        int
                n1 = random.nextInt(20) + 80,
                n2 = random.nextInt(8) + 2;

        lines.add(new HBox(
                CodeGenerator.createPart("\ti=l=f=d="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("/"),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart(".; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variable)),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        switch (variable) {
            case L:
            case I:
                answers[2] = Integer.toString(n1 / n2);
                break;
            case F:
            case D:
                answers[2] = toString((float) n1 / n2);
                break;
        }

    }

    private void createFourthExpression(Variable variable) {
        int
                n1 = random.nextInt(20) + 80,
                n2 = random.nextInt(8) + 2;

        lines.add(new HBox(
                CodeGenerator.createPart("\td=f=l=i=("),
                CodeGenerator.createPart("double", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")"),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("/"),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variable)),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[3] = Integer.toString(n1 / n2);
    }

    private Variable[] createVariables() {

        lines.add(new HBox(
                CodeGenerator.createPart("\tdouble ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("d;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tfloat ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("f;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tlong ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("l;")
        ));

        Variable[] variables = new Variable[4];

        for (int i = 0; i < variables.length; i++) {
            variables[i] = Variable.values()[random.nextInt(Variable.values().length)];
        }

        return variables;
    }

    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" PRINT(x)", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(" printf("),
                CodeGenerator.createPart("\"%.4g\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", ("),
                CodeGenerator.createPart("double", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")"),
                CodeGenerator.createPart("x)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" main()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));
    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private String toString(float f) {
        return removeZero(String.format("%.2f", f).replace(',', '.'));
    }

    private String removeZero(String string) {
        if (string.charAt(string.length() - 1) == '.')
            return string.substring(0, string.length() - 1);

        if (string.charAt(string.length() - 1) != '0')
            return string;

        return removeZero(string.substring(0, string.length() - 1));
    }

    private String toString(Variable variable) {
        switch (variable) {
            case D:
                return "d";
            case F:
                return "f";
            case I:
                return "i";
            case L:
                return "l";
            default:
                return "";
        }
    }

    private enum Variable {
        D,
        I,
        L,
        F
    }
}
