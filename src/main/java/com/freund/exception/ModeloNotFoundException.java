package com.freund.exception;

public class ModeloNotFoundException extends RuntimeException {

	/**
	 * Serial de clase.
	 */
	private static final long serialVersionUID = -3560838655515585192L;

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
}
