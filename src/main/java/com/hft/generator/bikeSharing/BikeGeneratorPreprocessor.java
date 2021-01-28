package com.hft.generator.bikeSharing;

import com.hft.dataProviders.bikeSharing.BikeDataProvider;
import com.hft.generator.GeneratorPreprocessor;
import com.hft.utility.TargetAttribute;

public class BikeGeneratorPreprocessor extends GeneratorPreprocessor {
    private final BikeDataProvider dataProvider;
    private final TargetAttribute targetAttribute;

    public BikeGeneratorPreprocessor(TargetAttribute targetAttribute) {
        super();
        this.title = "BikeSharingGruppeF";
        this.dataProvider = new BikeDataProvider();
        this.targetAttribute = targetAttribute;
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
        switch (targetAttribute) {
            case CASUAL:
                attributeList.add("casual numeric");
                break;
            case REGISTERED:
                attributeList.add("registered numeric");
                break;
            case CNT:
                attributeList.add("cnt numeric");
                break;
            default:
        }
    }

    @Override
    protected void processDataList() {
        dataProvider.getBikeDays().forEach(bikeDay -> dataList.add(bikeDay.getConvertedDataLine(targetAttribute)));
    }
}
