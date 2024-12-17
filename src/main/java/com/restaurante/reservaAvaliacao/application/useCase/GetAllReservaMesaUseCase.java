package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllReservaMesaUseCase {


	@NonNull
	private final ReservaMesaDomainService service;
	
	public Pagination<Output> execute (final int page, final int size, Long seqRestaurante){
    	return service.buscaReservaPorRestaurante(page, size, seqRestaurante).map(Output::from);
    }
    
    public record Output(Long seqReservaMesa, LocalDate dataReserva, Long seqRestaurante, String nomeCliente,
				String documentoCliente, StatusReserva status) {

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
