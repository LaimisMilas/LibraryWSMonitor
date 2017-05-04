package lt.laimis.test.library.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PingLibraryWS implements Runnable {
	
	public String libraryServer = "213.159.63.124";
	public String mobileiQServer = "212.122.94.219";
	// String failureServer = "212.100.100.100";
	public String pageUrlAddress = "http://213.159.63.124/do/bookprofile/list";

	public boolean validatePing(List<String> strings) {

		boolean result = false;

		List<String> containsStrings = new ArrayList<String>();
		containsStrings.add("from");

		result = StringUtils.containsAny(strings, containsStrings);

		return result;
	}

	public boolean validateOraclePing(List<String> logs) {

		boolean result = false;

		List<String> containsStrings = new ArrayList<String>();
		containsStrings.add("Attempting to contact");

		result = StringUtils.containsAny(logs, containsStrings);

		return result;
	}

	public  boolean validatePing(String log) {

		boolean result = false;

		List<String> containsStrings = new ArrayList<String>();
		containsStrings.add("from");

		result = StringUtils.containsAny(log, containsStrings);

		return result;
	}

	public boolean validateWebPage(String htmlString) {

		boolean result = false;

		List<String> containsStrings = new ArrayList<String>();
		containsStrings.add("<title>..::PENKI.LT::Biblioteka</title>");
		containsStrings.add("<strong>Duomenø bazës</strong>");
		containsStrings.add("<br>Autorius: Justinas Þilinskas <br>");

		result = StringUtils.containsAny(htmlString, containsStrings);

		return result;
	}

	public void conc(String object, String valid, boolean value) {

		String newLine = System.getProperty("line.separator");

		System.out.println(newLine + object + valid + value);
	}
	
	public void pingLoop(int sleepTime) {

		String isPing = "is ping ";
		String isvalid = "page is valid ";

		while (true) {

			WinSystemCommandRunner.printOut = false;

			// String newLine = System.getProperty("line.separator");

			System.out.println("");
			System.out.println(" " + new Date());

			boolean pingValid = validatePing(RemoteComunication
					.pingServer(libraryServer));

			if (pingValid) {

				conc(" LibraryServer     ", isPing, pingValid);

				// System.out.println(newLine + " LibraryServer    " + isPing
				// + pingValid);
			} else {

				conc(" --LibraryServer    ", isPing, pingValid);

				// System.out.println(newLine + " --LibraryServer   " + isPing
				// + pingValid);
			}

			boolean pingValid1 = validatePing(RemoteComunication
					.pingServer(mobileiQServer));

			if (pingValid1) {

				conc(" MobileiQServer    ", isPing, pingValid1);

				// System.out.println(newLine + " MobileiQServer   " + isPing
				// + pingValid1);
			} else {

				conc(" --MobileiQServer   ", isPing, pingValid1);

				// System.out.println(newLine + " --MobileiQServer  " + isPing
				// + pingValid1);
			}

			// System.out.println(newLine + " failureServer is ping - " +
			// validatePing(pingServer(failureServer)));

			boolean pingValid2 = validateOraclePing(PingOracle
					.pingOracleServer(mobileiQServer));

			if (pingValid2) {

				conc(" OracleServer      ", isPing, pingValid2);

				// System.out.println(newLine + " OracleServer      " + isPing
				// + pingValid2);
			} else {

				conc(" OracleServer      ", isPing, pingValid2);

				// System.out.println(newLine + " --OracleServer    " + isPing
				// + pingValid2);
			}

			String htmlPage = WebPageGrep.grep(pageUrlAddress);

			boolean pageValid = validateWebPage(htmlPage);

			if (pageValid) {

				conc(" LibraryWS   ", isvalid, pageValid);

				// System.out.println(newLine + " LibraryWS   " + isvalid
				// + pageValid);
			} else {

				conc(" LibraryWS   ", isvalid, pageValid);

				// System.out.println(newLine + " --LibraryWS " + isvalid
				// + pageValid);
			}

			if (sleepTime == 0) {
				sleepTime = 1;
			}

			try {

				TimeUnit.MINUTES.sleep(sleepTime);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void run() {
		
	}
}
