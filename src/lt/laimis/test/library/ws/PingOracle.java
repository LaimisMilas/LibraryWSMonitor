package lt.laimis.test.library.ws;

import java.util.ArrayList;
import java.util.List;

public class PingOracle {

	// pingina oracla DB
	public static List<String> pingOracleServer(String serverId) {

		List<String> result = null;

		String osName = System.getProperty("os.name").toLowerCase();

		if ((osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName
				.indexOf("aix") > 0)) {

			//String[] cmd = { "/bin/bash", "-c", "tnsping " + serverId };

			//result = SystemCommanRunner.runOnLinuxBashCommand(cmd);

		} else if (osName.indexOf("win") >= 0) {

			result = UnixSystemCommandRunner.runSystemCommandReturnList("tnsping "
					+ serverId);

		}

		return result;
	}

	public static boolean validatePing(String log) {

		boolean result = false;

		List<String> containsStrings = new ArrayList<String>();
		containsStrings.add("Attempting to contact");

		result = StringUtils.containsAny(log, containsStrings);

		return result;
	}

}
