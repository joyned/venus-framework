package com.keyhead.venus.framework.jpa.repository;

import java.util.List;

public interface VenusRepository<ID, T> {

	public List<T> findAll();

	public T findById(ID id);

	public T save(T entity);

	public T update(T entity);

	public T deleteById(ID id);
}
