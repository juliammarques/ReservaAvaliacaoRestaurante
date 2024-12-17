package com.restaurante.reservaAvaliacao.domain.gateway;

import java.util.Optional;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public interface ReservaMesaGateway {

	void deleteReserva(Long idReserva);

	void updateReserva(ReservaMesa restaurante);

	void createReservaMesa(ReservaMesa ReservaMesa);

	Optional<ReservaMesa> getReservaMesaById(Long seqReserva);

	Pagination<ReservaMesa> findAll(int page, int size, Long seqResataurante);

}
