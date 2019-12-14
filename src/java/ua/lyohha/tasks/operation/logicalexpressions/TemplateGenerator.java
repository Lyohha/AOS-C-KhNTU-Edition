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
                CodeGenerator.createPart("\tint", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" x="),
                CodeGenerator.createPart(x),
                CodeGenerator.createPart(", y="),
                CodeGenerator.createPart(y),
                CodeGenerator.createPart(", z="),
                CodeGenerator.createPart(z),
                CodeGenerator.createPart(";")
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

        lines.add(new HBox(CodeGenerator.createPart(" ")));

        lines.add(new HBox(
                CodeGenerator.createPart("\t"),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1)),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2)),
                addIDO(ido3, "z"),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
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
                CodeGenerator.createPart("\tx="),
                CodeGenerator.createPart(variables.x),
                CodeGenerator.createPart(", y="),
                CodeGenerator.createPart(variables.y),
                CodeGenerator.createPart(", z="),
                CodeGenerator.createPart(variables.z),
                CodeGenerator.createPart(";")
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
                CodeGenerator.createPart("\t"),
                addIDO(ido1, "x"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l1)),
                addIDO(ido2, "y"),
                CodeGenerator.createPart(CodeGenerator.getOperator(l2)),
                addIDO(ido3, "z"),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("x"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("y"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";"),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("z"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));


        answers[1] = Integer.toString(variables.x);
        answers[2] = Integer.toString(variables.y);
        answers[3] = Integer.toString(variables.z);
    }

    private Label addIDO(CodeGenerator.IDOperator operator, String variable) {
        switch (operator) {
            case SUFIXDECREMENT:
                return CodeGenerator.createPart(variable + "--");
            case PREFIXDECREMENT:
                return CodeGenerator.createPart("--" + variable);
            case SUFIXINCREMENT:
                return CodeGenerator.createPart(variable + "++");
            case PREFIXINCREMENT:
                return CodeGenerator.createPart("++" + variable);
            default:
                return null;
        }
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
