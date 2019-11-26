package ua.lyohha.tasks.operation.assignmentoperation;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ua.lyohha.tasks.CodeGenerator;

import java.util.ArrayList;
import java.util.List;

public class TemplateGenerator {

    private List<HBox> lines = new ArrayList<>();
    private String[] answers = new String[] {"an1", "an2", "an3", "an4"};


    public TemplateGenerator() {
        createTemplate();
    }

    public void setTemplate(Pane pane) {
        for (int i = 0; i < lines.size(); i++) pane.getChildren().add(lines.get(i));
    }

    private void createTemplate() {
        createFirstsLine();
    }

    public String[] getAnswers()
    {
        return answers;
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
                CodeGenerator.createPart("PRINTX", CodeGenerator.CodeType.DEFINE1),
                CodeGenerator.createPart(" ", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("printf(", CodeGenerator.CodeType.TEXT),
                CodeGenerator.createPart("\"%d\\n\"", CodeGenerator.CodeType.STRING),
                CodeGenerator.createPart(", x)", CodeGenerator.CodeType.TEXT)
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

}
