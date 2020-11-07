package configurator;

import dataProviders.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        stringBuilder.append(a + "class {0,1}");

        stringBuilder.append(nl);
        stringBuilder.append(nl);

        appendData();

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
            word.replaceAll("\\s","_");
            stringBuilder.append(word);
            stringBuilder.append(context);
            stringBuilder.append(nl);
        }
    }

    private static void appendData(){
        ArrayList<String> blacklistwords = dataProvider.getBlackListedWords();
        ArrayList<String> whitelistwords = dataProvider.getWhiteListedWords();
        ArrayList<Mail> Mails = dataProvider.getMails();

        stringBuilder.append(d);
        stringBuilder.append(nl);

        ExecutorService executor = Executors.newFixedThreadPool(11);

        for (Mail mail: Mails) {
            System.out.println("neuer thred MailProcessor");
            Runnable mailProcessor = () -> {mail.fillBoolWordLists(whitelistwords,blacklistwords);
                System.out.println("Mail: "+mail.id+" wurde fertig Bearbeitet.");};
            executor.execute(mailProcessor);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
        System.out.println("Finished all threads");

        for (Mail mail: Mails) {
            for (boolean winSub : mail.whiteWordInSub) {
                dataListAppander(winSub);
            }
            for (boolean winTxt : mail.whiteWordInText) {
                dataListAppander(winTxt);
            }
            for (boolean binSub : mail.blackWordInSub) {
                dataListAppander(binSub);
            }
            for (boolean binTxt : mail.blackWordInText){
                dataListAppander(binTxt);
            }
            stringBuilder.replace(stringBuilder.length()-1,stringBuilder.length(),""); //remove , from last in line
            stringBuilder.append(nl);
        }
    }

    private static void dataListAppander(boolean in){
        if (in){
            stringBuilder.append("1,");
        }else{
            stringBuilder.append("0,");
        }
    }
}
