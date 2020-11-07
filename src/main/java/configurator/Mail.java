package configurator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    private ArrayList<Boolean> blackWordInText;
    private ArrayList<Boolean> whiteWordInText;
    private ArrayList<Boolean> blackWordInSub;
    private ArrayList<Boolean> whiteWordInSub;

    private final int id;
    private final String subject;
    private final String text;
    private final boolean spam;

    private boolean withWhiteListWordInSubject;
    private boolean withWhiteListWordInText;
    private boolean withBlackListWordInSubject;
    private boolean withBlackListWordInText;

    private int averageSentenceLength;
    private int maximumSentenceLength;

    private boolean biggerThanAverageSubjectLength;
    private boolean biggerThanAverageTextLength;

    public Mail(int id, String subject, String text, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.spam = spam;

        this.withWhiteListWordInSubject = false;
        this.withWhiteListWordInText = false;
        this.withBlackListWordInSubject = false;
        this.withBlackListWordInText = false;

        processSentenceAnalysis();

        this.biggerThanAverageSubjectLength = false;
        this.biggerThanAverageTextLength = false;
    }

    public void processAnalytics(ArrayList<String> whiteList, ArrayList<String> blackList, int averageSubjectLength, int averageTextLength) {
        processAnalyticWhiteList(whiteList);
        processAnalyticBlackList(blackList);

        processAnalyticAverageLengths(averageSubjectLength, averageTextLength);
    }

    public void processAnalyticWhiteList(ArrayList<String> whiteList) {
        for (String goodWord : whiteList) {
            if (sourceMatchesWord(this.subject, goodWord)) {
                withWhiteListWordInSubject = true;
                return;
            }
            if (sourceMatchesWord(this.text, goodWord)) {
                withWhiteListWordInText = true;
                return;
            }
        }
    }

    public void processAnalyticBlackList(ArrayList<String> blackList) {
        for (String badWord : blackList) {
            if (sourceMatchesWord(this.subject, badWord)) {
                withBlackListWordInSubject = true;
                return;
            }
            if (sourceMatchesWord(this.text, badWord)) {
                withBlackListWordInText = true;
                return;
            }
        }
    }

    private boolean sourceMatchesWord(String source, String word) {
        String pattern = ".*" + word + ".*";
        Pattern registrarPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = registrarPattern.matcher(source);
        return matcher.find();
    }

    private void processSentenceAnalysis() {
        //TODO
        this.averageSentenceLength = -1;
        this.maximumSentenceLength = -1;
    }


    public void processAnalyticAverageLengths(int averageSubjectLength, int averageTextLength) {
        if (this.subject.length() > averageSubjectLength)
            this.biggerThanAverageSubjectLength = true;
        if (this.text.length() > averageTextLength)
            this.biggerThanAverageTextLength = true;
    }

    /*
    toString
     */

    public String toCsvString() {
        return id + ";"
                + spam + ";"

                + withWhiteListWordInSubject + ";"
                + withWhiteListWordInText + ";"
                + withBlackListWordInSubject + ";"
                + withBlackListWordInText + ";"

                + averageSentenceLength + ";"
                + maximumSentenceLength + ";"

                + biggerThanAverageSubjectLength + ";"
                + biggerThanAverageTextLength;
    }

    /*
    Getter
     */

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public boolean isWithWhiteListWordInSubject() {
        return withWhiteListWordInSubject;
    }

    public boolean isWithWhiteListWordInText() {
        return withWhiteListWordInText;
    }

    public boolean isWithBlackListWordInSubject() {
        return withBlackListWordInSubject;
    }

    public boolean isWithBlackListWordInText() {
        return withBlackListWordInText;
    }

    public int getAverageSentenceLength() {
        return averageSentenceLength;
    }

    public int getMaximumSentenceLength() {
        return maximumSentenceLength;
    }

    public boolean isBiggerThanAverageSubjectLength() {
        return biggerThanAverageSubjectLength;
    }

    public boolean isBiggerThanAverageTextLength() {
        return biggerThanAverageTextLength;
    }
}
