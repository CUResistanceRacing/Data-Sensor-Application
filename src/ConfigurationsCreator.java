/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author sachethhegde
 * 
 */
public class ConfigurationsCreator extends JPanel {
	public static JTextField /*ipAddress,*/ delimiter/*, colNameChooser*/, graphNameChooser, colList, yCols;
	public static JComboBox <String>  graphList;
	static JButton /*addButton, deleteButton,*/ setColumnsButton, addGraphButton, deleteGraphButton, setGraphValues;
	static ConfigurationListener listener;
	static HashSet<String> colListNames, graphListNames;
		
	static HashMap <String, VisualDisplay> visualDisplaySet;
	static HashMap <String, String> graphColMap;
	
	//static String selectedCol = "";
	//static String selectedGraph = "";

	public ConfigurationsCreator() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 

		listener = new ConfigurationListener();
		colListNames = new HashSet<String>();
		graphListNames = new HashSet<String>();
		graphColMap = new HashMap<String, String>();
		
		/* ------------------------------------------------------------------- */
		/* Meant for the overall application settings, used for getting data   */
		/* ------------------------------------------------------------------- */
		
		//add(new JLabel("IP Address:")); 
		//ipAddress = new JTextField(40); ipAddress.setEditable(true); ipAddress.addActionListener(listener); add(ipAddress);               

		add(new JLabel("Delimiter:")); 
		delimiter = new JTextField(4); delimiter.setEditable(true); delimiter.addActionListener(listener); add(delimiter);

		add(new JLabel("Data Columns (enter in order, separated with '+'):"));
		//colList = new JComboBox(); colList.addActionListener(listener); add(colList);
		colList = new JTextField(40); colList.setEditable(true); colList.addActionListener(listener); add(colList);
		
		setColumnsButton = new JButton("Set Columns"); setColumnsButton.addActionListener(listener); add(setColumnsButton);		

		/* ------------------------------------------------------------------------------------------- */
		/* Meant for specific visual display settings, used for getting data. Just for graphs for now. */
		/* ------------------------------------------------------------------------------------------- */
		
		add(new JLabel("Graph Selection:"));
		graphList = new JComboBox(); graphList.addActionListener(listener); add (graphList);

		add(new JLabel("Graph Name:"));
		graphNameChooser = new JTextField(40); graphNameChooser.setEditable(true);  graphNameChooser.addActionListener(listener); add(graphNameChooser);  

		addGraphButton = new JButton("Add Graph"); addGraphButton.addActionListener(listener); add(addGraphButton);
		deleteGraphButton = new JButton("Delete Graph");  deleteGraphButton.addActionListener(listener); add(deleteGraphButton);

		add(new JLabel("Graph Columns (separated with '+'):"));
		yCols = new JTextField(40); yCols.setEditable(true);  yCols.addActionListener(listener); add(yCols);  
		
		setGraphValues = new JButton("Set Graph Values"); setGraphValues.addActionListener(listener); add(setGraphValues);
		visualDisplaySet = new HashMap <String, VisualDisplay> ();
		
		setSize(350, 100);
		setVisible(true);   
	}

	public ConfigurationsCreator(Configurations configs) {
		new ConfigurationsCreator();
	}
	
	public static HashSet<String> tokenizeString (String text, String delimiter) {
		StringTokenizer st = new StringTokenizer(text, delimiter);
		HashSet<String> toReturn = new HashSet<String> ();

		while (st.hasMoreTokens()) {
			toReturn.add(st.nextToken());
		}
		
		return toReturn;
	}
	
	public static class ConfigurationListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == setColumnsButton) {
				String text = colList.getText();
				if ( text != "" && !colListNames.contains(text)) {
					colListNames = tokenizeString(text, "+");
				}
			}
			
			else if (e.getSource() == addGraphButton) {
				String text = graphNameChooser.getText();
				if ( text != "" && !graphListNames.contains(text)) {
					graphList.addItem(text);
					graphListNames.add(text);
				}
			}
			
			/*else if (e.getSource() == colList) {
				selectedCol = (String) colList.getSelectedItem();
				System.out.println(selectedCol);
			}*/
			
			else if (e.getSource() == graphList) {
				String graphName = (String)graphList.getSelectedItem();
				if (graphColMap.containsKey(graphName)) {
					yCols.setText(graphColMap.get(graphName));
				}
				else {
					yCols.setText("");
				}
			}
			
			/*else if (e.getSource() == deleteButton) {
				System.out.println (colList.getSelectedIndex());
				if (selectedCol != "" && colList.getSelectedIndex() != -1) {
					colList.removeItemAt(colList.getSelectedIndex());
				}
			}*/
			
			else if (e.getSource() == deleteGraphButton) {
				String selectedGraph = (String)graphList.getSelectedItem();
				if (selectedGraph != "" && graphList.getSelectedIndex() != -1) {
					graphList.removeItemAt(graphList.getSelectedIndex());
					graphColMap.remove(selectedGraph);
					graphListNames.remove(selectedGraph);
				}
			}
			
			/*else if (e.getSource() == xCols) {
//				if (updateGraphSettings()) {
//					
//				}
			}
			
			else if (e.getSource() == yCols) {
//				if (updateGraphSettings()) {
//					
//				}
			}*/
			
			else if (e.getSource() == setGraphValues) {
				String selectedGraph = (String)graphList.getSelectedItem();
				String graphCols = (String)yCols.getText();
				if (selectedGraph != "" && graphCols != "") {
					ArrayList <String> colNames = new ArrayList <String> ();
					HashSet <String> toInsertColNames = tokenizeString (graphCols, "+");
					for (String value : toInsertColNames) {
						colNames.add(value);
					}
					
					graphColMap.put(selectedGraph, graphCols);
					
					if (visualDisplaySet.containsKey(selectedGraph)) {
						((LiveGrapher)(visualDisplaySet.get(selectedGraph))).colNames = colNames;
					}
					else {
						LiveGrapher lg = new LiveGrapher (selectedGraph, colNames);
						visualDisplaySet.put((String) selectedGraph, lg);
					}
				}
				
				System.out.println (visualDisplaySet);
			}
		}
	}
	
	
	// TODO this is for testing purposes, remove after done testing
	public static void main (String[] args) {
		ConfigurationsCreator a = new ConfigurationsCreator();
		int result = JOptionPane.showConfirmDialog (null, a, "Configurations setup", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		System.out.println (result);
	}

}
