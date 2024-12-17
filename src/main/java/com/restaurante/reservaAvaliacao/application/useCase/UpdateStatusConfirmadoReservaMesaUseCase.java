package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class UpdateStatusConfirmadoReservaMesaUseCase {

	@NonNull
	private final ReservaMesaDomainService service;
	
	public void execute(Input input) {
		 service.atualizaStatusEncerrado(
		                ReservaMesa.with(
		                	input.id(),
		                	StatusReserva.CONFIRMADA
		                )
		            );
		    }

	 public record Input(Long id) {
	   }
}
