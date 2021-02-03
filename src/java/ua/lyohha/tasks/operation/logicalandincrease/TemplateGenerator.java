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
        int z = random.nextInt(10);
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
                /*CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(i1 ? "!x" : "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1)),
                CodeGenerator.createPart(i2 ? "!y" : "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2)),
                CodeGenerator.createPart(i3 ? "!z" : "z"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")*/
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", "),
                CodeGenerator.createPart(i1 ? "!x" : "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1)),
                CodeGenerator.createPart(i2 ? "!y" : "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2)),
                CodeGenerator.createPart(i3 ? "!z" : "z"),
                CodeGenerator.createPart(")"),
                CodeGenerator.createPart(";")
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
                CodeGenerator.createPart("\tx="),
                CodeGenerator.createPart(variables.x),
                CodeGenerator.createPart("; y="),
                CodeGenerator.createPart(variables.y),
                CodeGenerator.createPart(";")
        ));

        if (ido1 == CodeGenerator.IDOperator.PREFIXDECREMENT || ido1 == CodeGenerator.IDOperator.PREFIXINCREMENT) {
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
            variables.z = CodeGenerator.exeOperator(variables.x, n1, o1);
        } else {
            variables.z = CodeGenerator.exeOperator(variables.x, n1, o1);
            variables.x = CodeGenerator.exeOperator(variables.x, ido1);
        }


        lines.add(new HBox(
                CodeGenerator.createPart("\tz="),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; ")
               /* CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),*/

                /*CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),*/
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x)"),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", z)"),
                CodeGenerator.createPart(";")
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
        while ((variables.y == 1 &&
                o2 == CodeGenerator.Operator.DIVISION &&
                ido2 == CodeGenerator.IDOperator.PREFIXDECREMENT) ||
                (variables.y == 0 &&
                o2 == CodeGenerator.Operator.DIVISION &&
                (ido2 == CodeGenerator.IDOperator.SUFIXDECREMENT || ido2 == CodeGenerator.IDOperator.SUFIXINCREMENT)));


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
                CodeGenerator.createPart("\tz"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("="),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2)),
                CodeGenerator.createPart(" "),
                addIDO(ido2, "y"),
                CodeGenerator.createPart("; ")
                /*CodeGenerator.createPart("PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),*/
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", z)"),
                CodeGenerator.createPart(";")
        ));

        variables.z = CodeGenerator.exeOperator(variables.z, n1, o1);

        answers[3] = Integer.toString(variables.z);
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

        /*lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(" printf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", "),
                CodeGenerator.createPart("int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(")")
        ));*/

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
