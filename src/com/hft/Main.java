package com.hft;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList <Mail> sourceData = CsvReader.readCsvFile("resources/enron.csv",";");
        System.out.println("Finished!");
    }
}
