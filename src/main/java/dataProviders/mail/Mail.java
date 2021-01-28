package dataProviders.mail;

import lombok.Getter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Mail {

    public final int id;
    private final String subject;
    private final String text;
    private final boolean spam;

    public ArrayList<Boolean> blackWordInText;
    public ArrayList<Boolean> whiteWordInText;
    public ArrayList<Boolean> blackWordInSub;
    public ArrayList<Boolean> whiteWordInSub;

    private int averageSentenceLength;
    private int maximumSentenceLength;

    private boolean biggerThanAverageSubjectLength;
    private boolean biggerThanAverageTextLength;

    public Mail(String csvLine) {
        String[] splitLine = csvLine.split(";");
        this.id = Integer.parseInt(splitLine[0]);
        this.subject = splitLine[1];
        this.text = splitLine[2];
        this.spam = splitLine[3].equalsIgnoreCase("1");

        this.blackWordInSub = new ArrayList<>();
        this.blackWordInText = new ArrayList<>();
        this.whiteWordInSub = new ArrayList<>();
        this.whiteWordInText = new ArrayList<>();

        this.averageSentenceLength = 0;
        this.maximumSentenceLength = 0;

        this.biggerThanAverageSubjectLength = false;
        this.biggerThanAverageTextLength = false;

    }

    public void processAnalytics(ArrayList<String> whiteList, ArrayList<String> blackList, int averageSubjectLength, int averageTextLength) {
        processAnalyticWhiteList(whiteList);
        processAnalyticBlackList(blackList);

        processAnalyticAverageLengths(averageSubjectLength, averageTextLength);

        processAnalyticSentence();
    }


    public void processAnalyticWhiteList(ArrayList<String> whiteList) {
        for (String goodWord : whiteList) {
            //Subject
            if (sourceMatchesWord(this.subject, goodWord)) {
                this.whiteWordInSub.add(true);
            } else {
                this.whiteWordInSub.add(false);
            }

            if (sourceMatchesWord(this.text, goodWord)) {
                this.whiteWordInText.add(true);
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
            } else {
                this.blackWordInSub.add(false);
            }
            //Text
            if (sourceMatchesWord(this.text, badWord)) {
                this.blackWordInText.add(true);
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

    public void processAnalyticSentence() {
        ArrayList<Integer> sentenceLengths = new ArrayList<>();
        int textLength = text.length();
        for (int start = 0; start < textLength; ) {
            int end = calculateNextSentenceLength(start, text);
            sentenceLengths.add(end - start);
            start = end + 1;
        }
        for (int length : sentenceLengths) {
            averageSentenceLength += length;
            if (length > maximumSentenceLength) {
                maximumSentenceLength = length;
            }
        }
        try {
            averageSentenceLength /= sentenceLengths.size();
        } catch (Exception e) {
            averageSentenceLength = 0;
            System.out.println("[INFO] Mail: "+ this.id +" hat kein Text.");
        }
    }

    private int calculateNextSentenceLength(int index, String text) {
        char[] charText = text.toCharArray();
        for (; index < charText.length; index++) {
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
    Data Converter
     */

    public String getConvertedBoolArrays() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(boolListConverter(whiteWordInSub));
        stringBuilder.append(boolListConverter(whiteWordInText));
        stringBuilder.append(boolListConverter(blackWordInSub));
        stringBuilder.append(boolListConverter(blackWordInText));

        stringBuilder.append(averageSentenceLength).append(",");
        stringBuilder.append(maximumSentenceLength).append(",");

        stringBuilder.append(boolConverter(biggerThanAverageSubjectLength)).append(",");
        stringBuilder.append(boolConverter(biggerThanAverageTextLength)).append(",");

        stringBuilder.append(boolConverter(spam));
        stringBuilder.append("\n"); //new line
        return stringBuilder.toString();
    }

    private String boolListConverter(ArrayList<Boolean> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean bool : list) {
            stringBuilder.append(boolConverter(bool)).append(",");
        }
        return stringBuilder.toString();
    }

    private int boolConverter(boolean bool) {
        if (bool) {
            return 1;
        } else {
            return 0;
        }
    }
}
