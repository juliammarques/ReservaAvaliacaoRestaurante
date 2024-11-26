package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

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
}
