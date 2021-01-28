package runner;

import generator.FileGenerator;
import generator.mail.MailGeneratorPreprocessor;

public class MailFileGenerator {

    public static void main(String[] args) {
        FileGenerator fileGenerator = new FileGenerator(new MailGeneratorPreprocessor());
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/mails/analytic_enron.arff");
    }
}
