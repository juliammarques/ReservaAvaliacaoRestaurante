package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.useCase.CreateReservaMesa;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateReservaMesa;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.RestauranteMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservaVagaController {

	@NonNull
	private final CreateReservaMesa createReservaMesa;
	
	@NonNull
	private final UpdateReservaMesa UpdateReservaMesa;
		
	@NonNull
	private final RestauranteMapper restauranteMapper;
}
