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
	ArrayList <VisualDisplay> visDisplayElements;
	ArrayList <String> colNames;
	String ipAddress;
	String delimiter;
	boolean complete = false;
	
	public Configurations (ArrayList <VisualDisplay> visDisElements, String ipAdd, String del) {
		visDisplayElements = visDisElements;
		ipAddress = ipAdd;
		delimiter = del;
		complete = true;
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
