package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRestauranteUseCase {

	@NonNull
	private final RestauranteDomainService service;
	
	 public void execute(Input input) {
	 service.atualizaRestaurante(
	                Restaurante.with(
	                	input.id(),	
	                    input.nomeRestaurante(), 
	                    input.endereco(), 
	                    input.tipoCozinha()
	                )
	            );
	    }

	    public record Input(Long id, String nomeRestaurante,String endereco,String tipoCozinha) {

	    }
}
