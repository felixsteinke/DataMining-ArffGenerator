package dataProviders;

import configurator.Mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileReader {

    public static ArrayList<Mail> readCsvFile(String pathToFile, String separator) {
        int ignoredLines = 0;
        ArrayList<Mail> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(separator);
                try {
                    list.add(new Mail((Integer.parseInt(splitLine[0])), splitLine[1], splitLine[2], splitLine[3].equalsIgnoreCase("1")));
                } catch (Exception e){
                    ignoredLines++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Ignored " + ignoredLines + " Lines of Data.");
        return list;
    }

    public static ArrayList<String> readWordlist(String pathToFile){
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
