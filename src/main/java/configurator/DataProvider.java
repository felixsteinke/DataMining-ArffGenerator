package configurator;

import java.util.ArrayList;

public class DataProvider {

    private final ArrayList <Mail> sourceData;
    private final ArrayList<String> whiteListedWords;
    private final ArrayList<String> blackListedWords;

    public DataProvider() {
        this.sourceData = CsvReader.readCsvFile("src/main/resources/enron.csv",";");
        this.whiteListedWords = CsvReader.readWordlist("src/main/resources/whitelist.csv");
        this.blackListedWords = CsvReader.readWordlist("src/main/resources/blacklist.csv");
    }

    public ArrayList<Mail> getSourceData() {
        return sourceData;
    }

    public ArrayList<String> getWhiteListedWords() {
        return whiteListedWords;
    }

    public ArrayList<String> getBlackListedWords() {
        return blackListedWords;
    }
}
