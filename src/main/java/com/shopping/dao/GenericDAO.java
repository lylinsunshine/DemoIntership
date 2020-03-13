package com.shopping.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

	Page<T> page(int pageNumber, int pageSize);

}
