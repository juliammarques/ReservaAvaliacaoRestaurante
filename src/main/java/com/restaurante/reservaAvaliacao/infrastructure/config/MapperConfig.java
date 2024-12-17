package com.restaurante.reservaAvaliacao.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurante.reservaAvaliacao.infrastructure.mapper.AvaliacaoRestauranteMapper;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.QuantitativoMesaMapper;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.ReservaMesaMapper;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.RestauranteMapper;

@Configuration
public class MapperConfig {

	@Bean
	AvaliacaoRestauranteMapper avaliacaoMapper() {
		return new AvaliacaoRestauranteMapper();
	}
	@Bean
	QuantitativoMesaMapper quatitativoMapper() {
		return new QuantitativoMesaMapper();
	}
	@Bean
	ReservaMesaMapper reservaMapper() {
		return new ReservaMesaMapper();
	}
	@Bean
	RestauranteMapper restauranteMapper() {
		return new RestauranteMapper();
	}
}
