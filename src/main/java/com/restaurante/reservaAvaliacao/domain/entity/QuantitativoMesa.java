package com.restaurante.reservaAvaliacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuantitativoMesa {

	
	private Long seqQuantitativoMesaEntity;
	private Long seqRestaurante;
	private String tamanhoMesa;
	private Long  quantidadeDisponivel;
	//private String indDisponivel;
	
	public QuantitativoMesa (Long seqRestaurante,String tamanhoMesa, Long  quantidadeDisponivel) {
		this.seqRestaurante = seqRestaurante;
		this.tamanhoMesa = tamanhoMesa;
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	
	public static QuantitativoMesa with(final Long seqQuantitativoMesaEntity,final Long seqRestaurante,final String tamanhoMesa, final Long  quantidadeDisponivel) {
        return new QuantitativoMesa(seqQuantitativoMesaEntity,seqRestaurante, tamanhoMesa,quantidadeDisponivel);
    }
}
