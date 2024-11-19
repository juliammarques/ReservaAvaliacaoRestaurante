package com.restaurante.reservaAvaliacao.domain.entity;

import java.time.LocalDate;

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
	private String endereco;
	private String tipoCozinha;
	
	public Restaurante(String nomeRestaurante,String endereco,String tipoCozinha) {	
		this.nomeRestaurante = nomeRestaurante;
		this.endereco = endereco;
		this.tipoCozinha = tipoCozinha;
	}
	
}
