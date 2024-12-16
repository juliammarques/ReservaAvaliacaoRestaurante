package com.restaurante.reservaAvaliacao.domain.exception;

import com.restaurante.reservaAvaliacao.domain.DomainException;

public class ReservaFechada extends DomainException{

	private static final long serialVersionUID = 1L;
	private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String RESERVA_ID_FEHADA = "A reserva com o id %d ja foi encerrada pela restaurante";
    private static final String RESERVA_FECHADA = "Reserva fechada pelo restaurante";

    public ReservaFechada(final Long seqReserva) {
        super(RESERVA_FECHADA, String.format(RESERVA_ID_FEHADA, seqReserva), HTTP_STATUS_NOT_FOUND);
    }
}
