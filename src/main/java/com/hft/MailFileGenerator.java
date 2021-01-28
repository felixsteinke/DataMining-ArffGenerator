package com.hft;

import com.hft.generator.FileGenerator;
import com.hft.generator.mail.MailGeneratorPreprocessor;

public class MailFileGenerator {

    public static void main(String[] args) {
        FileGenerator fileGenerator = new FileGenerator(new MailGeneratorPreprocessor());
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/mails/analytic_enron.arff");
    }
}
