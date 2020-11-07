package configurator;

import dataProviders.DataProvider;

public class MainTest {

    public static void main(String[] args) {
        DataProvider data = new DataProvider();
        data.writeMailAnalytic();
        for (int i = 565; i > 0;i--){
            System.out.print("0,");
        }
    }

}
