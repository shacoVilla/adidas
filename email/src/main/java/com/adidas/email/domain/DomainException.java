package com.adidas.email.domain;

public class DomainException extends RuntimeException {
	
	private static final long serialVersionUID = -8285921913571059472L;

	DomainException(final String message) {
        super(message);
    }
}
