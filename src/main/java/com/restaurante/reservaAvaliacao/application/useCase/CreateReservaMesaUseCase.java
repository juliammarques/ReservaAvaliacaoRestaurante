package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateReservaMesaUseCase {

	@NonNull
	private final ReservaMesaDomainService service;
	
	public void execute (Input input) {
		service.criaReservaRestaurante(new ReservaMesa(input.dataReserva, input.seqRestaurante, input.nomeCliente, input.documentoCliente, StatusReserva.CONFIRMADA));
	}
	
	public record Input(String nomeCliente, String documentoCliente, LocalDate dataReserva,Long seqRestaurante) {
	}
}
