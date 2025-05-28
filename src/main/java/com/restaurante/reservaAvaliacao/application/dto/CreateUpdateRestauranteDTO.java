package com.restaurante.reservaAvaliacao.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUpdateRestauranteDTO {
	private String tipoCozinha;
	private String endereco;
	private String nomeRestaurante;
	
	public CreateUpdateRestauranteDTO () {
		super();
	}
}
