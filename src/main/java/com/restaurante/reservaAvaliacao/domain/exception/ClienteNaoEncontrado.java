package com.restaurante.reservaAvaliacao.domain.exception;

import com.restaurante.reservaAvaliacao.domain.DomainException;

public class ClienteNaoEncontrado extends DomainException{

	private static final long serialVersionUID = 1L;
	private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String CLIENTE_NAO_ENCONTRADO_RESTAURANTE = "Não foi encontrado registro anterior desse cliente para o restaurante solicitado";
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente sem registro";

    public ClienteNaoEncontrado() {
        super(CLIENTE_NAO_ENCONTRADO, CLIENTE_NAO_ENCONTRADO_RESTAURANTE, HTTP_STATUS_NOT_FOUND);
    }
}
