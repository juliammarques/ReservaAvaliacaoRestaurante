package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ReservaMesa")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaMesaEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqReservaMesa;
	@NotNull
	private LocalDate dataReserva;
	@NotNull
	private Long seqRestaurante;
	@NotNull
	private String nomeCliente;
	@NotNull
	private String documentoCliente;
	
	
	public static ReservaMesaEntity of(ReservaMesa reserva ) {
		return new ReservaMesaEntity(reserva.getSeqReservaMesa(), reserva.getDataReserva(), reserva.getSeqRestaurante(), reserva.getNomeCliente(), reserva.getDocumentoCliente());
	}
	
	
}
