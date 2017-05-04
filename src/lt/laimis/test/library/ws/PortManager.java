package lt.laimis.test.library.ws;

public class PortManager {

	public static void openPorts(int[] ports){
	
			for (int port : ports) {
				
				new Thread(new RunServer(port)).start();
			}
	}
	
	 static class RunServer implements Runnable {
		
		int port = 0;
		
		public RunServer(int port){
			this.port  = port;
		}
		
	    public void run() {
	    	
	    	System.out.println("Atidarau porta: " + port);
	    	
	    	MySocket.runServer(port);
	    }
	}
}
