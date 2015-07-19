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
	 * 
	 * @param data HashMap of each of the data sets present, mapping the data-type to the value
	 */
	abstract void receivedDataSet (HashMap<String, Integer> data);

}
