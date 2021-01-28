package runner;

import generator.FileGenerator;
import generator.bikeSharing.BikeGeneratorPreprocessor;

public class BikeFileGenerator {

    public static void main(String[] args) {
        FileGenerator fileGenerator = new FileGenerator(new BikeGeneratorPreprocessor());
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/bikesharing/bikeSharing.arff");
    }
}
