package ua.lyohha.tasks.types.stringconvert;

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
        Variables variables = createFirstsLine();

        createExpression(variables);

        createLastLine();
    }

    private void createExpression(Variables variables) {
        lines.add(new HBox(
                CodeGenerator.createPart("\titoa(num, S, "),
                CodeGenerator.createPart(10),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tS = strrev(S);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tnum = atoi(S);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tprintf("),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", num);")
        ));

        answers[0] = getStringInverted(variables.numbers);
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
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" <stdlib.h>", CodeGenerator.CodeType.LIBRARY)
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
                CodeGenerator.createPart("*S;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("num;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tS = ("),
                CodeGenerator.createPart("char", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("*)malloc("),
                CodeGenerator.createPart("sizeof", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("("),
                CodeGenerator.createPart("char"),
                CodeGenerator.createPart(")*"),
                CodeGenerator.createPart(10),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tnum = "),
                CodeGenerator.createPart(variables.number),
                CodeGenerator.createPart(";")
        ));

        return variables;
    }

    private String getStringInverted(List<Integer> numbers) {
        int num = 0;
        for (int i = 0, temp = 1; i < numbers.size(); i++, temp *= 10)
            num += numbers.get(i) * temp;

        return Integer.toString(num);
    }

    private int getInteger(List<Integer> numbers) {
        int num = 0;
        for (int i = numbers.size() - 1, temp = 1; i >= 0; i--, temp *= 10)
            num += numbers.get(i) * temp;
        return num;
    }

    private class Variables {
        private List<Integer> numbers;
        private int number;

        private Variables() {
            int
                    n1 = random.nextInt(2) + 4;
            numbers = new ArrayList<>();
            for (int i = 0; i < n1; i++)
                numbers.add(i == 0 ? random.nextInt(9) + 1 : random.nextInt(10));
            number = getInteger(numbers);
        }
    }
}
