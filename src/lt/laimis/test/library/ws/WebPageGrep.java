package lt.laimis.test.library.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageGrep {

	public static String grep(String address){
		
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    StringBuffer stringBuffer = new StringBuffer();

	    try {
	        url = new URL(address);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	        	
	        	stringBuffer.append(line);
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	        	ioe.printStackTrace();
	        }
	    }
	    
	    return stringBuffer.toString();
	}
	
	
}
