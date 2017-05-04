package lt.laimis.test.library.ws;

import java.util.Date;

public class WinOSMemory {

	public static long getOSFreeMenory() {

		com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
				.getOperatingSystemMXBean();

		long b = bean.getFreePhysicalMemorySize();

		return b;
	}

	public static long getOSTotalMenory() {

		com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
				.getOperatingSystemMXBean();

		long m = bean.getTotalPhysicalMemorySize();

		return m;
	}

	public static String getOSInfo() {

		Date now = new Date();

		com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
				.getOperatingSystemMXBean();

		long m = bean.getTotalPhysicalMemorySize();
		int a = bean.getAvailableProcessors();

		long b = bean.getFreePhysicalMemorySize();
		double c = bean.getSystemLoadAverage();
		double d = bean.getSystemCpuLoad();

		return now.toString() + " TotalPhysicalMemorySize:" +  BytesConverter.humanReadableByteCount(m, true)
				+ ", FreePhysicalMemorySize:" + BytesConverter.humanReadableByteCount(b, true) + ", AvailableProcessors:"
				+ a + ", SystemLoadAverage:" + c+ ", SystemCpuLoad:" + d;
	}

	public static void main(String[] args) {

		while (true) {

			TimeUtils.timeDelay(1);

			System.out.println(getOSInfo());
		}
	}

}
