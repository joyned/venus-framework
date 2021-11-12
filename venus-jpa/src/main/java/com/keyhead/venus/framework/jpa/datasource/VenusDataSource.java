package com.keyhead.venus.framework.jpa.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.keyhead.venus.framework.jpa.exception.InvalidDataSourcePropertyException;
import com.keyhead.venus.framework.logger.VenusLogger;
import com.keyhead.venus.framework.property.handler.VenusPropertyHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class VenusDataSource {

	private static VenusDataSource INSTANCE = null;

	private static final String PROPERTY_URL = "venus.datasource.url";
	private static final String PROPERTY_USERNAME = "venus.datasource.username";
	private static final String PROPERTY_PASSWORD = "venus.datasource.password";

	private static String dataSourceUrl;
	private static String dataSourceUsername;
	private static String dataSourcePassword;

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	private VenusDataSource() {
	}

	static {
		try {
			init();
			checkIfPropertiesNotBlankElseThrows();

			config.setJdbcUrl(dataSourceUrl);
			config.setUsername(dataSourceUsername);
			config.setPassword(dataSourcePassword);

			ds = new HikariDataSource(config);
		} catch (Exception e) {
			VenusLogger.error("Error while creating HikariDataSource", e, VenusDataSource.class);
		}
	}

	private static void init() {
		dataSourceUrl = VenusPropertyHandler.getInstance().getProperty(PROPERTY_URL);
		dataSourceUsername = VenusPropertyHandler.getInstance().getProperty(PROPERTY_USERNAME);
		dataSourcePassword = VenusPropertyHandler.getInstance().getProperty(PROPERTY_PASSWORD);
	}

	public static VenusDataSource getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VenusDataSource();
		}

		return INSTANCE;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	private static void checkIfPropertiesNotBlankElseThrows() {
		if (StringUtils.isBlank(dataSourceUrl)) {
			throw new InvalidDataSourcePropertyException(
					"Data source property '" + PROPERTY_URL + "' is empty or null.");
		}

		if (StringUtils.isBlank(dataSourceUsername)) {
			throw new InvalidDataSourcePropertyException(
					"Data source property '" + PROPERTY_USERNAME + "' is empty or null.");
		}

		if (StringUtils.isBlank(dataSourcePassword)) {
			throw new InvalidDataSourcePropertyException(
					"Data source property '" + PROPERTY_PASSWORD + "' is empty or null.");
		}
	}

}
