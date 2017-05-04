package lt.laimis.test.library.ws;

public class LoggingUtils {

	public static void print(StackTraceElement[] stack) {
	
		System.out.println("------ Laimonas ------ " +  stack[2]);

	}

	public String stackTraceToString(Throwable e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
