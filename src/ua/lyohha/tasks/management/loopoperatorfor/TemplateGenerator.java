package ua.lyohha.tasks.management.loopoperatorfor;

import javafx.scene.control.Label;
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
        createLastLine();
    }

    private Variables createVariables() {
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        int z = 0;
        lines.add(new HBox(
                CodeGenerator.createPart("\tint", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x="),
                CodeGenerator.createPart(x),
                CodeGenerator.createPart(" y"),
                CodeGenerator.createPart(";")
        ));

        return new Variables(x, y, z);
    }

    private void createFirstExpression(Variables variables) {
        int
                n1 = random.nextInt(10);
        CodeGenerator.CompareOperator
                c1 = variables.y > n1 ? CodeGenerator.CompareOperator.MORE : CodeGenerator.CompareOperator.LESS;
        CodeGenerator.IDOperator
                ido1 = variables.y > n1 ? CodeGenerator.IDOperator.SUFIXDECREMENT : CodeGenerator.IDOperator.SUFIXINCREMENT;
        CodeGenerator.Operator
                o1 = random.nextInt(2) == 1 ? CodeGenerator.Operator.ADDITION : CodeGenerator.Operator.DIFFERENCE;


        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y="),
                CodeGenerator.createPart(variables.y),
                CodeGenerator.createPart("; y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; "),
                addIDO(ido1, "y"),
                CodeGenerator.createPart(") x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("=y;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        for (; CodeGenerator.compare(variables.y, n1, c1) == 1; variables.y = CodeGenerator.exeOperator(variables.y, ido1))
            variables.x = CodeGenerator.exeOperator(variables.x, variables.y, o1);

        answers[0] = Integer.toString(variables.x);
    }

    private void createThirdExpression(Variables variables) {
        variables.x = random.nextInt(10);
        variables.y = random.nextInt(10);
        CodeGenerator.CompareOperator
                c1 = variables.x > variables.y ? CodeGenerator.CompareOperator.MORE : CodeGenerator.CompareOperator.LESS;
        CodeGenerator.IDOperator
                ido1 = variables.x > variables.y ? CodeGenerator.IDOperator.PREFIXDECREMENT : CodeGenerator.IDOperator.PREFIXINCREMENT,
                ido2 = variables.x > variables.y ? CodeGenerator.IDOperator.PREFIXINCREMENT : CodeGenerator.IDOperator.PREFIXDECREMENT;
        int
                n1 = variables.x > variables.y ? variables.x : variables.y,
                n2 = variables.x > variables.y ? variables.y : variables.x;
        int n3 = n1 - n2;
        n3 = (n3 % 2) == 1 ? n3 + 1 : n3;
        n3 /= 2;
        n1 -= n3;
        n2 += n3;

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(x="),
                CodeGenerator.createPart(variables.x),
                CodeGenerator.createPart(", y="),
                CodeGenerator.createPart(variables.y),
                CodeGenerator.createPart("; x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart("y;"),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(", "),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(") ;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        variables.x = variables.x > variables.y ? n1 : n2;
        variables.y = variables.x > variables.y ? n2 : n1;

        answers[2] = Integer.toString(variables.x);
        answers[3] = Integer.toString(variables.y);
    }

    private void createSecondExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2 = random.nextInt(10);
        CodeGenerator.CompareOperator
                c1 = n1 > n2 ? CodeGenerator.CompareOperator.MORE : CodeGenerator.CompareOperator.LESS;
        CodeGenerator.IDOperator
                ido1 = n1 > n2 ? CodeGenerator.IDOperator.SUFIXDECREMENT : CodeGenerator.IDOperator.SUFIXINCREMENT;

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; (x=y)"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                addIDO(ido1, "y"),
                CodeGenerator.createPart(") ;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[1] = Integer.toString(variables.x = variables.y = n2);
    }

    private Label addIDO(CodeGenerator.IDOperator operator, String variable) {
        switch (operator) {
            case SUFIXDECREMENT:
                return CodeGenerator.createPart(variable + "--");
            case PREFIXDECREMENT:
                return CodeGenerator.createPart("--" + variable);
            case SUFIXINCREMENT:
                return CodeGenerator.createPart(variable + "++");
            case PREFIXINCREMENT:
                return CodeGenerator.createPart("++" + variable);
            default:
                return null;
        }
    }


    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(" printf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", "),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")")
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

    private class Variables {
        public int x;
        public int y;
        public int z;

        public Variables(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
