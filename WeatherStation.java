import java.util.Random;

public class WeatherStation {

  //

  private router router;
  private double[][][][] data;
  String name;
  String location;
  private int day = 0;

  public WeatherStation(router router, String name, String location) {
    this.router = router;
    this.name = name;
    this.location = location;
    this.data = new double[10000000][24][6][1];
    this.router.addWeatherStation(this);
  }

  // it generates random data for the weather station as a multi-dimensional array of doubles
  // the format of the data is as follows:
  // data[day][time in hours][data type as number][the actual data] //example: data[6][13][4][360] =
  // 360 degrees of wind direction at 13:00 on day 6

  // the time is incremented by 1 hour for each row of the array

  // the temperature is in degrees celsius and is a double between -89.2 and 56.7  the data type is
  // 0
  // the humidity is a double between 0 and 100 because it is a percentage and the data type is 1
  // the wind speed is in km/h and is a double between 0 and 100 and the data type is 2
  // the wind direction is a double between 0 and 360 and the data type is 3
  // the rainfall is in mm and is a double between 0 and 100 and the data type is 4
  // the pressure is in hPa and is a double between 0 and 1000 and the data type is 5

  public double[][][][] measureData() {
    Random random = new Random();
    // day is the next day to be measured which is the day after the last day that has been measured

    for (int i = 0; i < 24; i++) {
      data[day][i][0][0] = random.nextDouble() * 146 - 89.2;
      data[day][i][1][0] = random.nextDouble() * 100;
      data[day][i][2][0] = random.nextDouble() * 100;
      data[day][i][3][0] = random.nextDouble() * 360;
      data[day][i][4][0] = random.nextDouble() * 100;
      data[day][i][5][0] = random.nextDouble() * 1000;
    }
    day++;
    return data;
  }

  public void sendAllData() {
    // the next function is the sendData function which sends the whole array of
    // data to the router
    router.receiveDataFromStation(data, name, location);
  }

  public void sendDayData(int sendDay) {
    // first check if the day is valid by checking if it is less or equal to the day that is being
    // measured
    if (sendDay <= day) {
      // create a new array to send the data for the day
      double[][][][] dayData = new double[1][24][6][1];
      // copy the data for the day into the new array
      for (int i = 0; i < 24; i++) {
        for (int j = 0; j < 6; j++) {
          dayData[0][i][j][0] = data[sendDay][i][j][0];
        }
      }
      // send the data for the day to the router
      router.receiveDataFromStation(dayData, name, location);
    }
    throw new IllegalArgumentException("The day you are trying to send is not valid");
  }
}
