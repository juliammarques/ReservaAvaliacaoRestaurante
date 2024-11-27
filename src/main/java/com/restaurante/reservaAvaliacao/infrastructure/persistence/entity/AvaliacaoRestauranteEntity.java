package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="AvaliacaoRestauranteEntity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliacaoRestauranteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqAvaliacao;
	@NotNull
	private LocalDate dataAvaliacao;
	@NotNull
	private Long notaAvaliacao;
	@NotNull
	private String comentario;
}
