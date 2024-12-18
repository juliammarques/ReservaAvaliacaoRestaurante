package com.restaurante.reservaAvaliacao.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaPaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetReservaMesaByIdUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusConfirmadoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusEncerradoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.ReservaMesaMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservaMesaController {

	@NonNull
	private final CreateReservaMesaUseCase createReservaMesa;
	
	@NonNull
	private final UpdateReservaMesaUseCase UpdateReservaMesa;

	@NonNull
	private final UpdateStatusConfirmadoReservaMesaUseCase updateStatusConfirmadoReservaMesaUseCase;
	@NonNull
	private final UpdateStatusEncerradoReservaMesaUseCase updateStatusEncerradoReservaMesaUseCase;
	@NonNull
	private final GetAllReservaMesaUseCase getAllReservaMesaUseCase;
	@NonNull
	private final GetReservaMesaByIdUseCase getReservaMesaById;
		
	@NonNull
	private final ReservaMesaMapper reservaMapper;
	
	
	public ResponseEntity<Void> ReservaMesa(final CreateUpdateReservaMesaDTO reserva){
		createReservaMesa.execute(reservaMapper.from(reserva));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualizaReservaPeloId(final Long id, final CreateUpdateReservaMesaDTO reserva){
		UpdateReservaMesa.execute(reservaMapper.from(id, reserva));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualisaStatusConfirmado(final Long id){
		updateStatusConfirmadoReservaMesaUseCase.execute(reservaMapper.from(id));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> atualizaStatusEncerrado (final Long id){
		updateStatusEncerradoReservaMesaUseCase.execute(reservaMapper.fromInput(id));
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<ReservaMesaPaginadoDTO> listaReserva(final Integer page, final Integer size, Long seqReserva ){
		final var reserva = getAllReservaMesaUseCase.execute(page, size, seqReserva);
		final var reservaResponse = reservaMapper.toDTO(reserva);
		return ResponseEntity.ok(reservaResponse);
	}
	
	
    public ResponseEntity<ReservaMesaDTO> reservaMesaId(final Long id) {
        final var reserva = getReservaMesaById.execute(id);
        final var reservaResponse = reservaMapper.toDTO(reserva);
        return ResponseEntity.ok(reservaResponse);
    }
}
