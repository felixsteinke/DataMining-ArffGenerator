package runner;

import generator.FileGenerator;
import generator.bikeSharing.BikeGeneratorPreprocessor;
import utility.TargetAttribute;

public class BikeFileGenerator {

    public static void main(String[] args) {
        FileGenerator fileGenerator = new FileGenerator(new BikeGeneratorPreprocessor(TargetAttribute.CASUAL));
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/bikesharing/bikeSharing_casual.arff");
        fileGenerator = new FileGenerator(new BikeGeneratorPreprocessor(TargetAttribute.REGISTERED));
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/bikesharing/bikeSharing_registered.arff");
        fileGenerator = new FileGenerator(new BikeGeneratorPreprocessor(TargetAttribute.CNT));
        fileGenerator.execute();
        fileGenerator.writeFile("src/main/resources/bikesharing/bikeSharing_cnt.arff");
    }
}
