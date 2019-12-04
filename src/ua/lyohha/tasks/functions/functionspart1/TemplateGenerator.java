package ua.lyohha.tasks.functions.functionspart1;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.*;

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
        List<Integer> types = getTypes();
        for (int i = 0; i < types.size(); i++)
            createExpression(variables, types.get(i), i);
        //createSecondExpression(text);
        createLastLine();
        createReset();
        if (types.contains(5))
            createWorkOver();
    }

    private void createReset() {

        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("reset(i)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\ti=i<="),
                CodeGenerator.createPart("CHANGE", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("?"),
                CodeGenerator.createPart("HIGH", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(":"),
                CodeGenerator.createPart("LOW", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));

    }

    private void createWorkOver() {
        int
                n1 = random.nextInt(9) + 1,
                n2 = random.nextInt(9) + 1;
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length - 1)],
                o2 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length - 1)],
                o3 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length - 1)],
                o4 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        lines.add(new HBox(
                CodeGenerator.createPart("")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("void ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("workover(i)")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("{")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\ti=(i%i)*((i"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart("i)"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o2)),
                CodeGenerator.createPart("("),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(CodeGenerator.getOperator(o3)),
                CodeGenerator.createPart("i)"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o4)),
                CodeGenerator.createPart(n2),
                CodeGenerator.createPart(");")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("\treturn ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart("i;")
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private void createExpression(Variables variables, int type, int answer) {
        switch (type) {
            case 1:
                //PRINT(i);
                createFirstExpression(variables, answer);
                break;
            case 2:
                //reset(i+4); PRINT(i);
                createSecondExpression(variables, answer);
                break;
            case 3:
                //reset(i=i+0); PRINT(i);
                createThirdExpression(variables, answer);
                break;
            case 4:
                //i=reset(i+0); PRINT(d,i);
                createFourthExpression(variables, answer);
                break;
            case 5:
                //workover(i);
                createFifthExpression(answer);
                break;
        }
    }

    private void createFirstExpression(Variables variables, int answer) {

        lines.add(new HBox(
                CodeGenerator.createPart("\tPRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[answer] = Integer.toString(variables.i);
    }

    private void createSecondExpression(Variables variables, int answer) {
        int
                n1 = random.nextInt(10);
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        lines.add(new HBox(
                CodeGenerator.createPart("\treset(i"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(");"),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[answer] = Integer.toString(variables.i);
    }

    private void createThirdExpression(Variables variables, int answer) {
        int
                n1 = random.nextInt(9) + 1;
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        lines.add(new HBox(
                CodeGenerator.createPart("\treset(i=i"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(");"),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        variables.i = CodeGenerator.exeOperator(variables.i, n1, o1);

        answers[answer] = Integer.toString(variables.i);
    }

    private void createFourthExpression(Variables variables, int answer) {
        int
                n1 = random.nextInt(9) + 1;
        CodeGenerator.Operator
                o1 = CodeGenerator.Operator.values()[random.nextInt(CodeGenerator.Operator.values().length)];

        variables.i = CodeGenerator.exeOperator(variables.i, n1, o1) <= variables.CHANGE ? variables.HIGH : variables.LOW;

        lines.add(new HBox(
                CodeGenerator.createPart("\ti=reset(i=i"),
                CodeGenerator.createPart(CodeGenerator.getOperator(o1)),
                CodeGenerator.createPart(n1),
                CodeGenerator.createPart(");"),
                CodeGenerator.createPart(" PRINT(", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart("i"),
                CodeGenerator.createPart(")", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(";")
        ));

        answers[answer] = Integer.toString(variables.i);
    }

    private void createFifthExpression(int answer) {

        lines.add(new HBox(
                CodeGenerator.createPart("\tworkover(i);")
        ));

        answers[answer] = "0";
    }

    private List<Integer> getTypes() {
        List<Integer> positions = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> pos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            pos.add(positions.remove(random.nextInt(positions.size())));
        }

        Collections.sort(pos);

        return pos;
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
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" LOW ", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(variables.LOW)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" HIGH ", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(variables.HIGH)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("#define", CodeGenerator.CodeType.DIRECTIVE),
                CodeGenerator.createPart(" CHANGE ", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(variables.CHANGE)
        ));

        lines.add(new HBox(
                CodeGenerator.createPart("int ", CodeGenerator.CodeType.OPERATOR),
                CodeGenerator.createPart(" i="),
                CodeGenerator.createPart("LOW", CodeGenerator.CodeType.DEFINE1),
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
                CodeGenerator.createPart(" i=HIGH;")
        ));

        return variables;
    }

    private void createLastLine() {
        lines.add(new HBox(
                CodeGenerator.createPart("}")
        ));
    }

    private Variables createVariables() {

        int
                low = random.nextInt(5),
                high = random.nextInt(5) + 6;
        int
                change = random.nextInt(high - low - 1) + low + 1;
        return new Variables(low, high, change);
    }

    private class Variables {
        public int LOW;
        public int HIGH;
        public int CHANGE;
        public int i;

        public Variables(int LOW, int HIGH, int CHANGE) {
            this.LOW = LOW;
            this.HIGH = HIGH;
            this.CHANGE = CHANGE;
            this.i = HIGH;
        }
    }

    private class Workover {

    }
}
