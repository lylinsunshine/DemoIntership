package com.shopping.dao;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface GenericDAO<T, K extends Serializable> {
	
	/**
	 * Save T
	 * 
	 * @param T Entity
	 */
	void insertOrUpdate(T entity);

	/**
	 * Delete T
	 * 
	 * @param T
	 */
	void delete(T entity);

	Page<T> page(int pageNumber, int pageSize, Map<String, Object> map);

}
