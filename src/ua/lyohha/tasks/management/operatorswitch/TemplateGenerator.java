package ua.lyohha.tasks.management.operatorswitch;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateGenerator {
    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[1];
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
        createLastLine();
    }

    private String createVariables() {
        char[] t = new char[]{
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                (char) (random.nextInt(7) > 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10))
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
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("c;")
        ));

        return text;
    }

    private void createFirstExpression(String text) {

        char
                c1 = text.charAt(random.nextInt(text.length())),
                c2 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                c3 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                c4 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10)),
                c5 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10));

        while(c2 == c3) c3 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10));

        while (c5 == c2 || c5 == c4) c5 = (char) (random.nextInt(2) == 1 ? 'a' + random.nextInt(26) : '0' + random.nextInt(10));


        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i=0; (c=input[i]) != "),
                CodeGenerator.createPart("\'\\0\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("; i++)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tswitch", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(c)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\tcase ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+c1, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(": putchar("),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+c2, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" continue", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\tcase ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+c3, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(": putchar("),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+c4, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" continue", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\tcase ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+c5, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(": "),
                CodeGenerator.createPart(" continue", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\tdefault", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(": putchar(c); "),
                CodeGenerator.createPart(" continue", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(";")
        ));


        lines.add(new HBox(
                CodeGenerator.createPart("\t\t}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tputchar("),
                CodeGenerator.createPart("\'\\n\'"),
                CodeGenerator.createPart(");")
        ));


        answers[0] = text.replace(c1, c2).replace(c3, c4).replace("" + c5, "");

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
}
