package com.xinterium.jspi.test;

import de.lohndirekt.print.IppPrintServiceLookup;
import de.lohndirekt.print.Messages;
import de.lohndirekt.print.attribute.auth.RequestingUserPassword;
import junit.framework.TestCase;
import org.junit.Ignore;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.RequestingUserName;
import java.util.Locale;

/**
 * @author bpusch
 *
 */
@Ignore
public class IppPrintServiceLookupTest extends TestCase {

	/**
	 * Constructor for IppPrintServiceLookupTest.
	 * @param name
	 */
	public IppPrintServiceLookupTest(String name) {
		super(name);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		//setting cups properties
		System.getProperties().setProperty(IppPrintServiceLookup.URI_KEY, Messages.getString("cups.uri")); //$NON-NLS-1$
		System.getProperties().setProperty(IppPrintServiceLookup.USERNAME_KEY, Messages.getString("cups.username")); //$NON-NLS-1$
		System.getProperties().setProperty(IppPrintServiceLookup.PASSWORD_KEY, Messages.getString("cups.password")); //$NON-NLS-1$
		System.getProperties().setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog"); //$NON-NLS-1$
		System.getProperties().setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.getProperties().setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
		System.getProperties().setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
	}

//	public void testGetServices(){
//		PrintService[] services = new IppPrintServiceLookup().getPrintServices(DocFlavor.INPUT_STREAM.POSTSCRIPT ,null);
//		assertTrue(services.length>0);
//		
//		//IppPrintServiceLookup must not return any pageable enabled services
//		services = new IppPrintServiceLookup().getPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
//		assertEquals(0,services.length);
//	}
	
	
	
	public void testGetServicesAuthenticated(){
		RequestingUserName user = new RequestingUserName(Messages.getString("cups.username"), Locale.GERMANY);
		RequestingUserPassword pass = new RequestingUserPassword(Messages.getString("cups.password"), Locale.GERMANY);

		AttributeSet set = new HashAttributeSet();
		set.add(user);
		set.add(pass);

		PrintService[] services = new IppPrintServiceLookup().getPrintServices(DocFlavor.INPUT_STREAM.POSTSCRIPT ,null);
		assertTrue(services.length>0);
	}
}
