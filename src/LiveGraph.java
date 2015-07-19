/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.util.HashMap;
import java.util.ArrayList;

import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.dataFile.write.DataStreamWriterFactory;
import org.LiveGraph.settings.*;

/**
 * @author sachethhegde
 * Represents a LiveGraph object
 */
public class LiveGraph extends VisualDisplay {
	static final String USER_DIR = System.getProperty("user.dir");
	ArrayList<String> colNames;
	DataStreamWriter out;
	DataFileSettings dfs;


	public LiveGraph (String graphName, ArrayList<String> colNames_list) {
		// Save and get settings
		dfs = new DataFileSettings();
		dfs.setDataFile("/" + graphName + "/data.csv");
		dfs.setUpdateFrequency(100);
		dfs.save("/" + graphName + "/startup.lgdfs");

		// Create DataStreamWriter, which will write the data to a corresponding file
		out = DataStreamWriterFactory.createDataWriter(USER_DIR, graphName);

		// Set a values separator:
		out.setSeparator(";");

		// Add a file description line:
		out.writeFileInfo("LiveGraph demo file.");

		// Set-up the data series:
		for (String name: colNames_list) {
			out.addDataSeries(name);
		}

		colNames = colNames_list;

	}

	public void receivedDataSet (HashMap <String,Integer> dataSet) {

		// Set datavalues
		for (String name: colNames) {
			out.setDataValue(dataSet.get(name));
		}

		// Write dataset to disk
		out.writeDataSet();
	}
}
