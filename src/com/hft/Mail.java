package com.hft;

public class Mail {

    private int id;
    private String subject;
    private String text;
    boolean spam;


    public Mail(int id, String subject, String text, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.spam = spam;
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
}
