package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

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
	
	private StatusReserva status;
	
	public ReservaMesa (LocalDate dataReserva,Long seqRestaurante,String nomeCliente,String documentoCliente,StatusReserva status){
		this.dataReserva = dataReserva;
		this.seqRestaurante = seqRestaurante;
		this.nomeCliente = nomeCliente;
		this.documentoCliente = documentoCliente;
		this.status = status;
	}
	
	public ReservaMesa (Long seqRestaurante,LocalDate dataReserva){
		this.dataReserva = dataReserva;
		this.seqRestaurante = seqRestaurante;
	}
	
	public ReservaMesa (Long seqRestaurante, StatusReserva status){
		this.seqRestaurante = seqRestaurante;
		this.status = status;
	}
	
	public ReservaMesa to(ReservaMesaEntity entity) {
		return new ReservaMesa(entity.getSeqReservaMesa(), entity.getDataReserva(), entity.getSeqRestaurante(),
				entity.getNomeCliente(),entity.getDocumentoCliente(),entity.getStatus());
		
	}
	
	public static ReservaMesa with(final Long seqReservaMesa,final LocalDate dataReserva,final Long seqRestaurante,final String nomeCliente,final String documentoCliente,final StatusReserva status) {
        return new ReservaMesa(seqReservaMesa,dataReserva,seqRestaurante, nomeCliente,documentoCliente,status);
    }
	
	public static ReservaMesa with(final Long seqReservaMesa,final LocalDate dataReserva) {
        return new ReservaMesa(seqReservaMesa,dataReserva);
    }
	
	public static ReservaMesa with(final Long seqReservaMesa, final StatusReserva status) {
        return new ReservaMesa(seqReservaMesa, status);
    }
}
