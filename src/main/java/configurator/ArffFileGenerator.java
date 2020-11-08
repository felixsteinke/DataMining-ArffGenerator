package configurator;

import dataProviders.DataProvider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArffFileGenerator {

    private static final StringBuilder stringBuilder = new StringBuilder();
    public static int number = 0;

    private static final String file = "src/main/resources/analytic_enron.arff";
    private static final String titleAnnotation = "@relation spamGruppeF";
    private static final String attributeAnnotation = "@attribute ";
    private static final String inText = "_in_Text ";
    private static final String inSubject = "_in_Subject ";
    private static final String dataInteger = "integer";
    private static final String dataReal = "real";
    private static final String dataAnnotation = "@data";
    private static final String newLine = "\n";

    public static void main(String[] args) {
        Instant start = Instant.now();
        DataProvider dataProvider = new DataProvider();
        new ArffFileGenerator().execute(
                dataProvider.getMails(),
                dataProvider.getBlackListedWords(),
                dataProvider.getWhiteListedWords(),
                dataProvider.getAverageSubjectLength(),
                dataProvider.getAverageTextLength());
        Instant finish = Instant.now();
        long minutes = Duration.between(start, finish).toMinutes();
        long seconds = Duration.between(start,finish).toSecondsPart();
        long millis = Duration.between(start,finish).toMillisPart();
        System.out.println("Es sind seit Start: "+minutes+"min "+seconds+"s "+millis+"millis vergangen.");
    }


    public void execute(ArrayList<Mail> mails, ArrayList<String> blackList, ArrayList<String> whiteList, int averageSubjectLength, int averageTextLength) {
        stringBuilder.append(titleAnnotation);
        stringBuilder.append(newLine);
        stringBuilder.append(newLine);

        appendAttributeFromListAs(blackList, inText);
        appendAttributeFromListAs(blackList, inSubject);
        appendAttributeFromListAs(whiteList, inText);
        appendAttributeFromListAs(whiteList, inSubject);

        stringBuilder.append(attributeAnnotation).append("averageSentenceLength ").append(dataReal).append(newLine);
        stringBuilder.append(attributeAnnotation).append("maximumSentenceLength ").append(dataReal).append(newLine);
        stringBuilder.append(attributeAnnotation).append("biggerThanAverageSubjectLength ").append(dataInteger).append(newLine);
        stringBuilder.append(attributeAnnotation).append("biggerThanAverageTextLength ").append(dataInteger).append(newLine);

        stringBuilder.append(attributeAnnotation + "class {0,1}");
        stringBuilder.append(newLine);
        stringBuilder.append(newLine);

        appendData(mails, blackList, whiteList, averageSubjectLength, averageTextLength);

        String result = stringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendAttributeFromListAs(ArrayList<String> wordList, String context) {
        for (String word : wordList) {
            stringBuilder.append(attributeAnnotation);
            stringBuilder.append(number);
            number++;
            word = word.replaceAll("\\s", "_");
            word = word.replaceAll("%", "");
            stringBuilder.append(word);
            stringBuilder.append(context);
            stringBuilder.append(dataInteger);
            stringBuilder.append(newLine);
        }
    }

    private void appendData(ArrayList<Mail> mails, ArrayList<String> blackList, ArrayList<String> whiteList, int averageSubjectLength, int averageTextLength) {
        stringBuilder.append(dataAnnotation).append(newLine);

        ExecutorService executor = Executors.newFixedThreadPool(11);

        for (Mail mail : mails) {
            System.out.println("neuer Thread MailProcessor");
            Runnable mailProcessor = () -> {
                mail.processAnalytics(whiteList, blackList, averageSubjectLength, averageTextLength);
                System.out.println("Mail: " + mail.id + " wurde fertig Bearbeitet.");
            };
            executor.execute(mailProcessor);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
        }

        System.out.println("Finished all threads.");

        for (Mail mail : mails) {
            stringBuilder.append(mail.getConvertedBoolArrays());
        }
    }
}
