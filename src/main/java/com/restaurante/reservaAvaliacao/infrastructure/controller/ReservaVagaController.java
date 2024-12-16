package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.useCase.CreateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetReservaMesaByIdUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.RestauranteMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservaVagaController {

	@NonNull
	private final CreateReservaMesaUseCase createReservaMesa;
	
	@NonNull
	private final UpdateReservaMesaUseCase UpdateReservaMesa;
	
	@NonNull
	private final UpdateStatusReservaMesaUseCase UpdateStatusReservaMesa;
	@NonNull
	private final GetAllReservaMesaUseCase getAllReservaMesa;
	@NonNull
	private final GetReservaMesaByIdUseCase getReservaMesaById;
		
	@NonNull
	private final RestauranteMapper restauranteMapper;
	
	
}
