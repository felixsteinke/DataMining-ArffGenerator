package generator;

import java.util.ArrayList;

public class Generator {

    private final StringBuilder stringBuilder;

    private final String file;
    private final String title;
    private final ArrayList<String> attributeList;
    private final String newLine = "\n";

    public Generator(String filePath, String title, ArrayList<String> attributeList) {
        this.stringBuilder = new StringBuilder();
        this.file = filePath;
        this.title = title;
        this.attributeList = attributeList;
    }

    public void execute() {
        appendHeader(title);
        appendAttributes(attributeList);
        appendTargetVariable();

    }

    private void appendHeader(String title) {
        String titleAnnotation = "@relation " + title;
        stringBuilder.append(titleAnnotation).append(newLine).append(newLine);
    }

    private void appendAttributes(ArrayList<String> attributeList) {
        String attributeAnnotation = "@attribute ";
        int attributeNumber = 0;
        for (String attribute : attributeList) {
            stringBuilder.append(attributeAnnotation).append(attributeNumber).append(attribute);
            attributeNumber++;
        }
    }

    private void appendTargetVariable() {
        String attributeAnnotation = "@attribute ";
        stringBuilder.append(attributeAnnotation).append("class {0,1}").append(newLine).append(newLine);
    }

    private void appendData() {
        String dataAnnotation = "@data";
        stringBuilder.append(dataAnnotation).append(newLine);


    }

}
