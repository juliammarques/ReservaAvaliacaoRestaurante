package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.util.ArrayList;
import java.util.List;

import com.restaurante.reservaAvaliacao.domain.Specification.ReservaMesaSpec;
import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservaMesaGatewayImpl implements ReservaMesaGateway {

	private final IReservaMesaRepository repository;
	
	@Override
	public void createReservaMesa(ReservaMesa reservaMesa) {
		// verificarQuantitativoMesa
		repository.save(ReservaMesaEntity.of(reservaMesa));
	}
	
	@Override
	// alterar retorno
	public List<ReservaMesa> buscaReservaMesaPorCliente(String documento){
		
		List<ReservaMesaEntity> reservaEntity = repository.findAll(ReservaMesaSpec.buscaPorDocumentoCliente(documento));
		List<ReservaMesa> resposta = new ArrayList<ReservaMesa>();
		for(var entity : reservaEntity) {
			ReservaMesa reserva = null;
			reserva.to(entity);
			resposta.add(reserva);
		}
		return resposta;
	}
	
	@Override
	public void updateReserva(ReservaMesa reserva) {
		
		repository.save(ReservaMesaEntity.of(reserva));
	}
	
	@Override
	public void deleteReserva (Long idReserva) {
		repository.deleteById(idReserva);
	}
	
	// gerenciar reservar busca reserva por data 
}
