package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;
import java.time.LocalDate;
import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestauranteEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqRestaurante;
	
	@NotBlank
	private String nomeRestaurante;
	
	@NotBlank
	private LocalDate dataCadastro;
	
	@NotBlank
	private List<QuantitativoMesaEntity> mesasDisponiveis;
	

	public static RestauranteEntity of(Restaurante restaurante) {
		return new RestauranteEntity(restaurante.getSeqRestaurante(),restaurante.getNomeRestaurante(),restaurante.getDataCadastro(), QuantitativoMesaEntity.list(restaurante.getMesasDisponiveis()));
		
	}
	
	
	
	
}
