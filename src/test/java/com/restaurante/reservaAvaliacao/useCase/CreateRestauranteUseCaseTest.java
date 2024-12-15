package com.restaurante.reservaAvaliacao.useCase;

import com.restaurante.reservaAvaliacao.application.useCase.CreateRestauranteUseCase;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;
import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

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
