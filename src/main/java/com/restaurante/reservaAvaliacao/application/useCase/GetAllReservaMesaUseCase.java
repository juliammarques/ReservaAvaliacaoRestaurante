package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllReservaMesaUseCase {


	@NonNull
	private final ReservaMesaDomainService service;
}
