package configurator;

import dataProviders.DataProvider;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataProvider manager = new DataProvider();
        ArrayList<Mail> mails = manager.getMailSource();
        long startTime = System.currentTimeMillis();
        int mailCounter = 0;
        for (Mail mail : mails) {
            mail.processAnalytics(manager.getWhiteListedWords(), manager.getBlackListedWords());
            mailCounter++;
            if ((mailCounter%100) == 0){
                System.out.println("[INFO] Progress at " + mailCounter + " from " + mails.size());
            }
        }
        int counterWhiteSubject = 0;
        int counterWhiteText = 0;
        int counterBlackSubject = 0;
        int counterBlackText = 0;

        for (Mail mail : manager.getMailSource()) {
            if (mail.isWithWhiteListWordInSubject())
                counterWhiteSubject++;
            if (mail.isWithWhiteListWordInText())
                counterWhiteText++;
            if (mail.isWithBlackListWordInSubject())
                counterBlackSubject++;
            if (mail.isWithBlackListWordInText())
                counterBlackText++;
        }
        System.out.println(counterWhiteSubject);
        System.out.println(counterWhiteText);
        System.out.println(counterBlackSubject);
        System.out.println(counterBlackText);

        long endTime = System.currentTimeMillis();
        System.out.println("[INFO] Time needed: " + (endTime-startTime));

        manager.writeMailAnalytic();

    }
}
