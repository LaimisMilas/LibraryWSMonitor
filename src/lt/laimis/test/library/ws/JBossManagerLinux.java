package lt.laimis.test.library.ws;

import java.util.ArrayList;
import java.util.List;

public class JBossManagerLinux {

	public static boolean isDuplicates() {

		System.out.println("Run, isDuplicates");

		boolean result = false;

		List<String> taskPids = getTaskPids();

		System.out.println("return taskPids.size() - " + taskPids.size());

		if (taskPids.size() > 1) {

			result = true;

		}

		return result;
	}

	public static String getLogs(String filePath) {

		String result = FileReadWriteHelper.readFileAsString(filePath);

		return result;
	}

	public static void kill() {
		
		System.out.println("JBossManagerLinux.kill()");

		List<String> taskPids = getTaskPids();
		
		System.out.println("Pids size: " + taskPids.size());

		for (String pid : taskPids) {
			
			System.out.println("Pid will by killed: " + pid);

			String[] cmd = { "/bin/bash", "-c", "kill -9 " + pid };

			UnixSystemCommandRunner.sempleLinuxBashCommand(cmd);
		}
	}

	public static void run() {

		String[] cmd = { "/bin/bash", "-c", "/root/jboss-as-7.1.1.Final/bin/standalone.sh &"};

		List<String> result = UnixSystemCommandRunner.runOnLinuxBashCommand(cmd);

		for (String line : result) {

			System.out.println(line);
		}
	}

	// Kai OS linux
	public static List<String> getTaskPids() {
		// reikia kad grazintu Task Pid numeri
		// gali buti kad yra pasileide keli procesai
		// reikia isanalizuoti loga ir nustatyti Task Pids
		List<String> taskPids = new ArrayList<String>();

		List<String> result = new ArrayList<String>();

		String[] cmd = { "/bin/bash", "-c", "ps ax | grep jboss" };

		result = UnixSystemCommandRunner.runOnLinuxBashCommand(cmd);

		System.out.println("runSystemCommandReturnList.size() - "
				+ result.size());

		for (String line : result) {

			System.out.println("Print Out, line - " + line);

			if (line.contains("java -D[Standalone]")) {

				String[] tmp = line.trim().split(" ");

				taskPids.add(tmp[0]);
			}

		}

		for (String pid : taskPids) {

			System.out.println("pid - " + pid);
		}

		// 4734 pts/1 S 0:00 /bin/sh
		// /root/jboss-as-7.1.1.Final/bin/standalone.sh
		// 4777 pts/1 Sl 0:45 java -D[Standalone] -server -XX:+UseCompressedOops
		// -XX:+TieredCompilation -Xms64m -Xmx512m -XX:MaxPermSize=256m
		// -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true
		// -Dsun.rmi.dgc.client.gcInterval=3600000
		// -Dsun.rmi.dgc.server.gcInterval=3600000
		// -Djboss.modules.system.pkgs=org.jboss.byteman
		// -Djava.awt.headless=true -Djboss.server.default.config=standalone.xml
		// -Dorg.jboss.boot.log.file=/root/jboss-as-7.1.1.Final/standalone/log/boot.log
		// -Dlogging.configuration=file:/root/jboss-as-7.1.1.Final/standalone/configuration/logging.properties
		// -jar /root/jboss-as-7.1.1.Final/jboss-modules.jar -mp
		// /root/jboss-as-7.1.1.Final/modules -jaxpmodule
		// javax.xml.jaxp-provider org.jboss.as.standalone
		// -Djboss.home.dir=/root/jboss-as-7.1.1.Final

		return taskPids;
	}

}
