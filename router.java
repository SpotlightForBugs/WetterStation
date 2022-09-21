import java.util.ArrayList;

public class router {
 
    
    //create a list of weather stations and add the function to add a weather station to the list
    private ArrayList<WeatherStation> weatherStations = new ArrayList<WeatherStation>();
    private double[][][][] TransmissionHistory = new double[10000000][24][6][1]; //this is the history of all the data that has been sent to the weather hub
     

   
    

    public void receiveDataFromStation(double[][][][] data, String name, String location) {

        //get the day from the first index of the array

        
        



    }

    




        

    public void sendAllDataToHub(double[][][][] data, String name, String location) {
       
        //send the whole array of data to the weather hub
        weatherHub.receiveData(data, name, location);
       

        
        
    }


    public void addWeatherStation(WeatherStation weatherStation) {
        weatherStations.add(weatherStation);
        weatherHub.setupWeatherStation(weatherStation.name, weatherStation.location);
    }
    
    
}
