package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;
import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private String endereco;
	
	@NotBlank
	private String tipoCozinha;
	
	

	public static RestauranteEntity of(Restaurante restaurante) {
		return new RestauranteEntity(restaurante.getSeqRestaurante(),restaurante.getNomeRestaurante(),restaurante.getDataCadastro(),
			restaurante.getEndereco(), restaurante.getTipoCozinha());
		
	}
	
	
	
	
}
