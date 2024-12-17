package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AvaliacaoRestaurante {

	private Long seqAvaliacao;
	private LocalDate dataAvaliacao;
	private Long notaAvaliacao;
	private String comentario;
	private String documentoCliente;
	private Long seqRestaurante;
	
	public AvaliacaoRestaurante (LocalDate dataAvaliacao,Long notaAvaliacao,String comentario,String documentoCliente,Long seqRestaurante){
		this.dataAvaliacao = dataAvaliacao;
		this.notaAvaliacao = notaAvaliacao;
		this.comentario = comentario;
		this.documentoCliente = documentoCliente;
		this.seqRestaurante = seqRestaurante;
	}
	public static AvaliacaoRestaurante with(final Long seqAvaliacao, final  LocalDate dataAvaliacao,final Long notaAvaliacao,final String comentario,final String documentoCliente,final Long seqRestaurante) {
		return new AvaliacaoRestaurante(seqAvaliacao, dataAvaliacao, notaAvaliacao, comentario, documentoCliente, seqRestaurante);
		
	}
}
