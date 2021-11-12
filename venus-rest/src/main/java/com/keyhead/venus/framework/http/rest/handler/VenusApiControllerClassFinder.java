package com.keyhead.venus.framework.http.rest.handler;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.keyhead.venus.framework.http.rest.annotation.ApiController;

public class VenusApiControllerClassFinder {

	private VenusApiControllerClassFinder() {
		super();
	}

	public static List<Class<?>> getClasses(String packageName) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			String path = packageName;
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<>();
			while (resources.hasMoreElements()) {
				URL resource = (URL) resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			ArrayList<Class<?>> classes = new ArrayList<>();
			for (File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}
			return classes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed while loading Controller classes", e);
		}
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				if (StringUtils.isBlank(packageName)) {
					classes.addAll(findClasses(file, file.getName()));
				} else {
					classes.addAll(findClasses(file, packageName + "." + file.getName()));
				}
			} else if (file.getName().endsWith(".class")) {
				Class<?> clazz = Class
						.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				if (clazz.isAnnotationPresent(ApiController.class)) {
					classes.add(clazz);
				}
			}
		}
		return classes;
	}
}
