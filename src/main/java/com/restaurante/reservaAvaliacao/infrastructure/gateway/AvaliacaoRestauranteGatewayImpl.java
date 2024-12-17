package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restaurante.reservaAvaliacao.domain.Specification.AvaliacaoRestauranteSpec;
import com.restaurante.reservaAvaliacao.domain.Specification.ReservaMesaSpec;
import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.exception.ClienteNaoEncontrado;
import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IAvaliacaoRestauranteRepository;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvaliacaoRestauranteGatewayImpl implements AvaliacaoRestauranteGateway{

	private final IAvaliacaoRestauranteRepository repository;
	private final IReservaMesaRepository reservaMesaRepository;

	
	@Override
	public void createAvaliacaoRestaurante(AvaliacaoRestaurante avaliacao) {
	var jaFoiNoRestaurante = reservaMesaRepository.findAll(ReservaMesaSpec.buscaPorDocumentoClienteESeqRestaurante(avaliacao.getDocumentoCliente(),avaliacao.getSeqRestaurante()));
	if(jaFoiNoRestaurante.isEmpty()) {
		throw new ClienteNaoEncontrado();
	}
		repository.save(AvaliacaoRestauranteEntity.of(avaliacao));
	}
	
	@Override
	public Pagination<AvaliacaoRestaurante> findAll(int page, int size,Long seqResataurante){
        final var pageable = Pageable.ofSize(size).withPage(page);
        Page<AvaliacaoRestaurante> listaAvaliacao = repository.findAll(AvaliacaoRestauranteSpec.buscaAvaliacaoPorSeqRestaurante(seqResataurante),pageable).map(AvaliacaoRestauranteEntity::toAvaliacao);
		return Pagination.fromAvaliacao(listaAvaliacao.getContent(),listaAvaliacao.getNumber(), listaAvaliacao.getSize(), (int) listaAvaliacao.getTotalElements(),
				listaAvaliacao.getTotalPages());
	}

}
