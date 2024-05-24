package br.org.serratec.ecommerce.exceptions;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(Object id) {
		super("Entity not found. ID" + id);
	}
	
}
