package lt.laimis.test.library.ws;

import java.io.File;

public class Main {
	
	int defSleep = 1;
	
	private void run(String[] args){
		
		

		if (args != null && args.length > 0 && args[0].equals("library")) {

			if (args.length > 1) {

				defSleep = Integer.valueOf(args[1]);

			}

			PingLibraryWS libraryWS = new PingLibraryWS();
			
			new Thread(libraryWS).start();
			
		} else if (args != null && args.length > 0
				&& args[0].equals("runjboss")) {

			JBossManagerLinux.run();

			System.exit(0);

		} else if (args != null && args.length > 0
				&& args[0].equals("openport")) {

			int[] ports = { 993, 1098, 1099, 1443, 3306, 8443, 8083, 8080, 8093 };

			PortManager.openPorts(ports);

		} else if (args != null && args.length > 0 && args[0].equals("client")) {

			RemoteComunication.runClient();

		} else if (args != null && args.length > 0 && args[0].equals("server")) {

			MySocket.runServer();

			System.exit(0);

		} else if (args != null && args.length > 0 && args[0].equals("kjboss")) {

			JBossManagerLinux.kill();

			System.exit(0);

		} else if (args != null && args.length > 0 && args[0].equals("djboss")) {

			boolean isd = JBossManagerLinux.isDuplicates();

			System.out.println(" Is Jboss Pid duplicated - " + isd);

			System.exit(0);

		} else {

			ConsoleHelper.printCommands("Commands: run, kjboss, djboss, server, client, ports");

			String str = ConsoleHelper.readFromConsole();

			if (str.equals("run")) {


			} else if (str.equals("kjboss")) {

				JBossManagerLinux.kill();

				System.exit(0);

			} else if (str.equals("openport")) {

				int[] ports = { 993, 1098, 1099, 1443, 3306, 8443, 8083, 8080,
						8093 };

				PortManager.openPorts(ports);

			} else if (str.equals("server")) {

				MySocket.runServer();

				System.exit(0);

			} else if (str.equals("ports")) {

				RemoteComunication.openPorts();

			} else if (str.equals("client")) {

				RemoteComunication.runClient();

			} else if (str.equals("djboss")) {

				boolean isd = JBossManagerLinux.isDuplicates();

				System.out.println(" Is Jboss Pid duplicated - " + isd);

				System.exit(0);

			} else {

				System.exit(0);
			}

		}
		
		
	}


	public static void main(String[] args) {
		new Main().run(args);
	}

}
