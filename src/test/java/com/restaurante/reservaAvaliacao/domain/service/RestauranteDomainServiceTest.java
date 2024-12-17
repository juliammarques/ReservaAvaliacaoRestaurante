package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.exception.RestauranteNaoExiste;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestauranteDomainServiceTest {

    private RestauranteGateway restauranteGatewayMock;
    private RestauranteDomainService restauranteDomainService;

    @BeforeEach
    void setUp() {
        // Criar o mock do RestauranteGateway
        restauranteGatewayMock = mock(RestauranteGateway.class);

        // Criar o serviço que será testado
        restauranteDomainService = new RestauranteDomainService(restauranteGatewayMock);
    }

    @Test
    void deveRegistrarRestaurante() {
        Restaurante restaurante = new Restaurante("Restaurante A", "Rua A", "Italiana");

        // Chama o método do serviço
        restauranteDomainService.registraRestaurante(restaurante);

        // Verifica se o método createRestaurante foi chamado no gateway com o restaurante correto
        verify(restauranteGatewayMock, times(1)).createRestaurante(restaurante);
    }

    @Test
    void deveAtualizarRestauranteQuandoExistir() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante B", "Rua B", "Chinesa");

        // Configura o comportamento do mock para retornar um restaurante quando procurado pelo ID
        when(restauranteGatewayMock.getRestauranteById(1L)).thenReturn(Optional.of(restaurante));

        // Chama o método do serviço
        restauranteDomainService.atualizaRestaurante(restaurante);

        // Verifica se o método updateRestaurante foi chamado no gateway com o restaurante correto
        verify(restauranteGatewayMock, times(1)).updateRestaurante(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoTentarAtualizarRestauranteNaoExistente() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante C", "Rua C", "Brasileira");

        // Configura o comportamento do mock para retornar um Optional vazio quando o restaurante não é encontrado
        when(restauranteGatewayMock.getRestauranteById(1L)).thenReturn(Optional.empty());

        // Verifica se a exceção RestauranteNaoExiste é lançada
        assertThrows(RestauranteNaoExiste.class, () -> restauranteDomainService.atualizaRestaurante(restaurante));
    }

    @Test
    void deveDeletarRestaurante() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante D", "Rua D", "Francesa");

        // Chama o método do serviço
        restauranteDomainService.deleteRestaurante(1L);

        // Verifica se o método deleteRestaurante foi chamado no gateway
        verify(restauranteGatewayMock, times(1)).deleteRestaurante(1L);
    }

    @Test
    void deveBuscarRestaurantesComPagina() {
        // Criando uma lista de restaurantes para simular a paginação
        Restaurante restaurante1 = new Restaurante(1L, "Restaurante A", "Rua A", "Italiana");
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante B", "Rua B", "Chinesa");

        Pagination<Restaurante> pagina = new Pagination<>(1, 10, 100, 10, Arrays.asList(restaurante1, restaurante2));

        // Configura o comportamento do mock para retornar a paginação de restaurantes
        when(restauranteGatewayMock.findAll(1, 10)).thenReturn(pagina);

        // Chama o método do serviço
        Pagination<Restaurante> resultado = restauranteDomainService.buscaRestaurante(1, 10);

        // Verifica se a paginação foi retornada corretamente
        assertEquals(1, resultado.page());
        assertEquals(10, resultado.size());
        assertEquals(100, resultado.total());
        assertEquals(10, resultado.totalPages());
        assertEquals(2, resultado.items().size()); // Deve ter 2 restaurantes na página
    }
}

