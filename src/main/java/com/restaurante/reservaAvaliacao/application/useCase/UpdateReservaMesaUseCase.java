package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateReservaMesaUseCase {

	@NonNull
	private final ReservaMesaDomainService service;
	
	 public void execute(Input input) {
	 service.atualizaReserva(
	                ReservaMesa.with(
	                	input.id(),
	                	input.dataReserva
	                )
	            );
	    }

	    public record Input(Long id,LocalDate dataReserva) {
	    }
}
