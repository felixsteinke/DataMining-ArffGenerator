package configurator;

import dataProviders.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {

    public static void main(String[] args) {
        DataProvider data = new DataProvider();
        data.writeMailAnalytic();
    }

}
