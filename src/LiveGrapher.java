/**
 * Resistance Racing - Data Sensor Application
 */

// Import Statements
import java.util.HashMap;
import java.util.ArrayList;


//import org.LiveGraph.dataFile.write.DataStreamWriter;
//import org.LiveGraph.dataFile.write.DataStreamWriterFactory;
//import org.LiveGraph.settings.*;
import org.LiveGraph.LiveGraph;
import org.LiveGraph.dataFile.write.*;
import org.LiveGraph.settings.*;

/**
 * @author sachethhegde
 * Represents a LiveGraph object
 */
public class LiveGrapher extends VisualDisplay {
	static final String USER_DIR = System.getProperty("user.dir");
	String graphName, lgdfsFileName;
	ArrayList<String> colNames;
	DataStreamWriter out;
	DataFileSettings dfs;


	public LiveGrapher (String graphName, ArrayList<String> colNames_list) {
		// Save and get settings
		dfs = new DataFileSettings();
		dfs.setDataFile( graphName + ".csv");
		dfs.setUpdateFrequency(100);
		
		this.lgdfsFileName = graphName + ".lgdfs";
		
		dfs.save(lgdfsFileName);

		// Create DataStreamWriter, which will write the data to a corresponding file
		out = DataStreamWriterFactory.createDataWriter(USER_DIR, graphName);

		// Set a values separator:
		out.setSeparator(";");

		// Add a file description line:
		out.writeFileInfo("Graph: " + graphName);

		// Set-up the data series:
		for (String name: colNames_list) {
			out.addDataSeries(name);
		}

		this.graphName = graphName;
		colNames = colNames_list;

	}

	@Override
	public void receivedDataSet (HashMap <String,Integer> dataSet) {

		// Set datavalues
		for (String name: colNames) {
			out.setDataValue(dataSet.get(name));
		}

		// Write dataset to memory
		out.writeDataSet();

		// Check for IOErrors:      
		if (out.hadIOException()) {
			out.getIOException().printStackTrace();
			out.resetIOException();
		}
	}

	@Override
	void display() {
		LiveGraph app = LiveGraph.application();
		app.exec(new String[] {"-dfs", lgdfsFileName});
	}
	
	@Override
	void finished() {
		out.close();
	}
}
