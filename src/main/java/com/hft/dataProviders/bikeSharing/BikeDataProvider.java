package com.hft.dataProviders.bikeSharing;

import com.hft.dataProviders.CsvFileReader;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BikeDataProvider {

    private final ArrayList<BikeDay> bikeDays;

    public BikeDataProvider() {
        CsvFileReader reader = new CsvFileReader();
        this.bikeDays = new ArrayList<>();
        reader.readFile("src/main/resources/bikeSharing/BikeSharingDay.csv").forEach(day -> bikeDays.add(new BikeDay(day)));
    }
}
