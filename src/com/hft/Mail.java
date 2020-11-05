package com.hft;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Mail {

    private int id;
    private String subject;
    private String text;
    boolean spam;

    Map<String,Boolean> analyticAttributes;

    public Mail(int id, String subject, String text, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.spam = spam;
        analyticAttributes = new HashMap<>();
    }

    public void addAnalyticAttribute(String key, boolean value){
        analyticAttributes.put(key, value);
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

    public String toAnalyticCsv(){
        StringBuilder stringBuilder = new StringBuilder(id + ";" + spam);
        Set<String> analyticKeys = analyticAttributes.keySet();
        for (String key : analyticKeys) {
            stringBuilder.append(";" + analyticAttributes.get(key));
        }
        return stringBuilder.toString();
    }
}
