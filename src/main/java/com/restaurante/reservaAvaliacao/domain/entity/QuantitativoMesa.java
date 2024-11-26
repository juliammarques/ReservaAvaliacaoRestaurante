package com.restaurante.reservaAvaliacao.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantitativoMesa {

	
	private Long seqQuantitativoMesaEntity;
	private Long seqRestaurante;
	private Long tamanhoMesa;
	private Long  quantidadeDisponivel;
	//private String indDisponivel;
}
