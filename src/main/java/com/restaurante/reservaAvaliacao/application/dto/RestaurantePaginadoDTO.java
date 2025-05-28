package com.restaurante.reservaAvaliacao.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantePaginadoDTO {
	
	private List<RestauranteDTO> data;
	private PaginationDTO pagination;
	
	public RestaurantePaginadoDTO() {
		super();
	}
}
