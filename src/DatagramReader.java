/**
 * Resistance Racing - Data Sensor Application
 */

/**
 * @author sachethhegde
 *
 */
public class DatagramReader extends DataConnectionManager {

	public DatagramReader (String ip) {
		super(ip);
	}

	/* (non-Javadoc)
	 * @see DataConnectionManager#receivedPacket(java.lang.String)
	 */
	@Override
	void receivedPacket(String packet) {
		// TODO Auto-generated method stub

	}

	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	String requestPacket() {
		// TODO Auto-generated method stub
		return null;
	}

}
