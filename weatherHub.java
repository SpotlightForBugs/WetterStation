import java.util.ArrayList;

public class weatherHub {

  private int knownStations = 0;
  private String[] knownWeatherStations = new String[1000];
  public ArrayList<WeatherHubStorage> weatherHubStorageList = new ArrayList<WeatherHubStorage>();

  public void receiveData(double[][][][] data, String name, String location) {

    // is the weather station known?
    boolean known = false;

    for (int i = 0; i < knownStations; i++) {
      if (knownWeatherStations[i].equals(name + location)) {

        weatherHubStorageList.get(i).addData(data);
        System.out.println("Data added to known weather station");
      }
    }
  }

  public void setupWeatherStation(String pName, String pLocation) {

    // is the name of the weather station and its location not already in use?

    // search the list of weather stations for the name and location hyphenated together
    for (int i = 0; i < knownStations; i++) {
      if (knownWeatherStations[i].equals(pName + "-" + pLocation)) {
        // if the name and location are already in use then exit the function
        throw new IllegalArgumentException(
            "The name and location of the weather station are already in use");
      }
    }
    // create a new array of doubles in the format of the data that is sent to the weather hub
    weatherHubStorageList.add(new WeatherHubStorage(pName, pLocation));
    knownWeatherStations[knownStations] =
        pName + "-" + pLocation; // add the name and location to the list of known weather stations
    knownStations++; // increase the number of known weather stations by 1
  }

  public void listWeatherStations() {
    for (int i = 0; i < knownStations; i++) {
      System.out.println(knownWeatherStations[i]);
    }
  }

  public void listWeatherStationData(String pName, String pLocation) {
    for (int i = 0; i < knownStations; i++) {
      if (knownWeatherStations[i].equals(pName + "-" + pLocation)) {
        weatherHubStorageList.get(i).listData();
      }
    }
  }

  public int countWeatherStations() {
    System.out.println(knownStations);
    return knownStations;
  }
}
