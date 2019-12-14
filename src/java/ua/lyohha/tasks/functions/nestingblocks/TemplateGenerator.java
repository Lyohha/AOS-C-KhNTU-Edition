package ua.lyohha.tasks.functions.nestingblocks;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.*;

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
        createFirstExpression();
        //createSecondExpression(text);
        createLastLine();
    }

    private void createFirstExpression() {
        List<Integer> pos = getPosition();
        int
                n1 = random.nextInt(10),
                n2 = random.nextInt(10),
                n3 = random.nextInt(10),
                n4 = random.nextInt(10);

        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        lines.add(new HBox(
                CodeGenerator.createPart("\tauto int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(";")
        ));

        int i = 0;

        if (pos.contains(1)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i++] = Integer.toString(n1);
        }
        lines.add(new HBox(
                CodeGenerator.createPart("\t{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i="),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart(";")
        ));

        if (pos.contains(2)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i++] = Integer.toString(n2);
        }
        lines.add(new HBox(
                CodeGenerator.createPart("\t\t{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\tint ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i="),
                CodeGenerator.createPart(n3),
                CodeGenerator.createPart(";")
        ));

        if (pos.contains(3)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\t\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i++] = Integer.toString(n3);
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\t\t\ti"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("="),
                CodeGenerator.createPart(n4),
                CodeGenerator.createPart(";")
        ));

        n3 = CodeGenerator.exeOperator(n3, n4, o1);

        if (pos.contains(4)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\t\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i++] = Integer.toString(n3);
        }


        lines.add(new HBox(
                CodeGenerator.createPart("\t\t}")
        ));

        if (pos.contains(5)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i++] = Integer.toString(n2);
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\t}")
        ));
        if (pos.contains(6)) {
            lines.add(new HBox(
                    CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart("i"),
                    CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                    CodeGenerator.createPart(";")
            ));
            answers[i] = Integer.toString(n1);
        }
    }

    private List<Integer> getPosition() {
        List<Integer> positions = new LinkedList<>( Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> pos = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            pos.add(positions.remove(random.nextInt(positions.size())));
        }

        Collections.sort(pos);

        return pos;
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
