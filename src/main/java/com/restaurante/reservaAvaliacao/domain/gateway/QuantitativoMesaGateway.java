package com.restaurante.reservaAvaliacao.domain.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;

public interface QuantitativoMesaGateway {

	void deleteQuantitativoMesa(Long idQuantitativo);

	void updateQuantitativoMesa(QuantitativoMesa quantitativoMesa);

	void createQuantitativoMesa(QuantitativoMesa quantitativoMesa);

}
