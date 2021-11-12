package com.keyhead.venus.framework.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VenusLogger {

	private VenusLogger() {
		super();
	}

	private static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	private static Logger getLogger(String clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	/*
	 * START INFO
	 */

	public static void info(String message, Class<?> clazz) {
		getLogger(clazz).info(message);
	}

	public static void info(String message, Throwable throwable, Class<?> clazz) {
		getLogger(clazz).info(message, throwable);
	}

	public static void info(String message, String clazz) {
		getLogger(clazz).info(message);
	}

	public static void info(String message, Throwable throwable, String clazz) {
		getLogger(clazz).info(message, throwable);
	}

	/*
	 * END INFO
	 */

	/*
	 * START DEBUG
	 */

	public static void debug(String message, Class<?> clazz) {
		getLogger(clazz).debug(message);
	}

	public static void debug(String message, Throwable throwable, Class<?> clazz) {
		getLogger(clazz).debug(message, throwable);
	}

	public static void debug(String message, String clazz) {
		getLogger(clazz).debug(message);
	}

	public static void debug(String message, Throwable throwable, String clazz) {
		getLogger(clazz).debug(message, throwable);
	}

	/*
	 * END DEBUG
	 */

	/*
	 * START WARN
	 */

	public static void warn(String message, Class<?> clazz) {
		getLogger(clazz).warn(message);
	}

	public static void warn(String message, Throwable throwable, Class<?> clazz) {
		getLogger(clazz).warn(message, throwable);
	}

	public static void warn(String message, String clazz) {
		getLogger(clazz).warn(message);
	}

	public static void warn(String message, Throwable throwable, String clazz) {
		getLogger(clazz).warn(message, throwable);
	}

	/*
	 * END WARN
	 */

	/*
	 * START ERROR
	 */

	public static void error(String message, Class<?> clazz) {
		getLogger(clazz).error(message);
	}

	public static void error(String message, Throwable throwable, Class<?> clazz) {
		getLogger(clazz).error(message, throwable);
	}

	public static void error(String message, String clazz) {
		getLogger(clazz).error(message);
	}

	public static void error(String message, Throwable throwable, String clazz) {
		getLogger(clazz).error(message, throwable);
	}

	/*
	 * END ERROR
	 */

}
