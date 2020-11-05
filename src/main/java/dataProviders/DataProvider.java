package dataProviders;

import configurator.Mail;

import java.util.ArrayList;

public class DataProvider {

    private final ArrayList <Mail> sourceData;
    private final ArrayList<String> whiteListedWords;
    private final ArrayList<String> blackListedWords;

    public DataProvider() {
        this.sourceData = CsvUtility.readCsvFile("src/main/resources/enron.csv",";");
        this.whiteListedWords = CsvUtility.readWordlist("src/main/resources/whitelist.csv");
        this.blackListedWords = CsvUtility.readWordlist("src/main/resources/blacklist.csv");
    }

    public ArrayList<Mail> getMailSource() {
        return sourceData;
    }

    public ArrayList<String> getWhiteListedWords() {
        return whiteListedWords;
    }

    public ArrayList<String> getBlackListedWords() {
        return blackListedWords;
    }

    public void writeMailAnalytic() {
        CsvUtility.writeCsvFile("src/main/resources/analytic.csv", sourceData);
    }
}
