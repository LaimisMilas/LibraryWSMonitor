package lt.laimis.test.library.ws;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PortScaner {


	public static String runClient(String host, int port) {

		System.out.println("MySocket.runClient()");

		Socket requestSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		String response = null;

		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket(host, port);
			
			if(requestSocket != null){
				
				System.out.println("");
				System.out.println("host:" + host  + ", port:" + port);
				System.out.println("isBound:" + requestSocket.isBound());
				System.out.println("isClosed:" + requestSocket.isClosed());
				System.out.println("isConnected:" + requestSocket.isConnected());
				System.out.println("isInputShutdown:" + requestSocket.isInputShutdown());
				System.out.println("isOutputShutdown:" + requestSocket.isOutputShutdown());
				
			}
			
			

		} catch (Exception ex) {
			
			System.out.println("Connection refused on, port:" + port);
			
		}finally {
			
			try {
	
				requestSocket.close();

			} catch (Exception classNot) {
				
			}
			
		}

		return response;
	}
	
	
	public static void main(String[] args) {
		
		//Klientu butinai, reikalingi portai 993, 1098, 1099, 1443, 3306, 8443, 8083, 8080, 8093
		//Autoricacijai reikia SSL: 8443
		//Servisams pasiekti: 8080
		
		String host = "localhost";
		
		int[] ports = {993, 1098, 1099, 1443, 3306, 8443, 8083, 8080, 8093};
		
		for (int port : ports) {
			PortScaner.runClient(host, port);
		}	
	}
	
}
