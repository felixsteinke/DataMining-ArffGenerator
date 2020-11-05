package configurator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList <Mail> sourceData = CsvReader.readCsvFile("resources/enron.csv",";");
        AttributeManager manager = new AttributeManager();
        System.out.println("Finished!");
    }
}
