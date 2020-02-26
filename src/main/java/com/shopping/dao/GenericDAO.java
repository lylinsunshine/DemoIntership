package com.shopping.dao;

import java.io.Serializable;

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


}
