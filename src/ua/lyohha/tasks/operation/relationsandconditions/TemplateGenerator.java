package ua.lyohha.tasks.operation.relationsandconditions;

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
        int x = random.nextInt(30);
        int y = random.nextInt(30);
        int z = random.nextInt(30);
        lines.add(new HBox(
                CodeGenerator.createPart("\tint", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x="),
                CodeGenerator.createPart(x),
                CodeGenerator.createPart(", y="),
                CodeGenerator.createPart(y),
                CodeGenerator.createPart(", z="),
                CodeGenerator.createPart(z),
                CodeGenerator.createPart(";")
        ));

        return new Variables(x, y, z);
    }

    private void createFirstExpression(Variables variables) {
        CodeGenerator.Operator o1, o2;
        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        while (true) {
            int y = variables.y;
            o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];
            o2 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

            if (o2 == CodeGenerator.Operator.DIVISION && variables.z == 0)
                continue;
            y = CodeGenerator.exeOperator(y, variables.z, o2);
            if (y == 0 && o1 == CodeGenerator.Operator.DIVISION)
                continue;
            variables.y = y;
            variables.x = CodeGenerator.exeOperator(variables.x, y, o1);

            break;
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tx"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("=y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2)),
                CodeGenerator.createPart("=z;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart("y?y:x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[0] = CodeGenerator.compare(variables.x, variables.y, c1) == 1 ? Integer.toString(variables.y) : Integer.toString(variables.x);

    }

    private void createSecondExpression(Variables variables) {
        CodeGenerator.IDOperator
                ido1 = CodeGenerator.IDOperator.values()[random.nextInt(CodeGenerator.IDOperator.values().length)],
                ido2 = CodeGenerator.IDOperator.values()[random.nextInt(CodeGenerator.IDOperator.values().length)];
        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        CodeGenerator.Operator o1;
        do {
            o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];
        }
        while (o1 == CodeGenerator.Operator.DIVISION
                && (CodeGenerator.compare(variables.x, variables.y, c1) == 1 ? variables.x : variables.y) == 1
                && ido1 == CodeGenerator.IDOperator.PREFIXDECREMENT);

        if (CodeGenerator.compare(variables.x, variables.y, c1) == 1) {
            if (ido1 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido1 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
                variables.x = CodeGenerator.exeOperator(variables.x, ido1);
                variables.z = CodeGenerator.exeOperator(variables.z, variables.x, o1);
            } else {
                variables.z = CodeGenerator.exeOperator(variables.z, variables.x, o1);
                variables.x = CodeGenerator.exeOperator(variables.x, ido1);
            }
        } else {
            if (ido2 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido2 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                variables.z = CodeGenerator.exeOperator(variables.z, variables.y, o1);
            } else {
                variables.z = CodeGenerator.exeOperator(variables.z, variables.y, o1);
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
            }
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("=x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart("y?"),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(":"),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[1] = Integer.toString(variables.z);
        answers[2] = Integer.toString(variables.y);
    }

    private void createThirdExpression(Variables variables) {
        variables.x = random.nextInt(10);
        variables.y = random.nextInt(10);
        variables.z = random.nextInt(10);

        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)],
                c2 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        int result = CodeGenerator.compare(CodeGenerator.compare(variables.z, variables.y, c1), variables.x, c2);

        lines.add(new HBox(
                CodeGenerator.createPart("\tx="),
                CodeGenerator.createPart(variables.x),
                CodeGenerator.createPart("; y="),
                CodeGenerator.createPart(variables.y),
                CodeGenerator.createPart("; z="),
                CodeGenerator.createPart(variables.z),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("(z"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c2)),
                CodeGenerator.createPart("x)?1:0"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[3] = Integer.toString(result);

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
