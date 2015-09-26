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

	public DatagramReader (String ip) {
		super(ip);
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
			InetAddress address = InetAddress.getByName(ip);
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
			socket.send(packet);
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			return( new String(packet.getData(), 0, packet.getLength()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
