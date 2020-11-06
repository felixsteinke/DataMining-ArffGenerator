package dataProviders;

import configurator.Mail;

import java.util.ArrayList;

public class DataProvider {

    private final ArrayList<Mail> mails;
    private final ArrayList<String> whiteListedWords;
    private final ArrayList<String> blackListedWords;

    private int averageSubjectLength;
    private int averageTextLength;

    public DataProvider() {
        this.mails = CsvUtility.readCsvFile("src/main/resources/enron.csv", ";");
        this.whiteListedWords = CsvUtility.readWordlist("src/main/resources/whitelist.csv");
        this.blackListedWords = CsvUtility.readWordlist("src/main/resources/blacklist.csv");

        calculateAverageValues();
    }

    private void calculateAverageValues() {
        for (Mail mail : mails) {
            averageSubjectLength += mail.getSubject().length();
            averageTextLength += mail.getText().length();
        }
        averageSubjectLength /= mails.size();
        averageTextLength /= mails.size();
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    public ArrayList<String> getWhiteListedWords() {
        return whiteListedWords;
    }

    public ArrayList<String> getBlackListedWords() {
        return blackListedWords;
    }

    public int getAverageSubjectLength() {
        return averageSubjectLength;
    }

    public int getAverageTextLength() {
        return averageTextLength;
    }

    public void writeMailAnalytic() {
        CsvUtility.writeCsvFile("src/main/resources/analytic.csv", mails);
    }
}
