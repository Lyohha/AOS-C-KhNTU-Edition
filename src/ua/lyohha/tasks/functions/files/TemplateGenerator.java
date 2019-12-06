package ua.lyohha.tasks.functions.files;

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
        createMain(variables);
        createFile1(variables);
        createFile2();
        setAnswers(variables);
    }

    private void setAnswers(Variables variables)
    {
        answers[0] = Integer.toString(variables.globalI);
        answers[1] = Integer.toString((variables.staticI += variables.n1));
        answers[2] = Integer.toString((variables.staticI -= variables.n2));
        answers[3] = Integer.toString(CodeGenerator.exeOperator(variables.staticJ, variables.globalI + variables.j, variables.o1));
    }

    private void createMain(Variables variables) {
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
                CodeGenerator.createPart("\ti=reset();")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tj="),
                CodeGenerator.createPart(variables.j),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("next()"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("last()"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("last(i+j)"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

    }

    private void createFile1(Variables variables) {
        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("//file1.h")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("static int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" i="),
                CodeGenerator.createPart(variables.staticI),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("next()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i+="),
                CodeGenerator.createPart(variables.n1),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("last()")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i-="),
                CodeGenerator.createPart(variables.n2),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart(" ")
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
                CodeGenerator.createPart("\tstatic int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("j="),
                CodeGenerator.createPart(variables.staticJ),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("(i=j"),
                CodeGenerator.createPart(CodeGenerator.getOperator(variables.o1)),
                CodeGenerator.createPart("=i);")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private void createFile2() {
        lines.add(new HBox(
                CodeGenerator.createPart(" ")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("//file2.h")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("extern int", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" i;")
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
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" \"file1.h\"", CodeGenerator.CodeType.LIBRARY)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#include", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" \"file2.h\"", CodeGenerator.CodeType.LIBRARY)
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

        return variables;
    }

    private Variables createVariables() {
        return new Variables(
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                random.nextInt(9) + 1,
                CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)],
                random.nextInt(9) + 1
        );
    }

    private class Variables {
        private int globalI;
        private int staticI;
        private int staticJ;
        private int n1;
        private int n2;
        private CodeGenerator.Operator o1;
        private int j;

        private Variables(
                int globalI,
                int staticI,
                int staticJ,
                int n1,
                int n2,
                CodeGenerator.Operator o1,
                int j
        ) {
            this.globalI = globalI;
            this.j = j;
            this.n1 = n1;
            this.n2 = n2;
            this.o1 = o1;
            this.staticI = staticI;
            this.staticJ = staticJ;
        }
    }
}
