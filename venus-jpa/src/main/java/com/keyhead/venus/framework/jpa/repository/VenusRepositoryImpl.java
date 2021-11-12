package com.keyhead.venus.framework.jpa.repository;

import java.lang.reflect.Field;
import java.util.List;

import com.keyhead.venus.framework.jpa.annotation.TableProperty;

public class VenusRepositoryImpl<ID, T> implements VenusRepository<ID, T> {

	private final Class<T> type;

	public VenusRepositoryImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	public List<T> findAll() {

		buildQueryWithReflection();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T deleteById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	private void buildQueryWithReflection() {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT ");

		for (Field field : type.getClass().getDeclaredFields()) {
			field.getDeclaredAnnotation(TableProperty.class);
		}
	}

}
