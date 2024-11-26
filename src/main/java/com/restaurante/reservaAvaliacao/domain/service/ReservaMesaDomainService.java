package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservaMesaDomainService {

	@NonNull
	private final ReservaMesaGateway gateway;
}
