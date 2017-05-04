package lt.laimis.test.library.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpTestServer {

	public static void runServer(String appname, int port) {

		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext(appname, new MyHandler());
			server.setExecutor(null); // creates a default executor
			server.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static class MyHandler implements HttpHandler {

		public void handle(HttpExchange t) throws IOException {

			String response = "OS memory: "  + WinOSMemory.getOSInfo();
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();

		}
	}

	public static void main(String[] args) throws Exception {

		if(args.length > 1){
			
			String appName = args[0];
			int port = Integer.valueOf(args[1]);
			
			runServer(appName, port);
			
		}else {
			
			runServer("/test",8045);
			
		}
		
		

	}

}