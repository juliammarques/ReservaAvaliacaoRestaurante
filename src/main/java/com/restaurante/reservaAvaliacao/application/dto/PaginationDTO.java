package com.restaurante.reservaAvaliacao.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaginationDTO {
	private int  page;
	private int  size;
	private int  total;
	private int  totalPages;

}
