package lt.laimis.test.library.ws;

import java.util.List;

public class StringUtils {

	boolean printOut = false;

	public static boolean containsAll(String inString,
			List<String> containsStrings) {

		int matchCounter = 0;

		boolean result = false;

		if (containsStrings != null) {

			for (String str : containsStrings) {

				if (inString.contains(str)) {

					matchCounter++;

				}
			}
		}

		if (matchCounter == containsStrings.size()) {

			result = true;

		}

		return result;
	}

	public static boolean containsAny(String inString,
			List<String> containsStrings) {

		boolean result = false;

		if (containsStrings != null) {

			for (String str : containsStrings) {

				if (inString.contains(str)) {
					result = true;
				}
			}
		}

		return result;
	}

	public static boolean containsAny(List<String> strings,
			List<String> containsStrings) {

		boolean result = false;

		if (containsStrings != null) {
			
			if(strings != null && strings.size() > 0){
				
				for (String line : strings) {
					
					for (String str : containsStrings) {

						if (line.contains(str)) {
							
							result = true;
						}
					}
				}
			}
		}

		return result;
	}

}
