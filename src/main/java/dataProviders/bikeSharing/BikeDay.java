package dataProviders.bikeSharing;

import lombok.Getter;

@Getter
public class BikeDay {
    private final int day; // dteday split
    private final int month; // dteday split
    private final int year; // dteday split
    private final int season; // season (1:springer, 2:summer, 3:fall, 4:winter)
    private final boolean holiday; // weather day is holiday or not (extracted from http://dchr.dc.gov/page/holiday-schedule)
    private final int weekday; // day of the week
    private final boolean workingDay; // if day is neither weekend nor holiday is 1, otherwise is 0.
    private final int weatherSit;
    /*
        - 1: Clear, Few clouds, Partly cloudy, Partly cloudy
		- 2: Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist
		- 3: Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds
		- 4: Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog
     */
    private final double temp; // Normalized temperature in Celsius. The values are divided to 41 (max)
    private final double atemp; // Normalized feeling temperature in Celsius. The values are divided to 50 (max)
    private final double hum; // Normalized humidity. The values are divided to 100 (max)
    private final double windSpeed; // Normalized wind speed. The values are divided to 67 (max)
    private final int casual; // count of casual users
    private final int registered; // count of registered users
    private final int cnt; // count of total rental bikes including both casual and registered


    public BikeDay(String csvLine) {
        String[] splitLine = csvLine.split(",");
        String[] dateSplit = splitLine[1].split("-");
        this.day = Integer.parseInt(dateSplit[0]);
        this.month = Integer.parseInt(dateSplit[1]);
        this.year = Integer.parseInt(dateSplit[2]);
        this.season = Integer.parseInt(splitLine[2]);
        this.holiday = splitLine[5].equalsIgnoreCase("1");
        this.weekday = Integer.parseInt(splitLine[6]);
        this.workingDay = splitLine[7].equalsIgnoreCase("1");
        this.weatherSit = Integer.parseInt(splitLine[8]);
        this.temp = Double.parseDouble(splitLine[9]) * 41;
        this.atemp = Double.parseDouble(splitLine[10]) * 50;
        this.hum = Double.parseDouble(splitLine[11]) * 100;
        this.windSpeed = Double.parseDouble(splitLine[12]) * 67;
        this.casual = Integer.parseInt(splitLine[13]);
        this.registered = Integer.parseInt(splitLine[14]);
        this.cnt = Integer.parseInt(splitLine[15]);
    }
}
