package com.hft;

import java.util.ArrayList;

public class AttributeManager {

    private  ArrayList<String> blackListedWords;

    public AttributeManager() {
        this.blackListedWords = CsvReader.readWordlist("resources/badwords.csv");
    }

    public  void containsBlacklistWord(Mail mail){

    }

}
