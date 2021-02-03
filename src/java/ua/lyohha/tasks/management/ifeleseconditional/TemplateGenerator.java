package ua.lyohha.tasks.management.ifeleseconditional;

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

    private Variables createVariables() {
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        int z = random.nextInt(10);
        lines.add(new HBox(
                CodeGenerator.createPart("\tint", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x, y="),
                CodeGenerator.createPart(y),
                CodeGenerator.createPart(", z;")
        ));

        return new Variables(x, y, z);
    }

    private void createFirstExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2 = random.nextInt(10),
                n3 = random.nextInt(10);

        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];

        variables.x = CodeGenerator.compare(variables.y, n1, c1) == 1 ? n2 : n3;

        lines.add(new HBox(
                CodeGenerator.createPart("\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(") x="),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart("else", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x="),
                CodeGenerator.createPart(n3),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                /*CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")*/
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x);")
        ));

        answers[0] = Integer.toString(variables.x);

    }

    private void createSecondExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2 = random.nextInt(10),
                n3 = random.nextInt(10),
                n4 = random.nextInt(10);
        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)],
                c2 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        if (CodeGenerator.compare(variables.y, n1, c1) == 1) {
            if (CodeGenerator.compare(variables.y, n2, c2) == 1)
                variables.x = n3;
            else
                variables.x = n4;
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(") "),
                CodeGenerator.createPart("if", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c2)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart(") x="),
                CodeGenerator.createPart(n3),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\telse ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("x="),
                CodeGenerator.createPart(n4),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                /*CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")*/
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x);")
        ));

        answers[1] = Integer.toString(variables.x);
    }

    private void createThirdExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2 = random.nextInt(10),
                n3 = random.nextInt(10),
                n4 = random.nextInt(10),
                n5 = random.nextInt(10);
        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)],
                c2 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];

        if ((variables.z = CodeGenerator.compare(variables.y, n1, c1)) == 1)
            variables.x = n2;
        else if (CodeGenerator.compare(variables.y, n3, c2) == 1)
            variables.x = n4;
        else
            variables.x = n5;

        lines.add(new HBox(
                CodeGenerator.createPart("\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(z=y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(") x="),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\telse if", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c2)),
                CodeGenerator.createPart(n3),
                CodeGenerator.createPart(") x="),
                CodeGenerator.createPart(n4),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\telse ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("x="),
                CodeGenerator.createPart(n5),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                /*CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")*/
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x);")
        ));

        answers[2] = Integer.toString(variables.x);

    }

    private void createFourthExpression(Variables variables) {
        int n1 = random.nextInt(10);
        String[] vars = new String[]{"x", "y", "z"};
        int pos = random.nextInt(vars.length);
        if ((variables.x = variables.z = variables.y) != 0) {
            if (pos == 0)
                variables.x = n1;
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(x=z=y) ; "),
                CodeGenerator.createPart(vars[pos]),
                CodeGenerator.createPart("="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                /*CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")*/
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x);")
        ));

        answers[3] = Integer.toString(variables.x);
    }


    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart("<stdio.h>", CodeGenerator.CodeType.LIBRARY)
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
