package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restaurante.reservaAvaliacao.domain.Specification.ReservaMesaSpec;
import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
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
	public Pagination<ReservaMesa> findAll(int page, int size,Long seqResataurante){
        final var pageable = Pageable.ofSize(size).withPage(page);
        Page<ReservaMesa> listaReservaRestaurante = repository.findAll(ReservaMesaSpec.buscaPorSeqRestaurante(seqResataurante),pageable).map(ReservaMesaEntity::toReserva);
		return Pagination.from(listaReservaRestaurante.getContent(),listaReservaRestaurante.getNumber(), listaReservaRestaurante.getSize(), (int) listaReservaRestaurante.getTotalElements(),
				listaReservaRestaurante.getTotalPages());
	}
	
	@Override
	public void updateReserva(ReservaMesa reserva) {
		repository.save(ReservaMesaEntity.of(reserva));
	}
	
	@Override
	public void deleteReserva (Long idReserva) {
		repository.deleteById(idReserva);
	}
	
	@Override
    public Optional<ReservaMesa> getReservaMesaById(final Long seqReserva) {
        return repository.findById(seqReserva).map(ReservaMesaEntity::toReserva);
    }
	// gerenciar reservar busca reserva por data 
}
