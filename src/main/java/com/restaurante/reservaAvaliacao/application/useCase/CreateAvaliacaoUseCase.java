package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAvaliacaoUseCase {

	@NonNull
	private final AvaliacaoRestauranteDomainService service;
	
	public void execute (Input input) {
		service.adicionaAvaliacaoRestaurante(new AvaliacaoRestaurante(LocalDate.now(), input.notaAvaliacao, input.comentario, input.documentoCliente, input.seqRestaurante));
	}
	
	public record Input(LocalDate dataAvaliacao,Long notaAvaliacao,String comentario,String documentoCliente,Long seqRestaurante) {
	}
	
}
