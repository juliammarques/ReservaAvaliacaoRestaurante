package com.restaurante.reservaAvaliacao.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUpdateQuantitativoMesaDTO {

	private Long seqQuantitativoMesaEntity;
	private Long seqRestaurante;
	private String tamanhoMesa;
	private Long  quantidadeDisponivel;
	
	public CreateUpdateQuantitativoMesaDTO() {
		super();
	}
}
