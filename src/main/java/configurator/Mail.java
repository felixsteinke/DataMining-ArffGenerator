package configurator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    public final int id;
    private final String subject;
    private final String text;
    private final boolean spam;

    public ArrayList<Boolean> blackWordInText;
    public ArrayList<Boolean> whiteWordInText;
    public ArrayList<Boolean> blackWordInSub;
    public ArrayList<Boolean> whiteWordInSub;

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

        this.blackWordInSub = new ArrayList<>();
        this.blackWordInText = new ArrayList<>();
        this.whiteWordInSub = new ArrayList<>();
        this.whiteWordInText = new ArrayList<>();

        this.withWhiteListWordInSubject = false;
        this.withWhiteListWordInText = false;
        this.withBlackListWordInSubject = false;
        this.withBlackListWordInText = false;

        this.averageSentenceLength = 0;
        this.maximumSentenceLength = 0;

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
            //Subject
            if (sourceMatchesWord(this.subject, goodWord)) {
                this.whiteWordInSub.add(true);
                withWhiteListWordInSubject = true;
            } else {
                this.whiteWordInSub.add(false);
            }

            if (sourceMatchesWord(this.text, goodWord)) {
                this.whiteWordInText.add(true);
                withWhiteListWordInText = true;
            } else {
                this.whiteWordInText.add(false);
            }
        }
    }

    public void processAnalyticBlackList(ArrayList<String> blackList) {
        for (String badWord : blackList) {
            //Subject
            if (sourceMatchesWord(this.subject, badWord)) {
                this.blackWordInSub.add(true);
                withBlackListWordInSubject = true;
            } else {
                this.blackWordInSub.add(false);
            }
            //Text
            if (sourceMatchesWord(this.text, badWord)) {
                this.blackWordInText.add(true);
                withBlackListWordInText = true;
            } else {
                this.blackWordInText.add(false);
            }
        }
    }

    private boolean sourceMatchesWord(String source, String word) {
        String pattern = ".*" + word + ".*";
        Pattern registrarPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = registrarPattern.matcher(source);
        return matcher.find();
    }

    private void processAnalyticSentence() {
        ArrayList<Integer> sentenceLengths = new ArrayList<>();
        for (int start = 0; start <= text.length(); ) {
            int end = calculateNextSentenceLength(start, this.text);
            sentenceLengths.add(end - start);
            start = end;
        }
        for (int length : sentenceLengths) {
            averageSentenceLength += length;
            if (length > maximumSentenceLength) {
                maximumSentenceLength = length;
            }
        }
        averageSentenceLength /= sentenceLengths.size();
    }

    private int calculateNextSentenceLength(int index, String text) {
        char[] charText = text.toCharArray();
        for (; index <= charText.length; index++) {
            if (charText[index] == '.') {
                return index;
            }
        }
        return charText.length;
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

    public ArrayList<Boolean> getBlackWordInText() {
        return blackWordInText;
    }

    public ArrayList<Boolean> getWhiteWordInText() {
        return whiteWordInText;
    }

    public ArrayList<Boolean> getBlackWordInSub() {
        return blackWordInSub;
    }

    public ArrayList<Boolean> getWhiteWordInSub() {
        return whiteWordInSub;
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

    public boolean isSpam(){
        return this.spam;
    }
}
