package configurator;

import dataProviders.DataProvider;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataProvider manager = new DataProvider();
        ArrayList<Mail> mails = manager.getMails();
        long startTime = System.currentTimeMillis();
        int mailCounter = 0;
        for (Mail mail : mails) {
            mail.processAnalytics(
                    manager.getWhiteListedWords(),
                    manager.getBlackListedWords(),
                    manager.getAverageSubjectLength(),
                    manager.getAverageTextLength());
            mailCounter++;
            if ((mailCounter % 1) == 0) {
                System.out.println("[INFO] Progress at " + mailCounter + " from " + mails.size());
            }
        }

        manager.writeMailAnalytic();

    }
}
