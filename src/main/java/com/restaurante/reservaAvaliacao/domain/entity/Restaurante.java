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
	
	public Restaurante(String nomeRestaurante,String endereco,String tipoCozinha,LocalDate dataCadastro) {	
		this.nomeRestaurante = nomeRestaurante;
		this.endereco = endereco;
		this.tipoCozinha = tipoCozinha;
		this.dataCadastro = dataCadastro;
	}
	
	public Restaurante(Long seqRestaurante,String nomeRestaurante,String endereco,String tipoCozinha) {	
		this.seqRestaurante = seqRestaurante;
		this.nomeRestaurante = nomeRestaurante;
		this.endereco = endereco;
		this.tipoCozinha = tipoCozinha;
	}

	
	public static Restaurante with(final String nomeRestaurante,final String endereco,final String tipoCozinha) {
        return new Restaurante(nomeRestaurante,endereco, tipoCozinha);
    }
	
	public static Restaurante with(final Long seqRestaurante, final String nomeRestaurante,final String endereco,final String tipoCozinha) {
        return new Restaurante(seqRestaurante,nomeRestaurante,endereco, tipoCozinha);
    }
	
}
