package com.restaurante.reservaAvaliacao.domain.gateway;

import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;

public interface ReservaMesaGateway {

	void deleteReserva(Long idReserva);

	void updateReserva(ReservaMesa restaurante);

	List<ReservaMesa> buscaReservaMesaPorCliente(String documento);

	void createReservaMesa(ReservaMesa ReservaMesa);

}
