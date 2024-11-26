package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Example;

import com.restaurante.reservaAvaliacao.domain.Specification.ReservaMesaSpec;
import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservaMesaGatewayImpl implements ReservaMesaGateway {

	private final IReservaMesaRepository repository;
	
	@Override
	public void createReservaMesa(ReservaMesa ReservaMesa) {
		// salvarReservaMesa
	}
	
	@Override
	// alterar retorno
	public List<ReservaMesaEntity> buscaReservaMesaPorCliente(String documento){
		return repository.findAll(ReservaMesaSpec.buscaPorDocumentoCliente(documento));
	}
	
	@Override
	public void updateReserva(ReservaMesa restaurante) {
		
		//#TODO:EDITAR RESERVA
	}
	
	@Override
	public void deleteReserva (Long idReserva) {
		repository.deleteById(idReserva);
	}
}
