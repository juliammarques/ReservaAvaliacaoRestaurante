package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuantitativoMesaDomainService {

	@NonNull
	private final QuantitativoMesaGateway gateway;
}
