package com.restaurante.reservaAvaliacao.infrastructure.mapper;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.PaginationDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaPaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllReservaMesaUseCase.Output;
import com.restaurante.reservaAvaliacao.application.useCase.GetReservaMesaByIdUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusConfirmadoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusEncerradoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public class ReservaVagaMapper {

	public CreateReservaMesaUseCase.Input from(final CreateUpdateReservaMesaDTO dto) {
        return new CreateReservaMesaUseCase.Input(dto.getNomeCliente(), dto.getDocumentoCliente(), dto.getDataReserva(), dto.getSeqRestaurante());
    }

    public UpdateReservaMesaUseCase.Input from(final Long id, final CreateUpdateReservaMesaDTO dto) {
        return new UpdateReservaMesaUseCase.Input(id,dto.getDataReserva());
    }
    
    public UpdateStatusConfirmadoReservaMesaUseCase.Input from(final Long id) {
        return new UpdateStatusConfirmadoReservaMesaUseCase.Input(id);
    }
    
    public UpdateStatusEncerradoReservaMesaUseCase.Input fromInput(final Long id) {
        return new UpdateStatusEncerradoReservaMesaUseCase.Input(id);
    }
    
    
    public ReservaMesaPaginadoDTO toDTO(final Pagination<GetAllReservaMesaUseCase.Output> pagination) {
        final var paginationDTO = 
            new PaginationDTO()
                .page(pagination.page())
                .size(pagination.size())
                .total(pagination.total())
                .totalPages(pagination.totalPages());

        return new ReservaMesaPaginadoDTO()
            .data(
                pagination.items()
                .stream()
                .map(this::toDTO).toList()
            )
            .pagination(paginationDTO);
    }
	
	public ReservaMesaDTO toDTO(final Output output) {
        return new ReservaMesaDTO()
        		.seqReservaMesa(output.seqReservaMesa())
                .dataReserva(output.dataReserva())
                .seqRestaurante(output.seqRestaurante())
                .nomeCliente(output.nomeCliente())
                .documentoCliente(output.documentoCliente())
                .status(output.status())
                ;
    }
	
	public ReservaMesaDTO toDTO(final GetReservaMesaByIdUseCase.Output output) {
        return new ReservaMesaDTO()
        		.seqReservaMesa(output.seqReservaMesa())
                .dataReserva(output.dataReserva())
                .seqRestaurante(output.seqRestaurante())
                .nomeCliente(output.nomeCliente())
                .documentoCliente(output.documentoCliente())
                .status(output.status())
                ;
    }
}
