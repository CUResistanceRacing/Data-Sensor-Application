/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.HashSet;

/**
 * @author sachethhegde
 * 
 */
public class ConfigurationsCreator extends JFrame{
	public static JTextField ipAddress, delimiter, colNameChooser, graphNameChooser;
	public static JComboBox colList, graphList, xCols, yCols;
	static JButton addButton, deleteButton, addGraphButton, deleteGraphButton;
	static ConfigurationListener listener;
	static Configurations config;
	static HashSet<String> colListNames, graphListNames;
	
	private String yes = "yes";
	
	static String selectedCol = "";
	static String selectedGraph = "";

	public ConfigurationsCreator() {
		// Must be changed later, looks horrible now
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); 

		listener = new ConfigurationListener();
		colListNames = new HashSet<String>();
		graphListNames = new HashSet<String>();

		/* Meant for the overall application settings, used for getting data */
		/////////
		add(new JLabel("IP Address:")); 
		ipAddress = new JTextField(40); ipAddress.setEditable(true); ipAddress.addActionListener(listener); add(ipAddress);               

		add(new JLabel("Delimiter:")); 
		delimiter = new JTextField(4); delimiter.setEditable(true); delimiter.addActionListener(listener); add(delimiter);

		add(new JLabel("Visual Elements: " + graphListNames.size())); 

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

		addGraphButton = new JButton("Add Graph"); addGraphButton.addActionListener(listener); add(addGraphButton);
		deleteGraphButton = new JButton("Delete Graph");  deleteGraphButton.addActionListener(listener); add(deleteGraphButton);

		add(new JLabel("Y-Axis"));
		yCols = new JComboBox(); yCols.addActionListener(listener); add (yCols); 

		add(new JLabel("X-Axis"));
		xCols = new JComboBox(); xCols.addActionListener(listener); add (xCols);
		
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
					yCols.addItem((Object)text);
					xCols.addItem((Object)text);
				}
			}
			
			else if (e.getSource() == addGraphButton) {
				String text = graphNameChooser.getText();
				if ( text != "" && !graphListNames.contains(text)) {
					graphList.addItem(text);
					graphListNames.add(text);
				}
			}
			
			else if (e.getSource() == colList) {
				selectedCol = (String) colList.getSelectedItem();
				System.out.println(selectedCol);
			}
			
			else if (e.getSource() == graphList) {
				selectedGraph = (String) graphList.getSelectedItem();
				System.out.println(selectedGraph);
			}
			
			else if (e.getSource() == deleteButton) {
				System.out.println (colList.getSelectedIndex());
				if (selectedCol != "" && colList.getSelectedIndex() != -1) {
					colList.removeItemAt(colList.getSelectedIndex());
				}
			}
			
			else if (e.getSource() == deleteGraphButton) {
				if (selectedGraph != "" && graphList.getSelectedIndex() != -1) {
					graphList.removeItemAt(graphList.getSelectedIndex());
				}
			}
		}
	}
	
	// TODO this is for testing purposes, remove after done testing
	public static void main (String[] args) {
		new ConfigurationsCreator();
	}

}
