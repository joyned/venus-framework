package com.keyhead.venus.framework.property.handler;

import java.io.InputStream;
import java.util.Properties;

import com.keyhead.venus.framework.logger.VenusLogger;

public class VenusPropertyHandler {

	private static VenusPropertyHandler INSTANCE = null;

	private static final String RESOURCE_NAME = "config.properties";
	private static Properties properties;

	private VenusPropertyHandler() {
		init();
	}

	private static void init() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = loader.getResourceAsStream(RESOURCE_NAME)) {
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			VenusLogger.error("Failed to load properties. Check if there is a file called " + RESOURCE_NAME
					+ " in src/main/resource", e, VenusPropertyHandler.class);
		}
	}

	public static VenusPropertyHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VenusPropertyHandler();
		}

		return INSTANCE;
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

}
