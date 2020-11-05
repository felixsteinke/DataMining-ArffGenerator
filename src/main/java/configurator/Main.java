package configurator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataProvider manager = new DataProvider();
        for (Mail mail : manager.getSourceData()) {
            mail.processAnalytics(manager.getWhiteListedWords(), manager.getBlackListedWords());
        }
        int counterWhiteSubject = 0;
        int counterWhiteText = 0;
        int counterBlackSubject = 0;
        int counterBlackText = 0;

        int intersection = 0;

        int mailCounter = 0;
        for (Mail mail : manager.getSourceData()) {
            if (mail.isWithWhiteListWordInSubject())
                counterWhiteSubject++;
            if (mail.isWithWhiteListWordInText())
                counterWhiteText++;
            if (mail.isWithBlackListWordInSubject())
                counterBlackSubject++;
            if (mail.isWithBlackListWordInText())
                counterBlackText++;

            mailCounter++;
            if ((mailCounter%1) == 0){
                System.out.println("[INFO] Progress at " + mailCounter + " from " + manager.getSourceData().size());
            }
        }
        System.out.println(counterWhiteSubject);
        System.out.println(counterWhiteText);
        System.out.println(counterBlackSubject);
        System.out.println(counterBlackText);
        System.out.println(intersection);

    }
}
