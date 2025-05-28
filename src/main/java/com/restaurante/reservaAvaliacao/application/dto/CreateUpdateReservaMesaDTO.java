package com.restaurante.reservaAvaliacao.application.dto;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUpdateReservaMesaDTO {

	private Long seqReservaMesa;
	private LocalDate dataReserva;
	private Long seqRestaurante;
	private String nomeCliente;
	private String documentoCliente;
	private StatusReserva status;
	
	public CreateUpdateReservaMesaDTO() {
		super();
	}
}
