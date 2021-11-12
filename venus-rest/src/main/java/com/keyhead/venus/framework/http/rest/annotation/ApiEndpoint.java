package com.keyhead.venus.framework.http.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.keyhead.venus.framework.http.rest.HttpMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiEndpoint {

	public String value();

	public HttpMethod method() default HttpMethod.GET;

}
