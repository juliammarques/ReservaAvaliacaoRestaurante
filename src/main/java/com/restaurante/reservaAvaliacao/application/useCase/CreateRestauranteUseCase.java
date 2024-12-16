package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRestauranteUseCase {

	@NonNull
	private final RestauranteDomainService service;
	
	public void execute (Input input) {
		service.registraRestaurante(new Restaurante(input.nomeRestaurante,input.tipoCozinha, input.endereco));
	}
	
	public record Input(String nomeRestaurante, String tipoCozinha, String endereco) {
	}
	

    public record Output(String nomeRestaurante, String tipoCozinha, String endereco) {

        public static Output from(Restaurante restaurante) {
            return new Output(
            		restaurante.getNomeRestaurante(),
            		restaurante.getTipoCozinha(),
            		restaurante.getEndereco());
        }
    }
}
