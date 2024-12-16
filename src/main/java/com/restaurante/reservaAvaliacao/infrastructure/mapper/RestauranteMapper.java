package com.restaurante.reservaAvaliacao.infrastructure.mapper;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.PaginationDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllRestauranteUseCase.Output;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public class RestauranteMapper {

	 public RestauranteDTO toDTO(final CreateRestauranteUseCase.Output output) {
	        return new RestauranteDTO().tipoCozinha(output.tipoCozinha())
	                    .endereco(output.endereco())
	                    .nomeRestaurante(output.nomeRestaurante())
	                    ;
	 }
	 
	public RestaurantePaginadoDTO toDTO(final Pagination<GetAllRestauranteUseCase.Output> pagination) {
        final var paginationDTO = 
            new PaginationDTO()
                .page(pagination.page())
                .size(pagination.size())
                .total(pagination.total())
                .totalPages(pagination.totalPages());

        return new RestaurantePaginadoDTO()
            .data(
                pagination.items()
                .stream()
                .map(this::toDTO).toList()
            )
            .pagination(paginationDTO);
    }
	
	public RestauranteDTO toDTO(final Output output) {
        return new RestauranteDTO()
        		.tipoCozinha(output.tipoCozinha())
                .endereco(output.endereco())
                .nomeRestaurante(output.nomeRestaurante())
                ;
    }
	
	public CreateRestauranteUseCase.Input from(final CreateUpdateRestauranteDTO dto) {
        return new CreateRestauranteUseCase.Input(dto.getNomeRestaurante(), dto.getEndereco(), dto.getTipoCozinha());
    }

    public UpdateRestauranteUseCase.Input from(final Long id, final CreateUpdateRestauranteDTO dto) {
        return new UpdateRestauranteUseCase.Input(id,dto.getNomeRestaurante(), dto.getEndereco(), dto.getTipoCozinha());
    }
}
