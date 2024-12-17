package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.dto.AvaliacaoRestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.dto.CreateAvaliacaoRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateAvaliacaoUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllAvaliacaoRestauranteUseCase;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.AvaliacaoRestauranteMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AvaliacaoRestauranteController {

	@NonNull
	private final CreateAvaliacaoUseCase createAvaliacaoUseCase;
	
	@NonNull
	private final GetAllAvaliacaoRestauranteUseCase getAllAvaliacaoRestauranteUseCase;
	
	@NonNull
	private final AvaliacaoRestauranteMapper quantitativoMesaMapper;
	
	public ResponseEntity<Void> criarAvaliacao(final CreateAvaliacaoRestauranteDTO avaliacao){
		createAvaliacaoUseCase.execute(quantitativoMesaMapper.from(avaliacao));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<AvaliacaoRestaurantePaginadoDTO> listaAvaliacoes(final Integer page, final Integer size, Long seqRestaurante){
		final var avaliacoes = getAllAvaliacaoRestauranteUseCase.execute(page,size,seqRestaurante);
		final var avaliacoesResponse = quantitativoMesaMapper.toDTO(avaliacoes);
		return ResponseEntity.ok(avaliacoesResponse);
	}
}
