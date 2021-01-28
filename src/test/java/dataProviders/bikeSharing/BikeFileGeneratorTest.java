package dataProviders.bikeSharing;

import generator.FileGenerator;
import generator.bikeSharing.BikeGeneratorPreprocessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.TargetAttribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BikeFileGeneratorTest {

    private static String executionResult;

    @BeforeAll
    static void executeGenerator() {
        executionResult = new FileGenerator(new BikeGeneratorPreprocessor(TargetAttribute.CASUAL)).execute();
    }

    @Test
    public void resultStructure() {
        assertTrue(executionResult.contains("@data"));
        assertTrue(executionResult.contains("@relation"));
        assertTrue(executionResult.contains("@attribute"));
    }

    @Test
    public void containsAttribute10() {
        int i = 0;
        Pattern p = Pattern.compile("attribute");
        Matcher m = p.matcher(executionResult);
        while (m.find()) {
            i++;
        }
        assertTrue(i > 10);
    }
}
