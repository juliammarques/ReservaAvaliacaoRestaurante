package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateQuantitativoMesaUseCase {

	@NonNull
	private final QuantitativoMesaDomainService service;
	
	public void execute (Input input) {
		service.atualizaQuantitativoMesa(new QuantitativoMesa(input.seqQuantitativoMesaEntity,input.seqRestaurante, input.tamanhoMesa,input.quantidadeDisponivel));
	}
	
	public record Input(Long seqQuantitativoMesaEntity,Long seqRestaurante,String tamanhoMesa,Long  quantidadeDisponivel) {
	}
}
