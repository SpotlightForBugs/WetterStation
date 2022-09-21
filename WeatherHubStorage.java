public class WeatherHubStorage {

  private double[][][][] storage = new double[10000000][24][6][1];

  public WeatherHubStorage(String pName, String pLocation) {}

  public void listData() {
    // print all data in the storage array as long as the day is not null
    for (int i = 0; i < storage.length; i++) {
      if (storage[i][0][0][0] != 0) {
        System.out.println("Day " + i);
        for (int j = 0; j < 24; j++) {
          for (int k = 0; k < 6; k++) {
            System.out.println(storage[i][j][k][0]);
          }
        }
      }
    }
  }

  public void addData(double[][][][] data) {
    // add the data to the storage array
    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 6; j++) {
        storage[getDayFromData(data)][i][j][0] = data[getDayFromData(data)][i][j][0];
      }
    }
  }

  public int getDayFromData(double[][][][] data) {
    double day = 0;
    for (double i = 0; i < data.length; i++) {
      if (data[(int) i][0][0][0] != 0) {
        day = i;
      }
    }
    return (int) day;
  }

  public double[][][][] getData() {
    return storage;
  }

  public void setData(double[][][][] data) {
    storage = data;
  }
}
