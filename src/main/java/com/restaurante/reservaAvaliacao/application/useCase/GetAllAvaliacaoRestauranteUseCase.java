package com.restaurante.reservaAvaliacao.application.useCase;

import java.time.LocalDate;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllAvaliacaoRestauranteUseCase {

	@NonNull
	private final AvaliacaoRestauranteDomainService service;
	
	public Pagination<Output> execute (final int page, final int size, Long seqRestaurante){
    	return service.buscaAvaliacaoPorRestaurante(page, size, seqRestaurante).map(Output::from);
    }
    
    public record Output(Long seqAvaliacao, LocalDate dataAvaliacao,Long notaAvaliacao,String comentario,String documentoCliente,Long seqRestaurante) {

		public static Output from(final AvaliacaoRestaurante avaliacao) {
    	return new Output(
    			avaliacao.getSeqAvaliacao(),
    			avaliacao.getDataAvaliacao(),
    			avaliacao.getNotaAvaliacao(),
    			avaliacao.getComentario(),
    			avaliacao.getDocumentoCliente(),
    			avaliacao.getSeqRestaurante()
       );
}
    }
    
}
