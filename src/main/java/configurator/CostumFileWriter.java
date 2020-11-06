package configurator;

import dataProviders.DataProvider;

import java.io.*;
import java.util.ArrayList;

public class CostumFileWriter {

    private static StringBuilder stringBuilder = new StringBuilder();
    private static DataProvider dataProvider = new DataProvider();

    private static String a = "@attribute ";
    private static String iT = "_in_Text";
    private static String iS = "_in_Subject";
    private static String r = "@relation spamGruppeF";
    private static String n = "numeric";
    private static String d = "@data";
    private static String file = "src/main/resources/GruppeF.arff";
    private static String nl = "\n";

    public static void main(String[] args) {

        stringBuilder.append(r);
        stringBuilder.append(nl);
        stringBuilder.append(nl);

        appendAttributeFromListAs(dataProvider.getBlackListedWords(),iT);
        appendAttributeFromListAs(dataProvider.getBlackListedWords(),iS);
        appendAttributeFromListAs(dataProvider.getWhiteListedWords(),iT);
        appendAttributeFromListAs(dataProvider.getWhiteListedWords(),iS);

        String result = stringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write(result);
            bufferedWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendAttributeFromListAs(ArrayList<String> wordList,String context){
        for (String word: wordList) {
            stringBuilder.append(a);
            //TODO remove whitespace from word
            stringBuilder.append(word);
            stringBuilder.append(context);
            stringBuilder.append(nl);
        }
    }
}
