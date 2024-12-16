package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllRestauranteUseCase {

    @NonNull
	private final RestauranteDomainService restauranteDomainService;
	
    public Pagination<Output> execute (final int page, final int size){
    	return restauranteDomainService.buscaRestaurante(page,size).map(Output::from);
    }
    
    public record Output(String nomeRestaurante,String endereco,String tipoCozinha) {

    	public static Output from(final Restaurante restaurante) {
    	return new Output(
    			restaurante.getNomeRestaurante(),
    			restaurante.getEndereco(),
    			restaurante.getTipoCozinha()
       );
}
}
}
