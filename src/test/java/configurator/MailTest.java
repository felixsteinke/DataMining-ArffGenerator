package configurator;

import dataProviders.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MailTest {

    static DataProvider dataProvider;
    Mail testMailWhite;
    Mail testMailBlack;

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
    }

    @Test
    void processAnalyticWhiteList() {
        ArrayList<String> whiteList = dataProvider.getWhiteListedWords();
        int listIndex = getWordIndex(whiteList, "meeting");

        testMailWhite.processAnalyticWhiteList(whiteList);

        Assertions.assertTrue(testMailWhite.getWhiteWordInSub().get(listIndex));
        Assertions.assertTrue(testMailWhite.isWithWhiteListWordInSubject());
        Assertions.assertTrue(testMailWhite.getWhiteWordInText().get(listIndex));
        Assertions.assertTrue(testMailWhite.isWithWhiteListWordInText());
    }

    @Test
    void processAnalyticBlackList() {
        ArrayList<String> blackList = dataProvider.getBlackListedWords();
        int listIndex = getWordIndex(blackList, "sex");

        testMailBlack.processAnalyticBlackList(blackList);

        Assertions.assertTrue(testMailBlack.getBlackWordInSub().get(listIndex));
        Assertions.assertTrue(testMailBlack.isWithBlackListWordInSubject());
        Assertions.assertTrue(testMailBlack.getBlackWordInText().get(listIndex));
        Assertions.assertTrue(testMailBlack.isWithBlackListWordInText());


    }

    @Test
    void processAnalyticSentence() {
        testMailWhite.processAnalyticSentence();
        Assertions.assertEquals(35, testMailWhite.getAverageSentenceLength());
        Assertions.assertEquals(35, testMailWhite.getMaximumSentenceLength());

        testMailBlack.processAnalyticSentence();
        Assertions.assertEquals(45, testMailBlack.getAverageSentenceLength());
        Assertions.assertEquals(65, testMailBlack.getMaximumSentenceLength());
    }

    private int getWordIndex(ArrayList<String> list, String word) {
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            if (element.equalsIgnoreCase(word)) {
                return i;
            }
        }
        return -1;
    }

}
