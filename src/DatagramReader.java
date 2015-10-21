import java.net.*;
import java.io.*;

/**
 * Resistance Racing - Data Sensor Application
 */

/**
 * @author sachethhegde
 *
 */
public class DatagramReader extends DataConnectionManager {

	public DatagramReader () {
		super();
	}

	@Override
	String run() {
		return requestAndReceivePacket();
	}

	@Override
	String requestAndReceivePacket() {
		// TODO Auto-generated method stub
		byte[] buf = new byte[256];
		
		try {
			String localName = java.net.InetAddress.getLocalHost().getHostName();

			InetAddress address = InetAddress.getByName(localName);
			DatagramSocket socket = new DatagramSocket(11111, InetAddress.getByName("0.0.0.0"));
		       socket.setBroadcast(true);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
			socket.send(packet);
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			socket.close();
			return( new String(packet.getData(), 0, packet.getLength()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
