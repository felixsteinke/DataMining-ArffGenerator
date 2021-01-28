package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileGenerator {

    private final StringBuilder stringBuilder;
    private final String newLine = "\n";

    private final GeneratorPreprocessor preprocessor;

    public FileGenerator(GeneratorPreprocessor preprocessor) {
        this.stringBuilder = new StringBuilder();
        this.preprocessor = preprocessor;
    }

    public String execute() {
        appendHeader(preprocessor.getTitle());
        appendAttributes(preprocessor.getAttributeList());
        appendData(preprocessor.getDataList());
        return stringBuilder.toString();
    }

    public void writeFile(String filePath) {
        String result = stringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(result);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendHeader(String title) {
        String titleAnnotation = "@relation " + title;
        stringBuilder.append(titleAnnotation).append(newLine).append(newLine);
    }

    private void appendAttributes(ArrayList<String> attributeList) {
        String attributeAnnotation = "@attribute ";
        int attributeNumber = 0;
        for (String attribute : attributeList) {
            stringBuilder.append(attributeAnnotation).append(attributeNumber).append(attribute).append(newLine);
            attributeNumber++;
        }
        stringBuilder.append(newLine);
    }

    private void appendData(ArrayList<String> dataList) {
        String dataAnnotation = "@data";
        stringBuilder.append(dataAnnotation).append(newLine);
        dataList.forEach(dataLine -> stringBuilder.append(dataLine).append(newLine));
    }
}
