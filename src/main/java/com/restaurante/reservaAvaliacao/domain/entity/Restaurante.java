package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurante {

	private Long seqRestaurante;
	private String nomeRestaurante;
	private LocalDate dataCadastro;
	private List<QuantitativoMesa> mesasDisponiveis;
	
	
}
