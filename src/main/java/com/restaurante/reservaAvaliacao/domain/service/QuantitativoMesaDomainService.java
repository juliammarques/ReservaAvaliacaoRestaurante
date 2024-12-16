package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.exception.QuantitativoMesaNaoEncontrado;
import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuantitativoMesaDomainService {

	@NonNull
	private final QuantitativoMesaGateway gateway;
	
	
	public void registraQuantitativoInicial (QuantitativoMesa quantitativo) {
		gateway.createQuantitativoMesa(quantitativo);
	}
	
	public void atualizaQuantitativoMesa (QuantitativoMesa quantitativo) {
		gateway.getQuantitativoMesaById(quantitativo.getSeqQuantitativoMesaEntity()).orElseThrow(()-> new QuantitativoMesaNaoEncontrado(quantitativo.getSeqQuantitativoMesaEntity()));
		gateway.updateQuantitativoMesa(quantitativo);
	}
	
	public void deleteQuantitativo(Long idQuantitativo) {
		gateway.getQuantitativoMesaById(idQuantitativo).orElseThrow(()-> new QuantitativoMesaNaoEncontrado(idQuantitativo));
		gateway.deleteQuantitativoMesa(idQuantitativo);
	}
}
