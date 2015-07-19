/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
//import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

// For JFileChooser
import javax.swing.JFileChooser;
import java.io.File;  

// For LiveGraph

/**
 * @author sachethhegde
 * This class runs the application to start the graphing.
 */
public class GUI {
	static JMenuItem saveItem, saveAsItem, openItem, openDefItem, quitItem;
	static JFileChooser fc;

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
	    
	    frame.setVisible(true);
	    
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
	    
	    saveItem = new JMenuItem("Save"); saveItem.addActionListener(new MenuBarListener());
	    saveAsItem = new JMenuItem("Save As"); saveAsItem.addActionListener(new MenuBarListener());
	    openItem = new JMenuItem("Open"); openItem.addActionListener(new MenuBarListener());
	    openDefItem = new JMenuItem("Open Default"); openDefItem.addActionListener(new MenuBarListener());
	    quitItem = new JMenuItem("Quit"); quitItem.addActionListener(new MenuBarListener());
	    
	    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
	    saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, (java.awt.event.InputEvent.SHIFT_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
	    openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
	    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, (java.awt.event.InputEvent.META_DOWN_MASK | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()))));
	    
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
	public static class MenuBarListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == openItem) {
				
				int returnVal = fc.showOpenDialog(new JFrame());			 
	            
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File configFile = fc.getSelectedFile();
	                
	                System.out.println ("Saving: " + configFile.getName() + "." );
	                startConfigs(configFile);
	            } 				
			} 
			
			else if (e.getSource() == openDefItem) {
				File configFile = new File("def_configFile.graphConfig");
                System.out.println ("Using default configuration file.");
                startConfigs (configFile);
			}
			
			else if (e.getSource() == saveItem) {
				
			}
			
			else if (e.getSource() == saveAsItem) {
				
			}
		}
	}
	
	/**
	 * Uses the config File and opens up the 
	 * @param configFile
	 * @return void
	 */
	public static void startConfigs (File configFile) {
		// Open configs file 
		
		
		// Start DataConnectionManager 
		
		
		// Create  a list of VisualDisplay Objects using the config file
		
		
		// Pass this to the VisualDisplayManager class (inner class), or make a method to determine a location on the screen, only thing for now
		
		
		// Get return statement of location of objects on screen / let the inner method/class do that itself
		
	}
}
