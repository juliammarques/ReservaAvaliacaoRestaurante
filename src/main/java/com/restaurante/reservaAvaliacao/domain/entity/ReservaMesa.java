package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ReservaMesa {

	
	private Long seqReservaMesa;
	
	private LocalDate dataReserva;
	
	private Long seqRestaurante;
	
	private String nomeCliente;
	
	private String documentoCliente;
	
	public ReservaMesa to(ReservaMesaEntity entity) {
		return new ReservaMesa(entity.getSeqReservaMesa(), entity.getDataReserva(), entity.getSeqRestaurante(),
				entity.getNomeCliente(),entity.getDocumentoCliente());
		
	}
}
