package com.restaurante.reservaAvaliacao.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

@Configuration
public class ServiceConfig {

	@Bean
	AvaliacaoRestauranteDomainService avaliacaoRestauranteDomainService(final AvaliacaoRestauranteGateway avaliacaoGateway) {
		return new AvaliacaoRestauranteDomainService(avaliacaoGateway);
	}
	@Bean
	QuantitativoMesaDomainService quantitativoMesaDomainService (final QuantitativoMesaGateway quantitativoGateway) {
		return new QuantitativoMesaDomainService(quantitativoGateway);
	}
	@Bean
	ReservaMesaDomainService reservaMesaDomainService(final ReservaMesaGateway reservaGateway) {
		return new ReservaMesaDomainService(reservaGateway);
	}
	@Bean
	RestauranteDomainService restauranteDomainService (final RestauranteGateway restauranteGateway) {
		return new RestauranteDomainService(restauranteGateway);
	}
}
