package lt.laimis.test.library.ws;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

//import com.bs2.atmiq.WSCommon.classes.proto.UserCredentials;
//import com.bs2.atmiq.modules.payments.invoice.common.interfaces.IPaymentsInvoiceService;

public class EJBViaLookUp {
	
	
	public static final String WSDL_VERSION = "1.0";
	public static String SERVER_IP = "127.0.0.1";
	//public static UserCredentials credentials;
	public static String userLocale = "ru";
	public static boolean SSL_ENABLED = false;
	public static String CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
	public static String PROVIDER_URL_PREFIX = "jnp://";
	public static String PROVIDER_URL_POSTFIX = ":1099";
	public static String JMS_CONNECTION_FACTORY = "XAConnectionFactory";
	public static boolean HTTP_CHUNKED = true;
	
	public static void lookup() {
/*	
 	eikia kada servisas aprasytas taip:
 	
	import javax.ejb.Local;
	import javax.ejb.Remote;
	import javax.ejb.Stateless;
	import org.jboss.annotation.ejb.LocalBinding;
	import com.bs2.atmiq.WSCommon.classes.proto.Header;
	import com.bs2.atmiq.modules.payments.invoice.common.interfaces.IPaymentsInvoiceService;
	import com.bs2.atmiq.modules.payments.invoice.common.interfaces.IPaymentsInvoiceServiceLocal;
	@Stateless(name = "PaymentsInvoiceService", mappedName = "PaymentsInvoiceService")
	@Remote({ IPaymentsInvoiceService.class })
	@Local({ IPaymentsInvoiceServiceLocal.class })
	@LocalBinding (jndiBinding = "Payments/Invoice/local")
	public class PaymentsInvoiceServiceImpl implements IPaymentsInvoiceService, IPaymentsInvoiceServiceLocal
	
	import java.rmi.Remote;
	import com.bs2.atmiq.WSCommon.classes.proto.Header;
	public interface IPaymentsInvoiceService extends Remote	
		
	import java.rmi.Remote;
	import com.bs2.atmiq.WSCommon.classes.proto.Header;
	public interface IPaymentsInvoiceServiceLocal extends Remote
	
	*/
		
		Properties jndiProps = new Properties();

		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
		jndiProps.put(Context.PROVIDER_URL, PROVIDER_URL_PREFIX + SERVER_IP
				+ PROVIDER_URL_POSTFIX);
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		
		String target = "PaymentInvoiceEAR/PaymentsInvoiceService";

		try {
			
			Context ctx = new InitialContext(jndiProps);

			NamingEnumeration namingEnum = ctx.list(target);
			
			while (namingEnum.hasMore()) {
				System.out.println(namingEnum.next());
			}
			
			Object namingContext = ctx.lookup(target + "/remote");
			
			//IPaymentsInvoiceService ifd = (IPaymentsInvoiceService) namingContext;
			//ifd.testWS(null, "");
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
