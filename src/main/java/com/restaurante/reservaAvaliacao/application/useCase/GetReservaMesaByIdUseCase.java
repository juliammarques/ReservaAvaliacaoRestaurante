package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetReservaMesaByIdUseCase {

	@NonNull
	private final ReservaMesaDomainService service;
	
	 public Output execute(final Long reservaId) {
	        final var reservaMesa = service.buscaReservaPorId(reservaId);
	        return Output.from(reservaMesa);
	    }

	 public record Output(Long seqReservaMesa, LocalDate dataReserva, Long seqRestaurante, String nomeCliente,
				String documentoCliente, StatusReserva status){

		 public static Output from(final ReservaMesa reserva) {
			 return new Output(
			 		reserva.getSeqReservaMesa(),
			 		reserva.getDataReserva(),
			 		reserva.getSeqRestaurante(),
			 		reserva.getNomeCliente(),
			 		reserva.getDocumentoCliente(),
			 		reserva.getStatus()
	                    );
	        }
	    }
	
}

