package configurator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    private int id;
    private String subject;
    private String text;
    private boolean spam;

    private boolean withWhiteListWordInSubject;
    private boolean withWhiteListWordInText;
    private boolean withBlackListWordInSubject;
    private boolean withBlackListWordInText;

    public Mail(int id, String subject, String text, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.spam = spam;

        this.withWhiteListWordInSubject = false;
        this.withWhiteListWordInText = false;
        this.withBlackListWordInSubject = false;
        this.withBlackListWordInText = false;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public boolean isSpam() {
        return spam;
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

    public void processAnalytics(ArrayList<String> whiteList, ArrayList<String> blackList){
        processAnalyticWhiteList(whiteList);
        processAnalyticBlackList(blackList);
    }

    private void processAnalyticWhiteList(ArrayList<String> whiteList){
        for (String goodWord : whiteList) {
            if (processSourceMatchesWord(this.subject, goodWord)){
                withWhiteListWordInSubject = true;
                return;
            }
            if (processSourceMatchesWord(this.text, goodWord)){
                withWhiteListWordInText = true;
                return;
            }
        }
    }

    private void processAnalyticBlackList(ArrayList<String> blackList){
        for (String badWord : blackList) {
            if (processSourceMatchesWord(this.subject, badWord)){
                withBlackListWordInSubject = true;
                return;
            }
            if (processSourceMatchesWord(this.text, badWord)){
                withBlackListWordInText = true;
                return;
            }
        }
    }

    private boolean processSourceMatchesWord(String source, String word){
        String pattern = ".*" + word + ".*";
        Pattern registrarPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = registrarPattern.matcher(source);
        return matcher.find();

    }



    public String toAnalyticCsv(){
        return id + ";"
                + spam + ";"
                + withWhiteListWordInSubject + ";"
                + withWhiteListWordInText + ";"
                + withBlackListWordInSubject + ";"
                + withBlackListWordInText;
    }
}
