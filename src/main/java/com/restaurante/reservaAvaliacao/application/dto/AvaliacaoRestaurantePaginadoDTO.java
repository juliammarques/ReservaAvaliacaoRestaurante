package com.restaurante.reservaAvaliacao.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AvaliacaoRestaurantePaginadoDTO {

	private List<AvaliacaoRestauranteDTO> data;
	private PaginationDTO pagination;
	
	public AvaliacaoRestaurantePaginadoDTO() {
		super();
	}
	
}
