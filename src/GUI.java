/**
 * Used to initialize the graphing application to be run.
 */

// Import statements
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author sachethhegde
 * This class runs the application to start the graphing.
 */
public class GUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "MyApplication");
		
	    JFrame frame = new JFrame("Gabby");
//
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setSize(160, 144);
//	    frame.setLocationRelativeTo(null);
//	    frame.setIgnoreRepaint(true);

	    // Setting up the JMenuBar
	    JMenuBar menuBar = new JMenuBar();
	    
	    JMenu fileMenu = new JMenu("File");
	    
	    JMenuItem saveItem = new JMenuItem("Save");
	    JMenuItem saveAsItem = new JMenuItem("Save As");
	    JMenuItem openItem = new JMenuItem("Open");
	    JMenuItem openDefItem = new JMenuItem("Open Default");
	    JMenuItem quitItem = new JMenuItem("Quit");
	    
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
	    System.out.println("heldfdfdfdfdfdlo");

	    // Populating the menu bar code goes here
	    frame.setJMenuBar(menuBar);
	    frame.setVisible(true);
	}
}
