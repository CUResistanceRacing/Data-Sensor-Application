/**
 * Resistance Racing - Data Sensor Application
 */

/**
 * @author sachethhegde
 * Manages getting the data from the corresponding address, using Datagrams
 */
public abstract class DataConnectionManager {
	public boolean done = false;

	
	/**
	 * Constructor used to initialize a dataConnectionManager instance
	 * @param ipAddress - IP Address to get on the network connection
	 */
	public DataConnectionManager () {
	}
	
	/**
	 * Abstract method that represents the method called when it is time to request for a packet
	 * to be sent over the network. It must be overwritten with the appropriate behavior to get the appropriate packets
	 * through the network.
	 */
	abstract String requestAndReceivePacket (); 
	
	/**
	 * Abstract method that represents the method that is run when the application is first started. It
	 * is in charge of getting the data via network appropriately, and calling receivedPacket() after
	 * getting it's packet information
	 */
	abstract String run ();
}
