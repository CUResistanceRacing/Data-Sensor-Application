/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

// For JFileChooser
import javax.swing.JFileChooser;
import java.io.File;   

import java.io.*;

/**
 * @author sachethhegde
 * This class runs the application to start the graphing.
 */
public class GUI {
	static JMenuItem saveItem, saveAsItem, openItem, openDefItem, quitItem;
	static JFileChooser fc;
	
	/**
	 * Constructor
	 */
	public GUI () {
		
	}

	/**
	 * Entry point main method
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "My Application");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
	    JFrame frame = new JFrame("Data Sensor Application");

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(160, 144);
	    frame.setLocationRelativeTo(null);
	    frame.setIgnoreRepaint(true);

	    // Setting up the JMenuBar
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
	    
	    JLabel text = new JLabel ("Settings and Elements");

	    frame.setJMenuBar(menuBar);
	    
	    frame.add(text);
	    frame.setVisible(true);
	    
	    fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	}
	
	/**
	 * Static inner class MenuBarListener is used to listen to events of the default
	 * MenuBar that is used for the program.
	 */
	static class MenuBarListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == openItem) {
				int returnVal = fc.showOpenDialog(new JFrame());

				 
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                //This is where a real application would open the file.
	                System.out.println ("Opening: " + file.getName() + "." );
	            } else {
	                System.out.println("Open command cancelled by user.");
	            }
				
			} else if (e.getSource() == openDefItem) {
				/* Open configurations here */
			}
			
		}
	}
}
