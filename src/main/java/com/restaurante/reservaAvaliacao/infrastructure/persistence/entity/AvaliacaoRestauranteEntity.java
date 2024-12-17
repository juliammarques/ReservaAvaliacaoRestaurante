package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;

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
@Table(name="AvaliacaoRestauranteEntity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliacaoRestauranteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqAvaliacao;
	@NotNull
	private LocalDate dataAvaliacao;
	@NotNull
	private Long notaAvaliacao;
	@NotNull
	private String comentario;
	@NotNull
	private String documentoCliente;
	@NotNull
	private Long seqRestaurante;
	
	public AvaliacaoRestauranteEntity (LocalDate dataAvaliacao,Long notaAvaliacao,String comentario,String documentoCliente,Long seqRestaurante){
		this.dataAvaliacao = dataAvaliacao;
		this.notaAvaliacao = notaAvaliacao;
		this.comentario = comentario;
		this.documentoCliente = documentoCliente;
		this.seqRestaurante = seqRestaurante;
	}

	public static AvaliacaoRestauranteEntity of(AvaliacaoRestaurante avaliacao) {
		return new AvaliacaoRestauranteEntity(avaliacao.getDataAvaliacao(),avaliacao.getNotaAvaliacao(),avaliacao.getComentario(),avaliacao.getDocumentoCliente(),avaliacao.getSeqRestaurante());
	}
	
	public AvaliacaoRestaurante toAvaliacao () {
		return AvaliacaoRestaurante.with(this.seqAvaliacao,this.dataAvaliacao,this.notaAvaliacao,this.comentario,this.documentoCliente, this.seqRestaurante);
	}

}
