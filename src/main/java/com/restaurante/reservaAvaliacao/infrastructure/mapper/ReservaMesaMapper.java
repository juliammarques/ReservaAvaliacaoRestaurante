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

public class ReservaMesaMapper {

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
        		new PaginationDTO(
    	            	pagination.page(),
    	            	pagination.size(),
    	                pagination.total(),
    	                pagination.totalPages());

        return new ReservaMesaPaginadoDTO(
                pagination.items()
                .stream()
                .map(this::toDTO).toList()
            ,paginationDTO);
    }
	
	public ReservaMesaDTO toDTO(final Output output) {
        return new ReservaMesaDTO(
        		output.seqReservaMesa(),
                output.dataReserva(),
                output.seqRestaurante(),
                output.nomeCliente(),
                output.documentoCliente(),
                output.status())
                ;
    }
	
	public ReservaMesaDTO toDTO(final GetReservaMesaByIdUseCase.Output output) {
        return new ReservaMesaDTO(
        		output.seqReservaMesa(),
                output.dataReserva(),
                output.seqRestaurante(),
                output.nomeCliente(),
                output.documentoCliente(),
                output.status())
                ;
    }
}
