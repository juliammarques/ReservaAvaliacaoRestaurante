package com.restaurante.reservaAvaliacao.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.AvaliacaoRestauranteGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.QuantitativoMesaGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.ReservaMesaGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.RestauranteGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IAvaliacaoRestauranteRepository;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IQuantitativoMesaRepository;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;

@Configuration
public class GatewayConfig {

	@Bean
	AvaliacaoRestauranteGateway avaliacaoRestauranteGateway(final IAvaliacaoRestauranteRepository avaliacaoRepository,
															final IReservaMesaRepository reservaMesaRepository) {
		return new AvaliacaoRestauranteGatewayImpl(avaliacaoRepository,reservaMesaRepository);
		
	}
	
	@Bean
	QuantitativoMesaGateway quantitativoMesaGateway (final IQuantitativoMesaRepository quantitativoRepository) {
		return new QuantitativoMesaGatewayImpl(quantitativoRepository);
	}
	
	@Bean
	ReservaMesaGateway reservaMesaGateway (final IReservaMesaRepository reservaRepository) {
		return new ReservaMesaGatewayImpl(reservaRepository);
	}
	
	@Bean
	RestauranteGateway restauranteGateway (final IRestauranteRepository restauranteRepository) {
		return new RestauranteGatewayImpl(restauranteRepository);
	}
}
