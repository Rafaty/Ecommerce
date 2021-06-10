package com.serratec.ecommerceapi.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IControllerCrud<T> {

	ResponseEntity<List<T>> select();

	ResponseEntity<T> selectById(Long id);

	ResponseEntity<T> insert(T obj);

	ResponseEntity<T> update(Long id, T newObj);

	void delete(Long id);

}
