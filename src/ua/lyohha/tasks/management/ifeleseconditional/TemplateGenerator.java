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
        //createSecondExpression(variables);
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
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[0] = Integer.toString(variables.x);

    }


    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart("<stdio.h>", CodeGenerator.CodeType.LIBRARY)
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
