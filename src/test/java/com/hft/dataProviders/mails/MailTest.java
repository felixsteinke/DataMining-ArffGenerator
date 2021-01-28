package com.hft.dataProviders.mails;

import com.hft.dataProviders.mail.Mail;
import com.hft.dataProviders.mail.MailDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MailTest {

    static MailDataProvider dataProvider;
    Mail testMailWhite;
    Mail testMailBlack;

    @BeforeAll
    static void setUpData() {
        dataProvider = new MailDataProvider();
    }

    @BeforeEach
    void prepareMail() {
        testMailWhite = new Mail("123;Subject to for meeting;Text with some uselessMeeting lines.Text with some uselessMeeting lines.;0");
        testMailBlack = new Mail("1;Sexmachine;I want to check this text. Long sentence without any symbol in between but with sexy things;1");
    }

    @Test
    void processAnalyticWhiteList() {
        ArrayList<String> whiteList = dataProvider.getWhiteListedWords();
        int listIndex = getWordIndex(whiteList, "meeting");

        testMailWhite.processAnalyticWhiteList(whiteList);

        Assertions.assertTrue(testMailWhite.getWhiteWordInSub().get(listIndex));
        Assertions.assertTrue(testMailWhite.getWhiteWordInText().get(listIndex));
    }

    @Test
    void processAnalyticBlackList() {
        ArrayList<String> blackList = dataProvider.getBlackListedWords();
        int listIndex = getWordIndex(blackList, "sex");

        testMailBlack.processAnalyticBlackList(blackList);

        Assertions.assertTrue(testMailBlack.getBlackWordInSub().get(listIndex));
        Assertions.assertTrue(testMailBlack.getBlackWordInText().get(listIndex));


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
