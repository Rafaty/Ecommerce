package com.serratec.ecommerceapi.interfaces;

import java.util.List;
public interface IServiceCrud<T> {

	List<T> list();

	T create(T obj);

	T updateById(Long id, T newObj);
	
	T findById(Long id);

	void deleteById(Long id);
	
	void updateObj(T oldObj, T newObj);
	
	
	
}
