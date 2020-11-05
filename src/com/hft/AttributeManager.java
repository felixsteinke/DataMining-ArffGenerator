package com.hft;

import java.util.ArrayList;

public class AttributeManager {

    private  ArrayList<String> blackListedWords;

    public AttributeManager() {
        this.blackListedWords = new ArrayList<>();
        blackListedWords.add("sex");
    }

    public  void containsBlacklistWord(Mail mail){

    }

}
