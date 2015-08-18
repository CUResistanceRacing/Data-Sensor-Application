/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * @author sachethhegde
 * 
 */
public class ConfigurationsCreator extends JFrame{
	TextField ipAddress, delimiter, colNameChooser, graphNameChooser;
	JComboBox colList, graphList;
	JButton addButton, deleteButton, addGraphButton, deleteGraphButton;
	ConfigurationListener listener;

	// Delete later
	private int length = 2;

	public ConfigurationsCreator() {
		// Must be changed later, looks horrible now
		setLayout(new FlowLayout()); 

		listener = new ConfigurationListener();

		/* Meant for the overall application settings, used for getting data */
		/////////
		add(new Label("IP Address:")); 
		ipAddress = new TextField(40); ipAddress.setEditable(true); ipAddress.addActionListener(listener); add(ipAddress);               

		add(new Label("Delimiter:")); 
		delimiter = new TextField(4); delimiter.setEditable(true); delimiter.addActionListener(listener); add(delimiter);

		add(new Label("Visual Elements: " + length)); 

		add(new Label("Data Columns (in order):"));
		colList = new JComboBox(); colList.addActionListener(listener); add(colList);

		add(new Label("Column Name:"));
		colNameChooser = new TextField(4); colNameChooser.setEditable(true); colNameChooser.addActionListener(listener); add(colNameChooser);

		addButton = new JButton("Add"); addButton.addActionListener(listener); add(addButton);
		deleteButton = new JButton("Delete"); deleteButton.addActionListener(listener); add(deleteButton);
		/////////

		/* Meant for specific visual display settings, used for getting data. Just for graphs for now. */
		/////////
		add(new Label("Graph Selection:"));
		graphList = new JComboBox(); graphList.addActionListener(listener); add (graphList);

		add(new Label("Graph Name:"));
		graphNameChooser = new TextField(40); graphNameChooser.setEditable(true);  graphNameChooser.addActionListener(listener); add(graphNameChooser);  

		JButton addGraphButton = new JButton("Add Graph"); addGraphButton.addActionListener(listener); add(addGraphButton);
		JButton deleteGraphButton = new JButton("Delete Graph");  deleteGraphButton.addActionListener(listener); add(deleteButton);

		add(new Label("Y-Axis"));
		JComboBox yCols = new JComboBox(); yCols.addActionListener(listener); add (yCols); 

		add(new Label("X-Axis"));
		JComboBox xCols = new JComboBox(); xCols.addActionListener(listener); add (xCols); 
		/////////

		setTitle("Configurations Setup");
		setSize(350, 100);
		setVisible(true);   
	}

	public ConfigurationsCreator(Configurations configs) {

	}
	
	public static class ConfigurationListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {
		}
	}

}
