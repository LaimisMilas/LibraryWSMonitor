package lt.laimis.test.library.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHelper {

	public static String readFromConsole() {

		System.out.print("Enter Command: ");

		String s = "";

		InputStreamReader inputStreamReader = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(inputStreamReader);

		try {

			// if(br.ready()){

			s = br.readLine();
			// }

		} catch (Exception nfe) {

			nfe.printStackTrace();

		} finally {

			/*
			 * try {
			 * 
			 * br.close(); inputStreamReader.close();
			 * 
			 * } catch (IOException e) { e.printStackTrace(); }
			 */

		}

		return s;
	}

	public static void printCommands(String text) {
		System.out.println(text);
	}

}
