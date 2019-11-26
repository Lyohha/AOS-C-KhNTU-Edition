package ua.lyohha.tasks.operation.logicalandincrease;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateGenerator {

    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[]{"an1", "an2", "an3", "an4"};
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
        int z = random.nextInt(10);
        lines.add(new HBox(
                CodeGenerator.createPart("\t", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(x), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(", y=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(y), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(", z=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(z), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        return new Variables(x, y, z);
    }

    private void createFirstExpression(Variables variables) {
        CodeGenerator.CompareOperator
                o1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)],
                o2 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        boolean
                i1 = random.nextBoolean(),
                i2 = random.nextBoolean(),
                i3 = random.nextBoolean();
        CodeGenerator.LogicalOperator
                l1 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)],
                l2 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)];

        /* binary table for next variable
        x==0|l1|l1==(x==0)
         0  |0 | 1
         0  |1 | 0
         1  |0 | 0
         1  |1 | 1

         this variant i want use:
         boolean x = i1?(variables.x == 0):!(variables.x==0);
         ide change to next. check binary table for test

         */
        boolean x = i1 == (variables.x == 0);
        boolean y = i2 == (variables.y == 0);
        boolean z = i3 == (variables.z == 0);

        boolean result = CodeGenerator.compare(CodeGenerator.compare(x, y, l1), z, l2);
        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(i1 ? "!x" : "x", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1), CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(i2 ? "!y" : "y", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2), CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(i3 ? "!z" : "z", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));


        answers[0] = result ? "1" : "0";
    }

    private void createSecondExpression(Variables variables) {
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];
        CodeGenerator.IDOperator
                ido1 = CodeGenerator.IDOperator.values()[random.nextInt(CodeGenerator.IDOperator.values().length)];
        int n1 = random.nextInt(9) + 1;
        variables.x = random.nextInt(10);
        variables.y = random.nextInt(10);

        lines.add(new HBox(
                CodeGenerator.createPart("\tx=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(variables.x), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart("; y=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(variables.y), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        if (ido1 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido1 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
            variables.z = CodeGenerator.exeOperator(variables.x, n1, o1);
        } else {
            variables.z = CodeGenerator.exeOperator(variables.x, n1, o1);
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
        }


        lines.add(new HBox(
                CodeGenerator.createPart("\tz=", CodeGenerator.CodeType.TEXT),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1), CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(n1), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart("; ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("; ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        answers[1] = Integer.toString(variables.x);
        answers[2] = Integer.toString(variables.z);
    }

    private void createThirdExpression(Variables variables) {
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)],
                o2 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];
        CodeGenerator.IDOperator ido1, ido2;
        ido1 = CodeGenerator.IDOperator.values()[random.nextInt(CodeGenerator.IDOperator.values().length)];
        do {
            ido2 = CodeGenerator.IDOperator.values()[random.nextInt(CodeGenerator.IDOperator.values().length)];
        }
        while (variables.y == 1 &&
                o2 == CodeGenerator.Operator.DIVISION &&
                ido2 == CodeGenerator.IDOperator.PREFIXDECREMENT);


        int n1;

        if (ido1 == CodeGenerator.IDOperator.PREFIXINCREMENT || ido1 == CodeGenerator.IDOperator.PREFIXDECREMENT) {
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
            if (ido2 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido2 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                n1 = CodeGenerator.exeOperator(variables.x, variables.y, o2);
            } else {
                n1 = CodeGenerator.exeOperator(variables.x, variables.y, o2);
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
            }
        } else {
            if (ido2 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido2 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                n1 = CodeGenerator.exeOperator(variables.x, variables.y, o2);
            } else {
                n1 = CodeGenerator.exeOperator(variables.x, variables.y, o2);
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
            }
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
        }

        while (n1 == 0 && o1 == CodeGenerator.Operator.DIVISION)
            o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        lines.add(new HBox(
                CodeGenerator.createPart("\tz", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1), CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("=", CodeGenerator.CodeType.TEXT),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2), CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                addIDO(ido2, "y"),
                CodeGenerator.createPart("; ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        variables.z = CodeGenerator.exeOperator(variables.z, n1, o1);

        answers[3] = Integer.toString(variables.z);
    }

    private Label addIDO(CodeGenerator.IDOperator operator, String variable) {
        switch (operator) {
            case SUFIXDECREMENT:
                return CodeGenerator.createPart(variable + "--", CodeGenerator.CodeType.TEXT);
            case PREFIXDECREMENT:
                return CodeGenerator.createPart("--" + variable, CodeGenerator.CodeType.TEXT);
            case SUFIXINCREMENT:
                return CodeGenerator.createPart(variable + "++", CodeGenerator.CodeType.TEXT);
            case PREFIXINCREMENT:
                return CodeGenerator.createPart("++" + variable, CodeGenerator.CodeType.TEXT);
            default:
                return null;
        }
    }


    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("<stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("printf(", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.TEXT)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("main()", CodeGenerator.CodeType.TEXT)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{", CodeGenerator.CodeType.TEXT)
        ));
    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}", CodeGenerator.CodeType.TEXT)
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
