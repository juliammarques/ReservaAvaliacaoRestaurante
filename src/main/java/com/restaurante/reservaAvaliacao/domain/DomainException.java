package com.restaurante.reservaAvaliacao.domain;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String title;
	    private final String detail;
	    private final int status;

	    public DomainException(final String title, final String detail, final int status) {
	        this.title = title;
	        this.detail = detail;
	        this.status = status;
	    }
}
