package com.keyhead.venus.framework.api.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.keyhead.venus.framework.api.annotation.ApiEndpoint;

public class VenusApiHandler {

	private static List<Class<?>> controllerClasses;

	public static void init() {
		controllerClasses = VenusApiControllerClassFinder.getClasses("");
	}

	public static Object executeMethodByInformedUrl(String passedUrl, String httpMethod) {
		for (Class<?> clazz : controllerClasses) {
			Method[] methods = clazz.getDeclaredMethods();

			for (Method method : methods) {
				if (passedUrl.equals(method.getAnnotation(ApiEndpoint.class).value())
						&& httpMethod.equals(method.getAnnotation(ApiEndpoint.class).method())) {
					try {
						return method.invoke(clazz.getConstructor().newInstance());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException | InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
		throw new RuntimeException("Resource '" + passedUrl + "' with HttpMethod '" + httpMethod + "' not found");
	}

	public static List<Class<?>> getControllerClasses() {
		return controllerClasses;
	}
}
