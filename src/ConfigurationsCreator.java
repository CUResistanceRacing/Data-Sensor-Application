/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.HashSet;

/**
 * @author sachethhegde
 * 
 */
public class ConfigurationsCreator extends JFrame{
	static JTextField ipAddress, delimiter, colNameChooser, graphNameChooser;
	static JComboBox colList, graphList;
	static JButton addButton, deleteButton, addGraphButton, deleteGraphButton;
	static ConfigurationListener listener;
	static HashSet<String> colListNames, graphListNames;

	// Delete later
	private int length = 2;

	public ConfigurationsCreator() {
		// Must be changed later, looks horrible now
		setLayout(new FlowLayout()); 

		listener = new ConfigurationListener();

		/* Meant for the overall application settings, used for getting data */
		/////////
		add(new JLabel("IP Address:")); 
		ipAddress = new JTextField(40); ipAddress.setEditable(true); ipAddress.addActionListener(listener); add(ipAddress);               

		add(new JLabel("Delimiter:")); 
		delimiter = new JTextField(4); delimiter.setEditable(true); delimiter.addActionListener(listener); add(delimiter);

		add(new JLabel("Visual Elements: " + length)); 

		add(new JLabel("Data Columns (in order):"));
		colList = new JComboBox(); colList.addActionListener(listener); add(colList);

		add(new JLabel("Column Name:"));
		colNameChooser = new JTextField(4); colNameChooser.setEditable(true); colNameChooser.addActionListener(listener); add(colNameChooser);

		addButton = new JButton("Add"); addButton.addActionListener(listener); add(addButton);
		deleteButton = new JButton("Delete"); deleteButton.addActionListener(listener); add(deleteButton);
		/////////

		/* Meant for specific visual display settings, used for getting data. Just for graphs for now. */
		/////////
		add(new JLabel("Graph Selection:"));
		graphList = new JComboBox(); graphList.addActionListener(listener); add (graphList);

		add(new JLabel("Graph Name:"));
		graphNameChooser = new JTextField(40); graphNameChooser.setEditable(true);  graphNameChooser.addActionListener(listener); add(graphNameChooser);  

		JButton addGraphButton = new JButton("Add Graph"); addGraphButton.addActionListener(listener); add(addGraphButton);
		JButton deleteGraphButton = new JButton("Delete Graph");  deleteGraphButton.addActionListener(listener); add(deleteButton);

		add(new JLabel("Y-Axis"));
		JComboBox yCols = new JComboBox(); yCols.addActionListener(listener); add (yCols); 

		add(new JLabel("X-Axis"));
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
			
			if (e.getSource() == addButton) {
				String text = colNameChooser.getText();
				if ( text != "" && !colListNames.contains(text)) {
					colList.addItem(text);
					colListNames.add(text);
				}
			}
			
			else if (e.getSource() == addGraphButton) {
				String text = graphNameChooser.getText();
				if ( text != "" && !graphListNames.contains(text)) {
					graphList.addItem(text);
					graphListNames.add(text);
				}
			}
		}
	}

}
