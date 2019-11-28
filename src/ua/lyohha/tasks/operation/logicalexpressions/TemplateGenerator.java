package ua.lyohha.tasks.operation.logicalexpressions;

import javafx.scene.control.Label;
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

    private void createFirstExpression(Variables variables) {

        CodeGenerator.LogicalOperator
                l1 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)],
                l2 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)];

        CodeGenerator.IDOperator
                ido1 = CodeGenerator.IDOperator.values()[random.nextInt(2)],
                ido2 = CodeGenerator.IDOperator.values()[random.nextInt(2)],
                ido3 = CodeGenerator.IDOperator.values()[random.nextInt(2)];

        variables.x = CodeGenerator.exeOperator(variables.x, ido1);

        if (l1 == CodeGenerator.LogicalOperator.AND) {
            if (variables.x == 0) {
                if (l2 == CodeGenerator.LogicalOperator.OR)
                    variables.z = CodeGenerator.exeOperator(variables.z, ido3);
            } else {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                if (variables.y == 0) {
                    if (l2 == CodeGenerator.LogicalOperator.OR)
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                } else {
                    if (l2 == CodeGenerator.LogicalOperator.AND)
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                }
            }
        } else {
            if (variables.x == 0) {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                if (variables.y == 0) {
                    if (l2 == CodeGenerator.LogicalOperator.OR) {
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                    }
                } else {
                    if (l2 == CodeGenerator.LogicalOperator.AND) {
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                    }
                }
            }
        }

        lines.add(new HBox(CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT)));

        lines.add(new HBox(
                CodeGenerator.createPart("\t", CodeGenerator.CodeType.TEXT),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1), CodeGenerator.CodeType.TEXT),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2), CodeGenerator.CodeType.TEXT),
                addIDO(ido3, "z"),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        answers[0] = Integer.toString(variables.y);

    }

    private void createSecondExpression(Variables variables) {

        CodeGenerator.LogicalOperator
                l1 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)],
                l2 = CodeGenerator.LogicalOperator.values()[random.nextInt(CodeGenerator.LogicalOperator.values().length)];

        CodeGenerator.IDOperator
                ido1 = CodeGenerator.IDOperator.values()[random.nextInt(2)],
                ido2 = CodeGenerator.IDOperator.values()[random.nextInt(2)],
                ido3 = CodeGenerator.IDOperator.values()[random.nextInt(2)];

        variables.x = random.nextInt(10);
        variables.y = random.nextInt(10);
        variables.z = random.nextInt(10);
        lines.add(new HBox(
                CodeGenerator.createPart("\t", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("x=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(variables.x), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(", y=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(variables.y), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(", z=", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(Integer.toString(variables.z), CodeGenerator.CodeType.NUMBER),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        variables.x = CodeGenerator.exeOperator(variables.x, ido1);

        if (l1 == CodeGenerator.LogicalOperator.AND) {
            if (variables.x == 0) {
                if (l2 == CodeGenerator.LogicalOperator.OR)
                    variables.z = CodeGenerator.exeOperator(variables.z, ido3);
            } else {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                if (variables.y == 0) {
                    if (l2 == CodeGenerator.LogicalOperator.OR)
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                } else {
                    if (l2 == CodeGenerator.LogicalOperator.AND)
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                }
            }
        } else {
            if (variables.x == 0) {
                variables.y = CodeGenerator.exeOperator(variables.y, ido2);
                if (variables.y == 0) {
                    if (l2 == CodeGenerator.LogicalOperator.OR) {
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                    }
                } else {
                    if (l2 == CodeGenerator.LogicalOperator.AND) {
                        variables.z = CodeGenerator.exeOperator(variables.z, ido3);
                    }
                }
            }
        }

        lines.add(new HBox(
                CodeGenerator.createPart("\t", CodeGenerator.CodeType.TEXT),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1), CodeGenerator.CodeType.TEXT),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2), CodeGenerator.CodeType.TEXT),
                addIDO(ido3, "z"),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";", CodeGenerator.CodeType.TEXT)
        ));


        answers[1] = Integer.toString(variables.x);
        answers[2] = Integer.toString(variables.y);
        answers[3] = Integer.toString(variables.z);
    }

    private Label addIDO(CodeGenerator.IDOperator operator, String variable) {
        switch (operator) {
            case SUFIXDECREMENT:
                return CodeGenerator.createPart(variable + "--", CodeGenerator.CodeType.TEXT);
            case PREFIXDECREMENT:
                return CodeGenerator.createPart("--" + variable, CodeGenerator.CodeType.TEXT);
            case SUFIXINCREMENT:
                return CodeGenerator.createPart(variable + "++", CodeGenerator.CodeType.TEXT);
            case PREFIXINCREMENT:
                return CodeGenerator.createPart("++" + variable, CodeGenerator.CodeType.TEXT);
            default:
                return null;
        }
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
