package configurator;

import java.io.*;
import java.util.ArrayList;

public class CsvReader {

    public static ArrayList<Mail> readCsvFile(String pathToFile, String separator) {
        int ignoredLines = 0;
        ArrayList<Mail> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitLine = line.split(separator);
                try{
                    list.add(new Mail((Integer.parseInt(splitLine[0])), splitLine[1], splitLine [2], splitLine[3].equalsIgnoreCase("1")));
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
        ArrayList<String> result = new ArrayList();
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

    public static void writeCsvFile(String pathToFile, ArrayList<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (String str : data) {
                writer.write(str + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
