package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
	private StatusReserva status;
	

    public enum StatusReserva {
        ESPERANDO_CONFIRMACAO,
    	CONFIRMADA,
        ENCERRADA
    }
	
	public static ReservaMesaEntity of(ReservaMesa reserva ) {
		return new ReservaMesaEntity(reserva.getSeqReservaMesa(), reserva.getDataReserva(), reserva.getSeqRestaurante(), reserva.getNomeCliente(), reserva.getDocumentoCliente(),reserva.getStatus());
	}
	
	public ReservaMesa toReserva() {
        return ReservaMesa.with(this.seqReservaMesa, this.dataReserva,this.seqRestaurante,this.nomeCliente,this.documentoCliente,this.status);
    }
	
}
