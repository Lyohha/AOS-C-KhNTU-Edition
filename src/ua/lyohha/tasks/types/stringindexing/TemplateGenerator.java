package ua.lyohha.tasks.types.stringindexing;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateGenerator {
    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[3];
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
        Variables variables = createFirstsLine();

        createFirstExpression(variables);
        createSecondExpression(variables);
        createLastLine();
    }

    private void createFirstExpression(Variables variables) {

        lines.add(new HBox(
                CodeGenerator.createPart("\tlen = strlen(S);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tpos = strstr(SS, S);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tindex = pos == "),
                CodeGenerator.createPart("NULL", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("?-"),
                CodeGenerator.createPart(1),
                CodeGenerator.createPart(":pos;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", len);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", index);")
        ));

        answers[0] = Integer.toString(variables.S.length());
        answers[1] = variables.type == 0 ? Integer.toString(variables.index) : "-1";
    }

    private void createSecondExpression(Variables variables) {

        int
                n1 = random.nextInt(variables.S.length()),
                n2 = random.nextInt(variables.SS.length());

        lines.add(new HBox(
                CodeGenerator.createPart("\tS["),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("] = SS["),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("];")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%s\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", S);")
        ));

        changeChar(variables, n1, n2);

        answers[2] = variables.S;
    }

    private void changeChar(Variables variables, int n1, int n2) {
        StringBuilder builder = new StringBuilder();
        if (n1 != 0)
            builder.append(variables.S.substring(0, n1));
        builder.append(variables.SS.charAt(n2));
        if (n1 != variables.S.length() - 1)
            builder.append(variables.S.substring(n1 + 1));

        variables.S = builder.toString();
    }

    private Variables createFirstsLine() {

        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdio.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <string.h>", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("main()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        return createVariables();

    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private Variables createVariables() {
        Variables variables = new Variables();
        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("*S, *SS;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("index, len, pos;")
        ));

        variables.S = createString();
        variables.type = random.nextInt(2);
        if (variables.type == 0) {
            variables.index = variables.S.length() - (random.nextInt(variables.S.length() - 2) + 1);
            variables.SS = variables.S.substring(variables.index);
        } else {
            variables.SS = createString(random.nextInt(variables.S.length() - 2) + 1);
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\tS = "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(variables.S, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tSS = "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(variables.SS, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(";")
        ));

        return variables;
    }

    private String createString() {
        return createString(random.nextInt(8) + 2);
    }

    private String createString(int length) {
        StringBuilder
                builder = new StringBuilder();
        char c;
        List<Character> symbols = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            do {
                c = (char) ('a' + random.nextInt(26));
            } while (symbols.contains(c));
            symbols.add(c);
        }

        for (int i = 0; i < length; i++)
            builder.append(symbols.get(i));

        return builder.toString();
    }

    private class Variables {
        private String SS;
        private String S;
        private int type;
        private int index;
    }
}
