package ua.lyohha.tasks.management.nestingofoperators;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateGenerator {
    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[2];
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
        String text = createVariables();
        createFirstExpression(text);
        createSecondExpression(text);
        createLastLine();
    }

    private String createVariables() {
        char[] t = new char[]{
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10))
        };
        String text = new String(t);

        lines.add(new HBox(
                CodeGenerator.createPart("\tchar", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("* input = "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(text, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i=,"),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart(", num="),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart(", sym="),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("c;")
        ));

        return text;
    }

    private void createFirstExpression(String text) {
        int num = 0;

        for (int i = 0; i < text.length(); i++)
            if (Character.isDigit(text.charAt(i))) num++;

        lines.add(new HBox(
                CodeGenerator.createPart("\twhile", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("((c=input[i++]) != "),
                CodeGenerator.createPart("EOS", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(")")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("c >= "),
                CodeGenerator.createPart("\'0\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(" && c <= "),
                CodeGenerator.createPart("\'9\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(") num++;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\telse", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" sym++;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tDEFINE(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("num"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(";")
        ));

        answers[0] = Integer.toString(num);
    }

    private void createSecondExpression(String text) {
        int
                type = random.nextInt(2);

        StringBuilder builder = new StringBuilder();

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i="),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart("; (c=input[i]) != "),
                CodeGenerator.createPart("EOS", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("; i++)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(c >= "),
                CodeGenerator.createPart(type == 0 ? "\'0\'" : "\'a\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(" && c <= "),
                CodeGenerator.createPart(type == 0 ? "\'9\'" : "\'z\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(") putchar(c);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tputchar("),
                CodeGenerator.createPart("\'\\n\'"),
                CodeGenerator.createPart(");")
        ));

        for (int i = 0; i < text.length(); i++) {
            if (type == 0 && Character.isDigit(text.charAt(i)))
                builder.append(text.charAt(i));
            if (type == 1 && !Character.isDigit(text.charAt(i)))
                builder.append(text.charAt(i));
        }

        answers[1] = builder.toString();
    }

    private void createFirstsLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" EOS ", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("\'\\0\'", CodeGenerator.CodeType.STRING)
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
}
