package lt.laimis.test.library.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UnixSystemCommandRunner {
	
	public static boolean printOut = false;

	public static void sempleLinuxBashCommand(String[] cmd) {
		
		try {

			Runtime.getRuntime().exec(cmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static List<String> runOnLinuxBashCommand(String[] cmd) {

		List<String> result = new ArrayList<String>();

		try {

			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();

			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {

				System.out.println("exec.readLine - " + s);

				result.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	// return List<String>
	public static List<String> runSystemCommandReturnList(String command) {

		System.out.println("runSystemCommandReturnList.command - " + command);

		List<String> stResponse = new ArrayList<String>();

		try {

			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();

			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {

				System.out.println("exec.readLine - " + s);

				stResponse.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (printOut) {

		}

		return stResponse;
	}
	
}
