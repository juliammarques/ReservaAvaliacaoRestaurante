package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.RestauranteMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CadastroRestauranteControlle    {
	
	@NonNull
	private final CreateRestauranteUseCase createRestauranteUseCase;
	
	@NonNull
	private final UpdateRestauranteUseCase updateRestauranteUseCase;
	
	@NonNull
	private final GetAllRestauranteUseCase getAllRestauranteUseCase;
	
	@NonNull
	private final RestauranteMapper restauranteMapper;
	
	public ResponseEntity<Void> criarRestaurante(final CreateUpdateRestauranteDTO restaurante){
		createRestauranteUseCase.execute(restauranteMapper.from(restaurante));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualizaRestaurantePeloId(final Long id, final RestauranteDTO Parquimetro){
		createRestauranteUseCase.execute(null);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<RestaurantePaginadoDTO> listaRestaurantes(final Integer page, final Integer size){
		final var restaurantes = getAllRestauranteUseCase.execute(page,size);
		final var restaurantesResponse = restauranteMapper.toDTO(restaurantes);
		return ResponseEntity.ok(restaurantesResponse);
	}
	
	
}
