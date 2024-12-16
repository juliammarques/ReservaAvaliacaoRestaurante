package com.restaurante.reservaAvaliacao.domain.exception;

import com.restaurante.reservaAvaliacao.domain.DomainException;

public class QuantitativoMesaNaoEncontrado extends DomainException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HTTP_STATUS_NOT_FOUND = 404;
	    private static final String QUANTITATIVO_MESA_ID_NAO_ENCONTRADO = "Quantitativo Mesa com o id %d não encontrado";
	    private static final String QUANTITATIVO_MESA_NAO_ENCONTRADO = "Quantitativo Mesa não encontrado";

	    public QuantitativoMesaNaoEncontrado(final Long seqQuantitativo) {
	        super(QUANTITATIVO_MESA_NAO_ENCONTRADO, String.format(QUANTITATIVO_MESA_ID_NAO_ENCONTRADO, seqQuantitativo), HTTP_STATUS_NOT_FOUND);
	    }
}
