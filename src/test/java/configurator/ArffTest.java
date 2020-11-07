package configurator;

import dataProviders.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArffTest {

    static DataProvider dataProvider;
    static Mail testMailWhite;
    static Mail testMailBlack;
    static ArrayList<Mail> testMails;

    @BeforeAll
    static void setUpData() {
        dataProvider = new DataProvider();
    }

    @BeforeEach
    void prepareMail() {
        testMailWhite = new Mail(
                123,
                "Subject to for meeting",
                "Text with some uselessMeeting lines.Text with some uselessMeeting lines.",
                false
        );
        testMailBlack = new Mail(
                1,
                "Sexmachine",
                "I want to check this text. Long sentence without any symbol in between but with sexy things",
                true
        );
        testMails = new ArrayList<>();
        testMails.add(testMailWhite);
        testMails.add(testMailBlack);
    }

    @Test
    void processAnalyticWhiteList() {
        new CustomFileWriter().execute(
                testMails,
                dataProvider.getBlackListedWords(),
                dataProvider.getWhiteListedWords(),
                dataProvider.getAverageSubjectLength(),
                dataProvider.getAverageTextLength());
    }

}
