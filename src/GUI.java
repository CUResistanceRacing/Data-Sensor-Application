/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements

// Basic GUI requirements
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
// import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

// For JFileChooser
import javax.swing.JFileChooser;

import java.io.File; 



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//// For serialization
//import java.io.FileInputStream;
//import java.io.ObjectInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sachethhegde
 * This class runs the application to start the graphing.
 */
public class GUI {
	static JMenuItem newItem, saveItem, saveAsItem, openItem, openDefItem, quitItem;
	static JFileChooser fc;
	static String configFile;

	/**
	 * Entry point main method
	 * @param args
	 */
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});   
	}

	/** 
	 * Calls submethods to create the GUI and sets up configurations
	 */
	public static void createAndShowGUI() {		
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "My Application");
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		JFrame frame = new JFrame("Data Sensor Application");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(160, 144);
		frame.setLocationRelativeTo(null);
		frame.setIgnoreRepaint(true);

		JMenuBar menuBar = createMenuBar();
		frame.setJMenuBar(menuBar);
		
		String address = "";
		try {
			address = (java.net.InetAddress.getLocalHost()).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel ipAddLabel = new JLabel ();
		ipAddLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		ipAddLabel.setText("Local Address: '" + address + "'");
		ipAddLabel.setVisible(true);

		frame.add(ipAddLabel);
		frame.pack();
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	}

	/**
	 * Creates the JMenuBar to be used in the application
	 * @return JMenuBar object to be used in the application
	 */
	public static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		newItem = new JMenuItem("New"); newItem.addActionListener(new MenuBarListener());
		saveItem = new JMenuItem("Save"); saveItem.addActionListener(new MenuBarListener());
		saveAsItem = new JMenuItem("Save As"); saveAsItem.addActionListener(new MenuBarListener());
		openItem = new JMenuItem("Open"); openItem.addActionListener(new MenuBarListener());
		openDefItem = new JMenuItem("Open Default"); openDefItem.addActionListener(new MenuBarListener());
		quitItem = new JMenuItem("Quit"); quitItem.addActionListener(new MenuBarListener());

		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, (java.awt.event.InputEvent.SHIFT_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));

		fileMenu.add(newItem);
		fileMenu.add(saveItem); fileMenu.add(saveAsItem); fileMenu.add(openItem);
		fileMenu.add(openDefItem); fileMenu.addSeparator(); fileMenu.add(quitItem);

		JMenu elementMenu = new JMenu("Elements");
		JMenu helpMenu = new JMenu("Help");

		menuBar.add(fileMenu);
		menuBar.add(elementMenu);
		menuBar.add(helpMenu);

		return (menuBar);
	}

	/**
	 * Static inner class MenuBarListener is used to listen to events of the default
	 * MenuBar that is used for the program.
	 */
	public static class MenuBarListener implements ActionListener  {

		public void actionPerformed (ActionEvent e) {

			if (e.getSource() == newItem) {
				Configurations config = createConfigs();
				if (config != null) { 
					startConfigs (config);
				}
			} 

			else if (e.getSource() == openItem) {

				int returnVal = fc.showOpenDialog(new JFrame());			 

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File configFile = fc.getSelectedFile();

					System.out.println ("Saving: " + configFile.getName() + "." );
					//startConfigs(configFile);
				} 				
			} 

			else if (e.getSource() == openDefItem) {
				File configFile = new File("def_configFile.graphConfig");
				System.out.println ("Using default configuration file.");
				//startConfigs (configFile);
			}

			// Requires saving the configuration file
			else if (e.getSource() == saveItem) {

			}

			else if (e.getSource() == saveAsItem) {
				// Open file-chooser dialog and choose a place to put the new config file

			}
		}
	}

	/**
	 * Uses the config object and applies it for creating the live data configurations
	 * @param configFile
	 * @return void
	 */
	public static void startConfigs (Configurations config) {

		// Start DataConnectionManager
		DataConnectionManager dcm = new DatagramReader();

		// Create  a list of VisualDisplay Objects using the config file
		ArrayList <VisualDisplay> visDisplayElements = config.visDisplayElements;

		// Pass this to the VisualDisplayManager class (inner class), or make a method to determine a location on the screen, only thing for now
		// The VisualDisplayManager will also send the corresponding information to each object on the screen
		// Get return statement of location of objects on screen / let the inner method/class do that itself
		
		for (VisualDisplay vd : visDisplayElements) {
	        vd.display();
	        System.out.println("Done executing.");
		}
		
		// TODO - figure out which function everything will actually be run
		// Create a start button after this
		// After start button is pressed, the main stuff can finally run
		// Make DataConnectionManager getting data periodically a thread -> gets data, feeds it to the graphs, etc.
			
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(new Runnable() {
		    public void run() {
		    	while (!dcm.done) {
		    		System.out.println("yee");
					String packet = dcm.run();
					System.out.println (packet);
					HashMap<String, Double> dataMap = initializeMap (packet, config.delimiter, config.colNames);
					for (VisualDisplay element : visDisplayElements) {
			    		System.out.println("yee");
						element.receivedDataSet(dataMap);
					}
				}
		    }
		});
	}

	/**
	 * Creates new configurations settings using a new window that can be opened separately
	 * @return Configurations file representing the configurations
	 */
	public static Configurations createConfigs () {
		ConfigurationsCreator configCreator = new ConfigurationsCreator();
		int result;
			result = JOptionPane.showConfirmDialog (null, configCreator, "Configurations setup", JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
			System.out.println (result);
		if (result != 0) {
			return null;
		}
		System.out.println ("Delete later");
//		try { 
//			FileOutputStream fout = new FileOutputStream("configgg.lgconfig");
//			ObjectOutputStream oos = new ObjectOutputStream(fout);   
//			oos.writeObject(configCreator);
//			oos.close();
//			System.out.println("Done");
//		} catch (Exception ee) {
//			ee.printStackTrace();
//			System.out.println ("Could not write to file.");
//		}
//		
		
		
		ArrayList <VisualDisplay> visDisplayList = new ArrayList <VisualDisplay> ((configCreator.visualDisplaySet).values());
		ArrayList <String> colList = new ArrayList <String> (configCreator.colListNames);

		return new Configurations (visDisplayList, configCreator.delimiter.getText(), colList );
	}
	
	public static HashMap<String, Double> initializeMap (String text, String delimiter, ArrayList<String> colNames) {
		StringTokenizer st = new StringTokenizer(text, delimiter);
		HashMap<String, Double> toReturn = new HashMap<String, Double> ();

		for (String column : colNames) {
			toReturn.put(column, Double.valueOf(st.nextToken()));
		}
		return toReturn;
	}
}
