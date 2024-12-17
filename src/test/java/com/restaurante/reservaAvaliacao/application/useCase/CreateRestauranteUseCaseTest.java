package com.restaurante.reservaAvaliacao.application.useCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;

class CreateRestauranteUseCaseTest {

    private RestauranteDomainService serviceMock;
    private CreateRestauranteUseCase useCase;

    @BeforeEach
    void setUp() {
        // Criação do mock para o RestauranteDomainService
        serviceMock = mock(RestauranteDomainService.class);
        // Instanciando o caso de uso com o serviço mockado
        useCase = new CreateRestauranteUseCase(serviceMock);
    }

    @Test
    void testExecute() {
        // Definindo os parâmetros de entrada
        String nomeRestaurante = "Restaurante Teste";
        String tipoCozinha = "Italiana";
        String endereco = "Endereço Teste";

        // Criando o record Input com os dados de entrada
        CreateRestauranteUseCase.Input input = new CreateRestauranteUseCase.Input(nomeRestaurante, tipoCozinha, endereco);

        // Chamando o método execute
        useCase.execute(input);

        // Verificando se o método 'registraRestaurante' foi chamado com o Restaurante correto
        Restaurante restauranteEsperado = new Restaurante(nomeRestaurante, endereco, tipoCozinha);
        verify(serviceMock, times(1)).registraRestaurante(restauranteEsperado);
    }
}
