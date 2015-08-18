/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.util.HashMap;

/**
 * @author sachethhegde
 * Superclass for many data display types, such as Graphs (supported right now) and meters
 */
public abstract class VisualDisplay {
	int xSize, ySize; // Default x and y size on the screen
	int xPos, yPos; // Default x and y position on the screen
	
	String dataReadID; // The ID to be used for the specific data type which will be read 
	
	/**
	 * Constructor
	 */
	public VisualDisplay () {
		
	}
	
	/**
	 * Abstract method that represents the method called when a dataset is received. This method
	 * must be overwritten with the appropriate behavior included to make data appear visually as necessary.
	 * @param data HashMap of each of the data sets present, mapping the data-type to the value
	 */
	abstract void receivedDataSet (HashMap<String, Integer> data);
	
	/**
	 * Abstract method that must be called in the beginning when the application is started to
	 * actually display the graph on the screen
	 */
	abstract void display();
	
	/** 
	 * Abstract method that will be called after the graphing and data
	 * sending is finished
	 */
	abstract void finished();

}
