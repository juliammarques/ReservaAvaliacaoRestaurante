package com.restaurante.reservaAvaliacao.application.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestauranteDTO {

	private Long seqRestaurante;
	private String nomeRestaurante;
	private LocalDate dataCadastro;
	private String endereco;
	private String tipoCozinha;
	
	public RestauranteDTO(String tipoCozinha, String endereco,String nomeRestaurante) {
		this.tipoCozinha= tipoCozinha;
		this.endereco = endereco;
		this.nomeRestaurante =nomeRestaurante;
	}
}
