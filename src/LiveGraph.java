/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.util.HashMap;

import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.dataFile.write.DataStreamWriterFactory;

/**
 * @author sachethhegde
 *
 */
public class LiveGraph extends VisualDisplay {
	static final String USER_DIR = System.getProperty("user.dir");
	DataStreamWriter out;
	
	public LiveGraph (String graphName) {
		out = DataStreamWriterFactory.createDataWriter(USER_DIR, graphName);
		
	}
	
	public void receivedDataSet (HashMap <String,Integer> dataSet) {
		
	}
}
