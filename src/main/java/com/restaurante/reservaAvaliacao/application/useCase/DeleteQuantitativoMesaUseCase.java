package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteQuantitativoMesaUseCase {

	@NonNull
	private final QuantitativoMesaDomainService service;
	
	public void execute (final Long idQuantitativo) {
		service.deleteQuantitativo(idQuantitativo);
	}
}
