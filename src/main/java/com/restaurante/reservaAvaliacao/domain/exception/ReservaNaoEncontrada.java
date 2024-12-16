package com.restaurante.reservaAvaliacao.domain.exception;

import com.restaurante.reservaAvaliacao.domain.DomainException;

public class ReservaNaoEncontrada extends DomainException{

	
		private static final long serialVersionUID = 1L;
		private static final int HTTP_STATUS_NOT_FOUND = 404;
	    private static final String RESERVA_ID_NAO_ENCONTRADO = "Restaurante com o id %d não encontrado";
	    private static final String RESERVA_NAO_ENCONTRADO = "restaurante não encontrado";

	    public ReservaNaoEncontrada(final Long seqReserva) {
	        super(RESERVA_NAO_ENCONTRADO, String.format(RESERVA_ID_NAO_ENCONTRADO, seqReserva), HTTP_STATUS_NOT_FOUND);
	    }
}
