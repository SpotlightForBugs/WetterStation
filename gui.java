import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gui extends JFrame implements ActionListener {

  weatherHub weatherHub = new weatherHub();

  public gui() {

    super("Weather Hub");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    // add a new panel to the frame
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    // add a new button to the panel
    JButton button = new JButton("Reload");
    panel.add(button);

    java.awt.Container pane = getContentPane();
    pane.add(panel);

    setVisible(true);

    button.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            // create  weatherHub.countWeatherStations() tables with 24 rows and 6 columns
            // each table will have a name and location
            // each table will have all the data for the day in it (24 rows and 6 columns)

            // create a new panel to hold the tables
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(weatherHub.countWeatherStations(), 1));
            // add the tables to the panel
            for (int i = 0; i < weatherHub.countWeatherStations(); i++) {

              // create a new table

              String location = weatherHub.weatherHubStorageList.get(i).getLocation();
              String name = weatherHub.weatherHubStorageList.get(i).getName();
              double[][][][] data = weatherHub.weatherHubStorageList.get(i).getData();
              JTable table = new JTable(24, 6);
              table.setName(name + " " + location);
              // add the data to the table
              for (int j = 0; j < 24; j++) {
                for (int k = 0; k < 6; k++) {
                  table.setValueAt(
                      data[weatherHub.weatherHubStorageList.get(i).getDayFromData(data)][j][k][0],
                      j,
                      k);
                }
              }
            }
          }
        });
  }

  public static void main(String args[]) {
    new gui();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}
