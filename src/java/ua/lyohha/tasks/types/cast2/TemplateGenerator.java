package ua.lyohha.tasks.types.cast2;

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
        Variables variables = createVariables();
        createFirstExpression(variables);
        createSecondExpression(variables);
        createThirdExpression(variables);
        createFourthExpression(variables);

        createLastLine();
    }

    private void createFirstExpression(Variables variables) {
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(2) + 2],
                o2 = CodeGenerator.Operator.values()[random.nextInt(2) + 2];
        lines.add(new HBox(
                CodeGenerator.createPart("\tx=(y=d"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("i)"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2)),
                CodeGenerator.createPart(2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variables.variables[0])),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        if (variables.variables[0] == Variable.X)
            answers[0] = toString(CodeGenerator.exeOperator((int) CodeGenerator.exeOperator(variables.d, 2, o1), 2, o2));
        else
            answers[0] = Integer.toString((int) CodeGenerator.exeOperator(variables.d, 2, o1));
    }

    private void createSecondExpression(Variables variables) {
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(2) + 2],
                o2 = CodeGenerator.Operator.values()[random.nextInt(2) + 2];
        lines.add(new HBox(
                CodeGenerator.createPart("\ty=(x=d"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("i)"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2)),
                CodeGenerator.createPart(2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variables.variables[1])),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        if (variables.variables[1] == Variable.X)
            answers[1] = toString(CodeGenerator.exeOperator(variables.d, 2, o1));
        else
            answers[1] = Integer.toString((int) CodeGenerator.exeOperator(CodeGenerator.exeOperator(variables.d, 2, o1), 2, o2));
    }

    private void createThirdExpression(Variables variables) {
        int
                i1 = random.nextInt(9 - variables.i) + variables.i + 1,
                f1 = random.nextInt(9) + 1;

        lines.add(new HBox(
                CodeGenerator.createPart("\ty=d*(x="),
                CodeGenerator.createPart(toDouble(i1, f1)),
                CodeGenerator.createPart("/d); "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[2] = Integer.toString(i1);
    }

    private void createFourthExpression(Variables variables) {
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(2)];
        int i1, i2, f1, f2;
        if (o1 == CodeGenerator.Operator.ADDITION) {
            i1 = random.nextInt(variables.i);
            f1 = random.nextInt(10);
            i2 = variables.i - i1;
            f2 = variables.f;
        } else {
            i1 = random.nextInt(9 - variables.i) + variables.i + 1;
            f1 = random.nextInt(10);
            i2 = i1 - variables.i + 1;
            f2 = random.nextInt(10);
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tx=d*(y=(("),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")"),
                CodeGenerator.createPart(toDouble(i1, f1)),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart(toDouble(i2, f2)),
                CodeGenerator.createPart(")/d); "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(toString(variables.variables[3])),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        if (o1 == CodeGenerator.Operator.ADDITION) {
            if (variables.variables[3] == Variable.X)
                answers[3] = toString(variables.d);
            else
                answers[3] = "1";
        } else {
            answers[3] = "0";
        }
    }

    private Variables createVariables() {

        Variable[] variables = new Variable[4];

        for (int i = 0; i < variables.length; i++) {
            variables[i] = Variable.values()[random.nextInt(Variable.values().length)];
        }
        Variables v = new Variables(variables, random.nextInt(5) + 1, random.nextInt(10));

        lines.add(new HBox(
                CodeGenerator.createPart("\tdouble ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("d="),
                CodeGenerator.createPart(v.d),
                CodeGenerator.createPart(", x;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i="),
                CodeGenerator.createPart(2),
                CodeGenerator.createPart(", y;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        return v;
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
                CodeGenerator.createPart("\"%g\\n\"", CodeGenerator.CodeType.STRING),
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

    private double toDouble(int n1, int n2) {
        return ((double) n1 + (double) n2 / 10);
    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private String toString(double d) {
        return removeZero(String.format("%.2f", d).replace(',', '.'));
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
            case X:
                return "x";
            case Y:
                return "y";
            default:
                return "";
        }
    }

    private class Variables {
        private Variable[] variables;
        private int i;
        private int f;
        private double d;

        private Variables(Variable[] variables, int i, int f) {
            this.variables = variables;
            this.i = i;
            this.f = f;
            this.d = (double) i + (double) f / 10;
        }

        private Variables(Variable[] variables, double d) {
            this.d = d;
            this.variables = variables;
            this.i = (int) d;
            this.f = (int) ((d - (double) this.i) * 10);
        }
    }

    private enum Variable {
        X,
        Y,
    }
}
