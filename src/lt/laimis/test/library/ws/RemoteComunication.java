package lt.laimis.test.library.ws;

import java.util.List;

public class RemoteComunication {
	
	public static List<String> pingServer(String serverId) {

		List<String> result = null;

		String osName = System.getProperty("os.name").toLowerCase();

		if ((osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName
				.indexOf("aix") > 0)) {

			String[] cmd = { "/bin/bash", "-c", "ping -c 5 " + serverId };

			result = UnixSystemCommandRunner.runOnLinuxBashCommand(cmd);

		} else if (osName.indexOf("win") >= 0) {

			result = UnixSystemCommandRunner.runSystemCommandReturnList("ping "
					+ serverId);

		}

		return result;
	}

	public static void openPorts() {
		
		System.out.println("Test is port open");
		System.out .println("Enter hostname and port number to test:");
		
		String str = ConsoleHelper.readFromConsole();

		//int[] ports = { 993, 1098, 1099, 1443, 3306, 8443, 8083, 8080, 8093 };

		String host = str.trim().split(":")[0];
		int port = Integer.valueOf(str.trim().split(":")[1]);

		String response = MySocket.runClient(host, port, "");

		System.out.println(response);
		
		openPorts();
	}
	
	public static void runClient() {

		// String host = "localhost";
		String host = "213.159.63.124";

		System.out.println("");
		System.out
				.println("Execute on server: runjboss, kjboss, djboss, logs, stop");

		String str = ConsoleHelper.readFromConsole();

		if (str.contains("runjboss")) {

			String runjboss = "runjboss";

			MySocket.runClient(host, 4058, runjboss);

		} else if (str.contains("kjboss")) {

			String kjboss = "kjboss";

			MySocket.runClient(host, 4058, kjboss);

		} else if (str.contains("djboss")) {

			String djboss = "djboss";

			MySocket.runClient(host, 4058, djboss);

		} else if (str.contains("logs")) {

			String readLogFile = "readfile,/root/jboss-as-7.1.1.Final/standalone/log/server.log";
			// String readLogFile =
			// "readfile,E:\\Service.Desk.IQ\\jboss\\server\\default\\log\\server.log";
			String storeToFile = "C:\\Users\\laimonas.milasius\\Desktop\\server.log";

			String logs = MySocket.runClient(host, 4058, readLogFile);

			if (logs != null) {

				FileReadWriteHelper.writeFile(storeToFile, logs);
			}

		}

		runClient();
	}
	
}
