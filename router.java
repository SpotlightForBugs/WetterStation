import java.util.ArrayList;

public class router {

  private weatherHub weatherHub;

  public router(weatherHub weatherHub) {
    this.weatherHub = weatherHub;
  }

  // create a list of weather stations and add the function to add a weather station to the list
  private ArrayList<WeatherStation> weatherStations = new ArrayList<WeatherStation>();
  private double[][][][] TransmissionHistory =
      new double[10000000][24][6]
          [1]; // this is the history of all the data that has been sent to the weather hub
  private double[][][][] receiveData =
      new double[10000000][24][6][1]; // this is the data that is being sent to the weather hub

  public int getDayFromData(double[][][][] data) {
    double day = 0;
    for (double i = 0; i < data.length; i++) {
      if (data[(int) i][0][0][0] != 0) {
        day = i;
      }
    }
    return (int) day;
  }

  public void receiveDataFromStation(double[][][][] data, String name, String location) {
    // this function receives data from a weather station and adds it to the history of data that
    // has been sent to the weather hub
    // it also sends the data to the weather hub
    int day = getDayFromData(data);
    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 6; j++) {
        TransmissionHistory[day][i][j][0] = data[day][i][j][0];
      }
    }
    weatherHub.receiveData(data, name, location);
  }

  public void sendAllDataToHub(double[][][][] data, String name, String location) {

    // send the whole array of data to the weather hub
    weatherHub.receiveData(data, name, location);
    // set the data in the transmission history to the data that was sent, overwriting the old data
    // because all the data has been sent
    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 6; j++) {
        TransmissionHistory[getDayFromData(data)][i][j][0] = data[getDayFromData(data)][i][j][0];
      }
    }
  }

  public void addWeatherStation(WeatherStation weatherStation) {
    weatherStations.add(weatherStation);
    weatherHub.setupWeatherStation(weatherStation.name, weatherStation.location);
  }
}
