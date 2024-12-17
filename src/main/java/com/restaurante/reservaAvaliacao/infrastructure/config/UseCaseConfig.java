package com.restaurante.reservaAvaliacao.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurante.reservaAvaliacao.application.useCase.CreateAvaliacaoUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.CreateQuantativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.CreateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.CreateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.DeleteQuantitativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllAvaliacaoRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetReservaMesaByIdUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateQuantitativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusConfirmadoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateStatusEncerradoReservaMesaUseCase;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

@Configuration
public class UseCaseConfig {

	@Bean
	CreateAvaliacaoUseCase createAvaliacaoUseCase(AvaliacaoRestauranteDomainService avaliacaoDomainService){
		return new CreateAvaliacaoUseCase(avaliacaoDomainService);
	}
	@Bean
	GetAllAvaliacaoRestauranteUseCase getAllAvaliacaoRestauranteUseCase(AvaliacaoRestauranteDomainService avaliacaoDomainService) {
		return new GetAllAvaliacaoRestauranteUseCase(avaliacaoDomainService);
	}
	@Bean
	CreateReservaMesaUseCase createReservaMesaUseCase (ReservaMesaDomainService reservaMesaDomainService) {
		return new CreateReservaMesaUseCase(reservaMesaDomainService);
	}
	@Bean
	GetAllReservaMesaUseCase getAllReservaMesaUseCase(ReservaMesaDomainService reservaMesaDomainService) {
		return new GetAllReservaMesaUseCase(reservaMesaDomainService);
	}
	@Bean
	GetReservaMesaByIdUseCase getReservaMesaByIdUseCase(ReservaMesaDomainService reservaMesaDomainService) {
		return new GetReservaMesaByIdUseCase(reservaMesaDomainService);
	}
	@Bean
	UpdateReservaMesaUseCase updateReservaMesaUseCase(ReservaMesaDomainService reservaMesaDomainService) {
		return new UpdateReservaMesaUseCase(reservaMesaDomainService);
	}
	@Bean
	UpdateStatusConfirmadoReservaMesaUseCase updateStatusConfirmadoReservaMesaUseCase(ReservaMesaDomainService reservaMesaDomainService) {
		return new UpdateStatusConfirmadoReservaMesaUseCase(reservaMesaDomainService);
	}
	@Bean
	UpdateStatusEncerradoReservaMesaUseCase updateStatusEncerradoReservaMesaUseCase(ReservaMesaDomainService reservaMesaDomainService) {
		return new UpdateStatusEncerradoReservaMesaUseCase(reservaMesaDomainService);
	}
	@Bean
	CreateRestauranteUseCase createRestauranteUseCase(RestauranteDomainService restauranteDomainService) {
		return new CreateRestauranteUseCase(restauranteDomainService);
	}
	@Bean
	GetAllRestauranteUseCase getAllRestauranteUseCase(RestauranteDomainService restauranteDomainService) {
		return new GetAllRestauranteUseCase(restauranteDomainService);
	}
	@Bean
	UpdateRestauranteUseCase updateRestauranteUseCase(RestauranteDomainService restauranteDomainService) {
		return new UpdateRestauranteUseCase(restauranteDomainService);
	}
	@Bean
	CreateQuantativoMesaUseCase createQuantativoMesaUseCase(QuantitativoMesaDomainService quantitativoDomainService) {
		return new CreateQuantativoMesaUseCase(quantitativoDomainService);
	}
	@Bean
	DeleteQuantitativoMesaUseCase deleteQuantitativoMesaUseCase(QuantitativoMesaDomainService quantitativoDomainService){
		return new DeleteQuantitativoMesaUseCase(quantitativoDomainService);
	}
	@Bean
	UpdateQuantitativoMesaUseCase updateQuantitativoMesaUseCase(QuantitativoMesaDomainService quantitativoDomainService) {
		return new UpdateQuantitativoMesaUseCase(quantitativoDomainService);
	}
	
	
	
	
	
	
	
	
}
