package ua.lyohha.tasks.functions.functionspart2;

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
        Variables variables = createFirstsLine();
        Type type = Type.values()[random.nextInt(Type.values().length)];
        createExpression(variables, type);
        createReset();
    }

    private void createExpression(Variables variables, Type type) {
        switch (type) {
            case NEW:
                createFirstExpression(variables);
                break;
            case LAST:
                createSecondExpression(variables);
                break;
            case NEXT:
                createThirdExpression(variables);
                break;
        }
    }

    private void createFirstExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2;
        n2 = n1 < 5 ? n1 + 4 : n1 - 4;
        CodeGenerator.IDOperator
                ido1 = n1 < 5 ? CodeGenerator.IDOperator.SUFIXINCREMENT : CodeGenerator.IDOperator.SUFIXDECREMENT;
        CodeGenerator.CompareOperator
                c1 = n1 < 5 ? CodeGenerator.CompareOperator.LESS : CodeGenerator.CompareOperator.MORE;

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(j="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; j"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart(ido1, "j"),
                CodeGenerator.createPart(")")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("new(i+j)"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("new(i)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tauto int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j="),
                CodeGenerator.createPart(variables.j),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn "),
                CodeGenerator.createPart("(i=j"),
                CodeGenerator.createPart(CodeGenerator.getOperator(variables.o1)),
                CodeGenerator.createPart("=i);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        for (int i = 0; CodeGenerator.compare(n1, n2, c1) == 1; n1 = CodeGenerator.exeOperator(n1, ido1), i++)
            answers[i] = Integer.toString(CodeGenerator.exeOperator(variables.j, variables.globalI + n1, variables.o1));

    }

    private void createSecondExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2;
        n2 = n1 < 5 ? n1 + 4 : n1 - 4;
        CodeGenerator.IDOperator
                ido1 = n1 < 5 ? CodeGenerator.IDOperator.SUFIXINCREMENT : CodeGenerator.IDOperator.SUFIXDECREMENT;
        CodeGenerator.CompareOperator
                c1 = n1 < 5 ? CodeGenerator.CompareOperator.LESS : CodeGenerator.CompareOperator.MORE;

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; j"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart(ido1, "j"),
                CodeGenerator.createPart(")")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("last(i)"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("last(j)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tstatic int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i="),
                CodeGenerator.createPart(variables.i),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn "),
                CodeGenerator.createPart("j="),
                CodeGenerator.createPart(variables.ido2, "i"),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        for (int i = 0; i < 4; i++) {
            answers[i] = Integer.toString(variables.i);
            variables.i = CodeGenerator.exeOperator(variables.i, variables.ido2);
        }
    }

    private void createThirdExpression(Variables variables) {
        int
                n1 = random.nextInt(10),
                n2;
        n2 = n1 < 5 ? n1 + 4 : n1 - 4;
        CodeGenerator.IDOperator
                ido1 = n1 < 5 ? CodeGenerator.IDOperator.SUFIXINCREMENT : CodeGenerator.IDOperator.SUFIXDECREMENT;
        CodeGenerator.CompareOperator
                c1 = n1 < 5 ? CodeGenerator.CompareOperator.LESS : CodeGenerator.CompareOperator.MORE;

        lines.add(new HBox(
                CodeGenerator.createPart("\tfor", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j="),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart("; j"),
                CodeGenerator.createPart(CodeGenerator.getOperator(c1)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart("; "),
                CodeGenerator.createPart(ido1, "j"),
                CodeGenerator.createPart(")")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\t\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("next(i)"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("next(j)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn "),
                CodeGenerator.createPart("(j="),
                CodeGenerator.createPart(variables.ido1, "i"),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        for (int i = 0; i < 4; i++) {
            variables.globalI = CodeGenerator.exeOperator(variables.globalI, variables.ido1);
            answers[i] = Integer.toString(variables.globalI);

        }
    }

    private void createReset() {
        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("reset()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private Variables createFirstsLine() {

        Variables variables = createVariables();

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
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" i="),
                CodeGenerator.createPart(variables.globalI),
                CodeGenerator.createPart(";")
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

        lines.add(new HBox(
                CodeGenerator.createPart("\tauto int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" i, j;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\ti = reset();")
        ));

        return variables;
    }

    private Variables createVariables() {

        return new Variables(
                Type.values()[random.nextInt(Type.values().length)],
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                CodeGenerator.IDOperator.values()[random.nextInt(2)],
                CodeGenerator.IDOperator.values()[random.nextInt(2) + 2],
                CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)]
        );
    }

    private class Variables {
        public Type type;
        public int i;
        public int globalI;
        public int j;
        public CodeGenerator.IDOperator ido1;//for next
        public CodeGenerator.IDOperator ido2;//for last
        public CodeGenerator.Operator o1;

        public Variables(
                Type type, int i, int j, int globalI,
                CodeGenerator.IDOperator ido1,
                CodeGenerator.IDOperator ido2,
                CodeGenerator.Operator o1) {
            this.globalI = globalI;
            this.i = i;
            this.j = j;
            this.type = type;
            this.ido1 = ido1;
            this.ido2 = ido2;
            this.o1 = o1;
        }
    }

    private enum Type {
        NEW,
        LAST,
        NEXT
    }
}
