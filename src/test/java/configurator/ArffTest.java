package configurator;

import dataProviders.mail.Mail;
import dataProviders.mail.MailDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArffTest {

    static MailDataProvider dataProvider;
    static Mail testMailWhite;
    static Mail testMailBlack;
    static ArrayList<Mail> testMails;

    @BeforeAll
    static void setUpData() {
        dataProvider = new MailDataProvider();
    }

    @BeforeEach
    void prepareMail() {
        testMailWhite = new Mail("123;Subject to for meeting;Text with some uselessMeeting lines.Text with some uselessMeeting lines.;0");
        testMailBlack = new Mail("1;Sexmachine;I want to check this text. Long sentence without any symbol in between but with sexy things;1");
        testMails = new ArrayList<>();
        testMails.add(testMailWhite);
        testMails.add(testMailBlack);
    }

    @Test
    void processAnalyticWhiteList() {
        new ArffFileGenerator().execute(
                testMails,
                dataProvider.getBlackListedWords(),
                dataProvider.getWhiteListedWords(),
                dataProvider.getAverageSubjectLength(),
                dataProvider.getAverageTextLength());
    }

}
