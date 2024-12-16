package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateQuantativoMesaUseCase {

	@NonNull
	private final QuantitativoMesaDomainService service;
	
	public void execute (Input input) {
		service.registraQuantitativoInicial(new QuantitativoMesa(input.seqRestaurante, input.tamanhoMesa,input.quantidadeDisponivel));
	}
	
	public record Input(Long seqRestaurante,String tamanhoMesa,Long  quantidadeDisponivel) {
	}
	
}
