package com.restaurante.reservaAvaliacao.domain.gateway;

import java.util.Optional;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;

public interface QuantitativoMesaGateway {

	void deleteQuantitativoMesa(Long idQuantitativo);

	void updateQuantitativoMesa(QuantitativoMesa quantitativoMesa);

	void createQuantitativoMesa(QuantitativoMesa quantitativoMesa);

	Optional<QuantitativoMesa> getQuantitativoMesaById(Long seqQuantitativo);

}
