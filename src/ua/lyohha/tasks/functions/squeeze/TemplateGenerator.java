package ua.lyohha.tasks.functions.squeeze;

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
        createMain();
        createSqueezeFunction();
    }

    private void createMain() {
        String
                string = createText();
        char[]
                symbols = createRemovedSymbols();

        lines.add(new HBox(
                CodeGenerator.createPart("void ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("main()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("*s;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("c["),
                CodeGenerator.createPart(4),
                CodeGenerator.createPart("];")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\ts="),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(string, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t"),
                CodeGenerator.createPart("c["),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart("]="),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+symbols[0], CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" c["),
                CodeGenerator.createPart(1),
                CodeGenerator.createPart("]="),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+symbols[1], CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" c["),
                CodeGenerator.createPart(2),
                CodeGenerator.createPart("]="),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+symbols[2], CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" c["),
                CodeGenerator.createPart(3),
                CodeGenerator.createPart("]="),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(""+symbols[3], CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i="),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart("; i<"),
                CodeGenerator.createPart(4),
                CodeGenerator.createPart("; i++)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tsqueeze(s,c[i]);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tprintf("),
                CodeGenerator.createPart("\"%s\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(",s);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        for (int i = 0; i < 4; i++)
            answers[i] = (string = removeSymbol(string, symbols[i]));

    }

    private void createSqueezeFunction() {
        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("squeeze("),
                CodeGenerator.createPart("char ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("*s, "),
                CodeGenerator.createPart("char ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("c)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i,j;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i=j="),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart("; s[i]!="),
                CodeGenerator.createPart("\'\\0\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("; i++)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tif", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(s[i]!=c)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\ts[j++]=s[i];")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\ts[j]="),
                CodeGenerator.createPart("\'\\0\'", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private String createText() {
        StringBuilder
                stringBuilder = new StringBuilder();
        int
                count = random.nextInt(5) + 3;
        for (int i = 0; i < count; i++) {
            stringBuilder.append((char) ('a' + random.nextInt(26)));
        }

        return stringBuilder.toString();
    }

    private String removeSymbol(String str, char symbol) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != symbol)
                builder.append(str.charAt(i));
        }

        return builder.toString();
    }

    private char[] createRemovedSymbols() {
        char[] symbols = new char[4];

        for (int i = 0; i < 4; i++)
            symbols[i] = (char) ('a' + random.nextInt(26));

        return symbols;
    }



    private void createFirstsLine() {

        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

    }
}
