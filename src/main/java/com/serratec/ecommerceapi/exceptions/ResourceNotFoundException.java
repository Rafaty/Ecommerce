package com.serratec.ecommerceapi.exceptions;

public class ResourceNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = -5482594407568983071L;

	public ResourceNotFoundException(Long id) {
		super("Recurso solicitado com a id "+ id +" não pode ser encontrado!");
	}
}