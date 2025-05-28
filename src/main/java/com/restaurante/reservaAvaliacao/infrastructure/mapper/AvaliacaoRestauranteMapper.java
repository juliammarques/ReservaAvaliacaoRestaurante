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
	            new PaginationDTO(
	            	pagination.page(),
	            	pagination.size(),
	                pagination.total(),
	                pagination.totalPages());

	        return new AvaliacaoRestaurantePaginadoDTO(
	                pagination.items()
	                .stream()
	                .map(this::toDTO).toList()
	            ,paginationDTO);
	    }
		
		public AvaliacaoRestauranteDTO toDTO(final Output output) {
	        return new AvaliacaoRestauranteDTO(
	        		output.seqAvaliacao(),
	                output.dataAvaliacao(),
	                output.notaAvaliacao(),
	                output.comentario(),
	                output.documentoCliente(),
	                output.seqRestaurante())
	                ;
	    }
}
