package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.util.Optional;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IQuantitativoMesaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuantitativoMesaGatewayImpl implements QuantitativoMesaGateway{

	private final IQuantitativoMesaRepository repository;

	
	@Override
	public void createQuantitativoMesa(QuantitativoMesa quantitativoMesa) {
		repository.save(QuantitativoMesaEntity.of(quantitativoMesa));
	}
	
	@Override
	public void updateQuantitativoMesa(QuantitativoMesa quantitativoMesa) {
		repository.save(QuantitativoMesaEntity.of(quantitativoMesa));
	}
	
	@Override
	public void deleteQuantitativoMesa(Long idQuantitativo) {
		repository.deleteById(idQuantitativo);
	}
	
	@Override
    public Optional<QuantitativoMesa> getQuantitativoMesaById(final Long seqQuantitativo) {
        return repository.findById(seqQuantitativo).map(QuantitativoMesaEntity::toQuantitativo);
    }
}
