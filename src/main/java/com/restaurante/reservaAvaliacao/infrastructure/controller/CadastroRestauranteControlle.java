package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateQuantitativoMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateQuantativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.CreateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.DeleteQuantitativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateQuantitativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.QuantitativoMesaMapper;
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
	private final CreateQuantativoMesaUseCase createQuantativoMesaUseCase;
	
	@NonNull
	private final UpdateQuantitativoMesaUseCase updateQuantitativoMesaUseCase;
	
	@NonNull
	private final DeleteQuantitativoMesaUseCase deleteQuantitativoMesaUseCase;
		
	@NonNull
	private final RestauranteMapper restauranteMapper;
	
	@NonNull
	private final QuantitativoMesaMapper quantitativoMesaMapper;
	
	public ResponseEntity<Void> criarRestaurante(final CreateUpdateRestauranteDTO restaurante){
		createRestauranteUseCase.execute(restauranteMapper.from(restaurante));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualizaRestaurantePeloId(final Long id, final CreateUpdateRestauranteDTO restaurante){
		updateRestauranteUseCase.execute(restauranteMapper.from(id, restaurante));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<RestaurantePaginadoDTO> listaRestaurantes(final Integer page, final Integer size){
		final var restaurantes = getAllRestauranteUseCase.execute(page,size);
		final var restaurantesResponse = restauranteMapper.toDTO(restaurantes);
		return ResponseEntity.ok(restaurantesResponse);
	}
	
	
	public ResponseEntity<Void> criarQuantitativoMesaDisponivel(final CreateUpdateQuantitativoMesaDTO quantitativo){
		createQuantativoMesaUseCase.execute(quantitativoMesaMapper.from(quantitativo));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualizaQuantitativoPeloId(final Long id, final CreateUpdateQuantitativoMesaDTO quantitativo){
		updateQuantitativoMesaUseCase.execute(quantitativoMesaMapper.from(id, quantitativo));
		return ResponseEntity.noContent().build();
	}
	
	public  ResponseEntity<Void> excluiQuantitativoMesa(final Long id){
		deleteQuantitativoMesaUseCase.execute(id);
		return ResponseEntity.noContent().build();
	} 
	
}
