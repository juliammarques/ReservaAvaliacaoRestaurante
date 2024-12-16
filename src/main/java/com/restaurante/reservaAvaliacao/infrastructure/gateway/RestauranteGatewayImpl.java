package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteGatewayImpl implements RestauranteGateway {

	private final IRestauranteRepository repository;
	
	@Override
	public void createRestaurante(Restaurante restaurante) {
		restaurante.setDataCadastro(LocalDate.now());
		repository.save(RestauranteEntity.of(restaurante));
	}
	
	@Override
	public Pagination<Restaurante> findAll(int page, int size){
        final var pageable = Pageable.ofSize(size).withPage(page);
        Page<Restaurante> listaRestaurante = repository.findAll(pageable).map(RestauranteEntity::toRestaurante);
		return Pagination.from(listaRestaurante.getNumber(), listaRestaurante.getSize(), (int) listaRestaurante.getTotalElements(),
				listaRestaurante.getTotalPages(), listaRestaurante.getContent());
	}
	 
	@Override
	public void updateRestaurante(Restaurante restaurante) {
		repository.save(RestauranteEntity.of(restaurante));
	}
	
	@Override
	public void deleteRestaurante (Long idRestaurante) {
		repository.deleteById(idRestaurante);
	}
	
	@Override
    public Optional<Restaurante> getRestauranteById(final Long seqRestaurante) {
        return repository.findById(seqRestaurante).map(RestauranteEntity::toRestaurante);
    }
	
}
