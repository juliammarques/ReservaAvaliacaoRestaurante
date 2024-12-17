package com.restaurante.reservaAvaliacao.infrastructure.mapper;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.application.dto.AvaliacaoRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.AvaliacaoRestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.dto.CreateAvaliacaoRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.PaginationDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateAvaliacaoUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllAvaliacaoRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllAvaliacaoRestauranteUseCase.Output;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public class AvaliacaoRestauranteMapper {


	public CreateAvaliacaoUseCase.Input from(final CreateAvaliacaoRestauranteDTO dto) {
        return new CreateAvaliacaoUseCase.Input(LocalDate.now(), dto.getNotaAvaliacao(), dto.getComentario(),dto.getDocumentoCliente(),dto.getSeqRestaurante());
    }
	  public AvaliacaoRestaurantePaginadoDTO toDTO(final Pagination<GetAllAvaliacaoRestauranteUseCase.Output> pagination) {
	        final var paginationDTO = 
	            new PaginationDTO()
	                .page(pagination.page())
	                .size(pagination.size())
	                .total(pagination.total())
	                .totalPages(pagination.totalPages());

	        return new AvaliacaoRestaurantePaginadoDTO()
	            .data(
	                pagination.items()
	                .stream()
	                .map(this::toDTO).toList()
	            )
	            .pagination(paginationDTO);
	    }
		
		public AvaliacaoRestauranteDTO toDTO(final Output output) {
	        return new AvaliacaoRestauranteDTO()
	        		.seqAvaliacao(output.seqAvaliacao())
	                .dataAvaliacao(output.dataAvaliacao())
	                .notaAvaliacao(output.notaAvaliacao())
	                .comentario(output.comentario())
	                .documentoCliente(output.documentoCliente())
	                .seqRestaurante(output.seqRestaurante())
	                ;
	    }
}
