package com.hft.dataProviders.mail;

import com.hft.dataProviders.CsvFileReader;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class MailDataProvider {

    private final ArrayList<Mail> mails;
    private final ArrayList<String> whiteListedWords;
    private final ArrayList<String> blackListedWords;

    private int averageSubjectLength;
    private int averageTextLength;

    public MailDataProvider() {
        CsvFileReader reader = new CsvFileReader();
        this.mails = new ArrayList<>();
        reader.readFile("src/main/resources/mails/enron.csv").forEach(mail -> mails.add(new Mail(mail)));
        this.whiteListedWords = reader.readWordlist("src/main/resources/mails/whitelist.csv");
        this.blackListedWords = reader.readWordlist("src/main/resources/mails/blacklist.csv");

        calculateAverageValues();
    }

    private void calculateAverageValues() {
        averageSubjectLength = 0;
        averageTextLength = 0;

        for (Mail mail : mails) {
            averageSubjectLength += mail.getSubject().length();
            averageTextLength += mail.getText().length();
        }
        averageSubjectLength /= mails.size();
        averageTextLength /= mails.size();
    }
}
