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
        /*createFirstExpression(variables);
        createSecondExpression(variables);*/
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
