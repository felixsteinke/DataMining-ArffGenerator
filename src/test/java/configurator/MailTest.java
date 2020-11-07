package configurator;

import dataProviders.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MailTest {

    static DataProvider dataProvider;
    Mail testMailClean;
    Mail testMailTrash;

    @BeforeAll
    static void setUpData() {
        dataProvider = new DataProvider();
    }

    @BeforeEach
    void prepareMail() {
        testMailClean = new Mail(
                123,
                "Subject to for meeting",
                "Text with some useless lines.",
                false
        );
        testMailTrash = new Mail(
                1,
                "Sexmachine",
                "I want to check this text. Long sentence without any symbol in between",
                true
        );
    }

    @Test
    void processAnalyticWhiteList() {
        ArrayList<String> whiteList = dataProvider.getWhiteListedWords();
        testMailClean.processAnalyticWhiteList(whiteList);
        int whiteListIndex = -1;
        for (int i = 0; i < whiteList.size(); i++) {
            String word = whiteList.get(i);
            if (word.equalsIgnoreCase("meeting")) {
                whiteListIndex = i;
            }
        }
        Assertions.assertTrue(testMailClean.getWhiteWordInSub().get(whiteListIndex));
    }

    @Test
    void processAnalyticBlackList() {
    }

    @Test
    void fillBoolWordLists() {

    }

    @Test
    void toCsvString() {

    }
}
