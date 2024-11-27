package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoRestaurante {

	private Long seqAvaliacao;
	private LocalDate dataAvaliacao;
	private Long notaAvaliacao;
	private String comentario;
}
