package com.keyhead.venus.framework.http.rest.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyhead.venus.framework.http.rest.HttpMethod;
import com.keyhead.venus.framework.http.rest.annotation.ApiEndpoint;
import com.keyhead.venus.framework.http.rest.exceptions.BadRequestException;

public class VenusApiHandler {

	private static List<Class<?>> controllerClasses;

	public static void init() {
		controllerClasses = VenusApiControllerClassFinder.getClasses("");
	}

	@SuppressWarnings("unchecked")
	public static Object executeMethodByInformedUrl(String passedUrl, HttpMethod httpMethod, Object requestBody) {
		for (Class<?> clazz : controllerClasses) {
			Method[] methods = clazz.getDeclaredMethods();

			for (Method method : methods) {
				if (passedUrl.equals(method.getAnnotation(ApiEndpoint.class).value())
						&& httpMethod.equals(method.getAnnotation(ApiEndpoint.class).method())) {
					try {
						if (StringUtils.isNotBlank(requestBody.toString())) {
							ObjectMapper mapper = new ObjectMapper();

							Map<Object, Object> map = mapper.readValue(requestBody.toString(), Map.class);

							Object object = new ObjectMapper().convertValue(map, method.getParameters()[0].getType());

							return method.invoke(clazz.getConstructor().newInstance(), object);
						}

						return method.invoke(clazz.getConstructor().newInstance());
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
							| SecurityException | InstantiationException | JsonProcessingException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						throw new BadRequestException("Failed to convert request body to required method body", e);
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
