package com.restaurante.reservaAvaliacao.application.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAvaliacaoRestauranteDTO {
	private LocalDate dataAvaliacao;
	private Long notaAvaliacao;
	private String comentario;
	private String documentoCliente;
	private Long seqRestaurante;
	
	public CreateAvaliacaoRestauranteDTO() {
		super();
	}
}
