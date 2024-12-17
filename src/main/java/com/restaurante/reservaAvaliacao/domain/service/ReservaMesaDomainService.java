package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.exception.ReservaNaoEncontrada;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservaMesaDomainService {

	@NonNull
	private final ReservaMesaGateway gateway;
	
	public void criaReservaRestaurante (ReservaMesa reserva) {
		gateway.createReservaMesa(reserva);
	}
	
	public void atualizaReserva (ReservaMesa reserva) {
		gateway.getReservaMesaById(reserva.getSeqReservaMesa())
				.orElseThrow(()->new ReservaNaoEncontrada(reserva.getSeqReservaMesa()));
		gateway.updateReserva(reserva);
	}
	
	public void atualizaStatusConfirmado(ReservaMesa reserva) {
		gateway.getReservaMesaById(reserva.getSeqReservaMesa())
		.orElseThrow(()->new ReservaNaoEncontrada(reserva.getSeqReservaMesa()));
		gateway.updateReserva(reserva);
	}
	
	public void atualizaStatusEncerrado(ReservaMesa reserva) {
		gateway.getReservaMesaById(reserva.getSeqReservaMesa())
		.orElseThrow(()->new ReservaNaoEncontrada(reserva.getSeqReservaMesa()));
		gateway.updateReserva(reserva);
	}
	
	public Pagination<ReservaMesa> buscaReservaPorRestaurante(int page, int size,Long seqRestaurante){
		return gateway.findAll(page, size,seqRestaurante);
	}
	
	public ReservaMesa buscaReservaPorId(Long id){
		return gateway.getReservaMesaById(id).orElseThrow(()->new ReservaNaoEncontrada(id));
	}
}
