package lt.laimis.test.library.ws;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
 
public class JMXExample {
 
    public static void main(String[] args) throws Exception {
        //Get a connection to the JBoss AS MBean server on localhost
        String host = "213.159.63.124";
        int port = 9999;  // management-native port
        String urlString =
            System.getProperty("jmx.service.url","service:jmx://" + host + ":" + port);
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
        MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
 
        //Invoke on the JBoss AS MBean server
        int count = connection.getMBeanCount();
        System.out.println(count);
        jmxConnector.close();
    }
}
