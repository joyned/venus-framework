package com.keyhead.venus.framework;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.keyhead.venus.framework.api.handler.VenusApiHandler;
import com.keyhead.venus.framework.servlet.VenusApiServlet;

public class VenusFramework {

	public static Class<?> mainClazz;

	public static void start(Class<?> clazz) {
		try {

			System.out.println("=======================================\r\n"
					+ " _   _ _____ _   _ _   _ _____ \r\n"
					+ "| | | |  ___| \\ | | | | /  ___|\r\n"
					+ "| | | | |__ |  \\| | | | \\ `--. \r\n"
					+ "| | | |  __|| . ` | | | |`--. \\\r\n"
					+ "\\ \\_/ / |___| |\\  | |_| /\\__/ /\r\n"
					+ " \\___/\\____/\\_| \\_/\\___/\\____/ \r\n"
					+ "                               \r\n"
					+ "=======================================\r\n"
					+ "Venus Framework 1.0v                               ");

			mainClazz = clazz;

			Tomcat tomcat = new Tomcat();
			tomcat.setBaseDir("temp");
			tomcat.setPort(8080);

			String contextPath = "/";
			String docBase = new File(".").getAbsolutePath();

			Context context = tomcat.addContext(contextPath, docBase);

			String servletName = "VenusApiServlet";
			String urlPattern = "/";

			tomcat.addServlet(contextPath, servletName, new VenusApiServlet());

			context.addServletMappingDecoded(urlPattern, servletName);

			VenusApiHandler.init();

			tomcat.start();
			tomcat.getServer().await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
