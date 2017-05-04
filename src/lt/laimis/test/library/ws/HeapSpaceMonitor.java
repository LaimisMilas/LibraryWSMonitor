package lt.laimis.test.library.ws;

import java.io.File;

public class HeapSpaceMonitor {
	
	private static void printHeapValues(){

		  /* Total number of processors or cores available to the JVM */
		  System.out.println("Available processors (cores): " + 
		  Runtime.getRuntime().availableProcessors());

		  /* Total amount of free memory available to the JVM */
		  System.out.println("Heap Size: " + 
				  BytesConverter.humanReadableByteCount(Runtime.getRuntime().freeMemory(), true));

		  /* This will return Long.MAX_VALUE if there is no preset limit */
		  long maxMemory = Runtime.getRuntime().maxMemory();
		  /* Maximum amount of memory the JVM will attempt to use */
		  System.out.println("Heap Max Size: " + 
		  (maxMemory == Long.MAX_VALUE ? "no limit" : BytesConverter.humanReadableByteCount(maxMemory, true)));

		  /* Total memory currently in use by the JVM */
		  System.out.println("Heap Free Size: " + 
				  BytesConverter.humanReadableByteCount(Runtime.getRuntime().totalMemory(), true));
		  	  
		  /* Get a list of all filesystem roots on this system */
		  File[] roots = File.listRoots();

		  /* For each filesystem root, print some info
		  for (File root : roots) {
		    System.out.println("File system root: " + root.getAbsolutePath());
		    System.out.println("Total space (bytes): " + root.getTotalSpace());
		    System.out.println("Free space (bytes): " + root.getFreeSpace());
		    System.out.println("Usable space (bytes): " + root.getUsableSpace());
		  }
		  */
		  
	}
	
	public static void main(String[] args) {
		
		printHeapValues();
		
	}
	
}
