package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRestauranteUseCase {

	@NonNull
	private final RestauranteDomainService service;
	
	public void execute (Input input) {
		service.registraRestaurante(null);
	}
	
	public record Input(String nomeRestaurante) {
		
	}
}
