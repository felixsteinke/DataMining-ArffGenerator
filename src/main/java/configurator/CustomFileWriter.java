package configurator;

import dataProviders.DataProvider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomFileWriter {

    private static final StringBuilder stringBuilder = new StringBuilder();
    public static int number = 0;

    private static final String a = "@attribute ";
    private static final String iT = "_in_Text ";
    private static final String iS = "_in_Subject ";
    private static final String r = "@relation spamGruppeF";
    private static final String data = "integer";
    private static final String d = "@data";
    private static final String file = "src/main/resources/GruppeF.arff";
    private static final String nl = "\n";

    public static void main(String[] args) {
        DataProvider dataProvider = new DataProvider();
        new CustomFileWriter().execute(
                dataProvider.getMails(),
                dataProvider.getBlackListedWords(),
                dataProvider.getWhiteListedWords(),
                dataProvider.getAverageSubjectLength(),
                dataProvider.getAverageTextLength());
    }


    public void execute(ArrayList<Mail> mails, ArrayList<String> blackList, ArrayList<String> whiteList, int averageSubjectLength, int averageTextLength) {
        stringBuilder.append(r);
        stringBuilder.append(nl);
        stringBuilder.append(nl);

        appendAttributeFromListAs(blackList, iT);
        appendAttributeFromListAs(blackList, iS);
        appendAttributeFromListAs(whiteList, iT);
        appendAttributeFromListAs(whiteList, iS);

        stringBuilder.append(a + "class {0,1}");

        stringBuilder.append(nl);
        stringBuilder.append(nl);

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
            stringBuilder.append(a);
            stringBuilder.append(number);
            number++;
            word = word.replaceAll("\\s", "_");
            word = word.replaceAll("%", "");
            stringBuilder.append(word);
            stringBuilder.append(context);
            stringBuilder.append(data);
            stringBuilder.append(nl);
        }
    }

    private void appendData(ArrayList<Mail> mails, ArrayList<String> blackList, ArrayList<String> whiteList, int averageSubjectLength, int averageTextLength) {

        stringBuilder.append(d);
        stringBuilder.append(nl);

        ExecutorService executor = Executors.newFixedThreadPool(11);

        for (Mail mail : mails) {
            System.out.println("neuer thred MailProcessor");
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

        System.out.println("Finished all threads");

        for (Mail mail : mails) {
            stringBuilder.append(mail.getConvertedBoolArrays());
        }
    }
}
