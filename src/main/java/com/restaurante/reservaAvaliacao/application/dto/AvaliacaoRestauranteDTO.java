package com.restaurante.reservaAvaliacao.application.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AvaliacaoRestauranteDTO {
	private Long seqAvaliacao;
	private LocalDate dataAvaliacao;
	private Long notaAvaliacao;
	private String comentario;
	private String documentoCliente;
	private Long seqRestaurante;
}
