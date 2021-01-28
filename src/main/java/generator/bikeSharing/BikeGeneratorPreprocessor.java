package generator.bikeSharing;

import dataProviders.bikeSharing.BikeDataProvider;
import generator.GeneratorPreprocessor;

public class BikeGeneratorPreprocessor extends GeneratorPreprocessor {
    private final BikeDataProvider dataProvider;

    public BikeGeneratorPreprocessor() {
        super();
        this.title = "SpamGruppeF";
        this.dataProvider = new BikeDataProvider();
        processAttributeList();
        processDataList();
    }

    @Override
    protected void processAttributeList() {
        attributeList.add("day numeric");
        attributeList.add("month numeric");
        attributeList.add("year numeric");
        attributeList.add("season numeric");
        attributeList.add("holiday numeric");
        attributeList.add("weekday numeric");
        attributeList.add("workingDay numeric");
        attributeList.add("weatherSit numeric");
        attributeList.add("temp numeric");
        attributeList.add("atemp numeric");
        attributeList.add("hum numeric");
        attributeList.add("windSpeed numeric");
        attributeList.add("casual numeric");
        attributeList.add("registered numeric");
        attributeList.add("cnt numeric");
    }

    @Override
    protected void processDataList() {
        dataProvider.getBikeDays().forEach(bikeDay -> dataList.add(bikeDay.getConvertedDataLine()));
    }
}
