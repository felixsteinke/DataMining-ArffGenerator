package dataProviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileReader {

    public ArrayList<String> readFile(String pathToFile) {
        int ignoredLines = 0;
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            br.readLine(); //head line
            while ((line = br.readLine()) != null) {
                try {
                    list.add(line);
                } catch (Exception e) {
                    ignoredLines++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Ignored " + ignoredLines + " Lines of Data.");
        return list;
    }

    public ArrayList<String> readWordlist(String pathToFile) {
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
