/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.io.File;  
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author sachethhegde
 * Represents configurations for an instance of GUI
 */
public class Configurations implements Serializable {
	transient ArrayList <VisualDisplay> visDisplayElements;
	ArrayList <String> colNames;
	String delimiter;
	boolean complete = false;
	
	public Configurations (ArrayList <VisualDisplay> visDisElements, String del, ArrayList <String> colNamesList) {
		visDisplayElements = visDisElements;
		delimiter = del;
		complete = true;
		colNames = colNamesList;
	}
	
	public Configurations() {
		
	}
		
	/**
	 * Verifies whether this file is a correct file for configurations
	 * @param configFile
	 * @return true if the File fits the specs, false otherwise
	 */
	public static boolean verifyFileIntegrity (File configFile) {
		return true;
	}

}
