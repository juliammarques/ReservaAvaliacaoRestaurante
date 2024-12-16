package com.restaurante.reservaAvaliacao.domain.exception;

import com.restaurante.reservaAvaliacao.domain.DomainException;

public class RestauranteNaoExiste extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String RESTAURANTE_ID_NAO_ENCONTRADO = "Parquimetro com o id %d não encontrado";
    private static final String RESTAURANTE_NAO_ENCONTRADO = "Parquimetro não encontrado";

    public RestauranteNaoExiste(final Long seqRestaurante) {
        super(RESTAURANTE_NAO_ENCONTRADO, String.format(RESTAURANTE_ID_NAO_ENCONTRADO, seqRestaurante), HTTP_STATUS_NOT_FOUND);
    }
}
