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
import org.LiveGraph.settings.GraphSettings.XAxisType;

/**
 * @author sachethhegde
 * Represents a LiveGraph object
 */
public class LiveGrapher extends VisualDisplay {
	static final String USER_DIR = System.getProperty("user.dir");
	String graphName, lgdfsFileName, lggsFileName;
	ArrayList<String> colNames;
	DataStreamWriter out;
	DataFileSettings dfs;
	GraphSettings gfs;
	static final int updateFreq = 100; // In Hz
	int timeCounter;


	public LiveGrapher (String graphName, ArrayList<String> colNames_list) {
		// Save and get settings
		dfs = new DataFileSettings();
		dfs.setDataFile( graphName + ".lgdat");
		dfs.setUpdateFrequency(updateFreq);
		dfs.setShowOnlyTailData(true);
		
		this.lgdfsFileName = graphName + ".lgdfs";
		
		dfs.save(lgdfsFileName);
		
		gfs = new GraphSettings();
		gfs.setXAxisSeriesIndex(0);
		gfs.setXAxisType(XAxisType.XAxis_DataValSimple);;
		
		this.lggsFileName = graphName + ".lggs";
		gfs.save(lggsFileName);

		// Create DataStreamWriter, which will write the data to a corresponding file
		out = DataStreamWriterFactory.createDataWriter(USER_DIR, graphName);

		// Set a values separator:
		out.setSeparator(";");

		// Add a file description line:
		out.writeFileInfo("Graph: " + graphName);

		// Set-up the data series:
		out.addDataSeries("time");
		for (String name: colNames_list) {
			out.addDataSeries(name);
		}

		this.graphName = graphName;
		colNames = colNames_list;
		timeCounter = 0;

	}

	@Override
	public void receivedDataSet (HashMap <String,Double> dataSet) {
		out.setDataValue(timeCounter *  (1/((float)updateFreq)));
		//System.out.println (timeCounter *  (((float)updateFreq)/1000) + "   " + timeCounter);
		// Set datavalues
		for (String name: colNames) {
			out.setDataValue(dataSet.get(name));
		}

		// Write dataset to memory
		out.writeDataSet();
		timeCounter++;


		// Check for IOErrors:      
		if (out.hadIOException()) {
			out.getIOException().printStackTrace();
			out.resetIOException();
		}
		try { out.wait(100); } catch (Exception e) {};
	}

	@Override
	void display() {
		LiveGraph app = LiveGraph.application();
		app.exec(new String[] {"-dfs", lgdfsFileName, "-gs", lggsFileName});
		app.setDisplayDataFileSettingsWindow(false);		
		app.setDisplayGraphSettingsWindow(false);
	}
	
	@Override
	void finished() {
		out.close();
	}
}
