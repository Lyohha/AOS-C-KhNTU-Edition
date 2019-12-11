package ua.lyohha.tasks.types.stringassignment;

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
        Variables variables = new Variables();

        createFirstsLine();
        createFirstExpression(variables);
        createSecondExpression(variables);
        createThirdExpression(variables);
        createFourthExpression(variables);
        createLastLine();
    }

    private void createFirstExpression(Variables variables) {
        variables.S = createString();

        lines.add(new HBox(
                CodeGenerator.createPart("\tstrcpy(S, "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(variables.S, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%s\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", S);")
        ));

        answers[0] = variables.S;
    }

    private void createSecondExpression(Variables variables) {
        variables.T = createString();

        lines.add(new HBox(
                CodeGenerator.createPart("\tstrcpy(T, "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(variables.T, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tstrcat(S, T);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%s\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", S);")
        ));

        variables.S = variables.S + variables.T;
        answers[1] = variables.S;
    }

    private void createThirdExpression(Variables variables) {
        CodeGenerator.CompareOperator
                c1 = CodeGenerator.CompareOperator.values()[random.nextInt(CodeGenerator.CompareOperator.values().length)];
        variables.L = createString();

        lines.add(new HBox(
                CodeGenerator.createPart("\tstrcpy(L, "),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(variables.L, CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart("\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tP=strcmp(S, L) "),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(" "),
                CodeGenerator.createPart(0),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", P);")
        ));

        answers[2] = Integer.toString(CodeGenerator.compare(stringCompare(variables.S, variables.L), 0, c1));
    }

    private void createFourthExpression(Variables variables) {
        int
                n1 = random.nextInt(variables.S.length());

        lines.add(new HBox(
                CodeGenerator.createPart("\tputchar(S["),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("]);")
        ));

        answers[3] = "" + variables.S.charAt(n1);
    }

    private int stringCompare(String str1, String str2) {

        if (str1.length() != str2.length()) {
            if (str1.length() > str2.length())
                return str1.charAt(str2.length());
            else
                return '\0' - str2.charAt(str1.length());
        }

        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return str1.charAt(i) - str2.charAt(i);
        }

        return 0;
    }

    private void createFirstsLine() {

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

        createVariables();

    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private void createVariables() {
        lines.add(new HBox(
                CodeGenerator.createPart("\tchar ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("S["),
                CodeGenerator.createPart(20),
                CodeGenerator.createPart("], T["),
                CodeGenerator.createPart(20),
                CodeGenerator.createPart("], L["),
                CodeGenerator.createPart(20),
                CodeGenerator.createPart("];")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("P;")
        ));
    }

    private String createString() {
        int
                size = random.nextInt(9) + 1;
        StringBuilder
                builder = new StringBuilder();

        for (int i = 0; i < size; i++)
            builder.append((char) ('a' + random.nextInt(26)));

        return builder.toString();
    }

    private class Variables {
        private String S;
        private String T;
        private String L;
    }
}
