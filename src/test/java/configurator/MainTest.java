package configurator;

import dataProviders.DataProvider;

public class MainTest {

    public static void main(String[] args) {
        DataProvider data = new DataProvider();
        data.writeMailAnalytic();
    }

}
