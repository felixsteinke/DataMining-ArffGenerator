package com.hft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Mail {

    private int id;
    private String subject;
    private String text;
    private boolean spam;

    private boolean containsWhiteListWord;
    private boolean containsBlackListWord;

    public Mail(int id, String subject, String text, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.spam = spam;

        this.containsBlackListWord = false;
    }

    public void processAnalytics(ArrayList<String> whiteList, ArrayList<String> blackList){
        processAnalyticWhiteList(whiteList);
        processAnalyticBlackList(blackList);
    }

    private void processAnalyticWhiteList(ArrayList<String> whiteList){
        for (String goodWord : whiteList) {
            if (subject.contains(goodWord) || text.contains(goodWord)){

            }
        }
    }

    private void processAnalyticBlackList(ArrayList<String> blackList){

    }

    public String toAnalyticCsv(){
        return id + ";" + spam + ";" + containsWhiteListWord + ";" + containsBlackListWord;
    }
}
