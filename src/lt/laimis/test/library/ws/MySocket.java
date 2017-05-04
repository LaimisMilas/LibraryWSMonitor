package lt.laimis.test.library.ws;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket {

	ServerSocket server;
	Socket client;

	static String message;

	public static String runClient(String host, int port, String text) {

		System.out.println("MySocket.runClient()");

		Socket requestSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		String response = null;

		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket(host, port);
			System.out.println("Connected to " + host + " in port " + port);
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server
			try {

				sendMessage(text, out);

				response = (String) in.readObject();

				in.close();
				out.close();
				requestSocket.close();

			} catch (ClassNotFoundException classNot) {

				classNot.printStackTrace();
			}

		} catch (UnknownHostException unknownHost) {

			unknownHost.printStackTrace();

		} catch (IOException ioException) {

			ioException.printStackTrace();

		} finally {

			try {

				in.close();
				out.close();
				requestSocket.close();

			} catch (Exception classNot) {
				classNot.printStackTrace();
			}

		}

		return response;
	}

	public static void runServer(int port) {

		Socket connection = null;
		ServerSocket providerSocket = null;
		ObjectOutputStream out = null;

		try {

			// 1. creating a server socket
			providerSocket = new ServerSocket(port, 10);

			// 2. Wait for connection
			connection = providerSocket.accept();

			if (connection != null) {

				// 3. get Input and Output streams
				out = new ObjectOutputStream(connection.getOutputStream());
				out.flush();

				sendMessage("Jûsø konfiguracija teisinga, portas: " + port
						+ " yra pasiekemas.", out);

			}

			out.close();
			connection.close();
			providerSocket.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public static void runServer() {

		System.out.println("MySocket.runServer()");

		ServerSocket providerSocket = null;
		Socket connection = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		try {

			// 1. creating a server socket
			providerSocket = new ServerSocket(4058, 10);
			// 2. Wait for connection
			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());
			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();

			in = new ObjectInputStream(connection.getInputStream());

			try {

				String messageFromClient = (String) in.readObject();

				System.out.println(messageFromClient);

				// command,arg0,arg1

				if (messageFromClient == null
						|| messageFromClient.length() == 0) {
					messageFromClient = "Nieko nera";
				}

				String[] tmp = messageFromClient.split(",");

				if (messageFromClient.contains("readfile")) {

					System.out.println("read file: " + tmp[1]);

					sendMessage(FileReadWriteHelper.readFileAsString(tmp[1]),
							out);

				}

				if (messageFromClient.contains("kjboss")) {

					System.out.println("trying to kill jboss");

					JBossManagerLinux.kill();

				}

				if (messageFromClient.contains("djboss")) {

					boolean isd = JBossManagerLinux.isDuplicates();

					System.out.println(" Is Jboss Pid duplicated - " + isd);

				}

				if (messageFromClient.contains("runjboss")) {

					System.out.println("trying to run jboss");

					JBossManagerLinux.run();

				}

				if (messageFromClient.contains("stop")) {
					System.exit(0);
				}

			} catch (ClassNotFoundException classNot) {
				System.err.println("data received in unknown format");
			}

			in.close();
			out.close();
			providerSocket.close();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		runServer();
	}

	static void sendMessage(String msg, ObjectOutputStream out) {

		System.out.println("MySocket.sendMessage()");

		try {

			out.writeObject(msg);
			out.flush();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
