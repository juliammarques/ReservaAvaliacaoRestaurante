package com.restaurante.reservaAvaliacao.service;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestauranteDomainServiceTest {

    private RestauranteGateway gatewayMock;
    private RestauranteDomainService service;

    @BeforeEach
    void setUp() {
        // Criando o mock do RestauranteGateway
        gatewayMock = mock(RestauranteGateway.class);
        // Instanciando o serviço com o mock
        service = new RestauranteDomainService(gatewayMock);
    }

    @Test
    void testRegistraRestaurante() {
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Endereço Teste", "Italiana");

        // Chama o método de registrar
        service.registraRestaurante(restaurante);

        // Verifica se o método createRestaurante foi chamado no gateway
        verify(gatewayMock, times(1)).createRestaurante(restaurante);
    }

    @Test
    void testAtualizaRestaurante() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante Atualizado", LocalDate.now(), "Novo Endereço", "Francesa");

        // Chama o método de atualizar
        service.atualizaRestaurante(restaurante);

        // Verifica se o método updateRestaurante foi chamado no gateway
        verify(gatewayMock, times(1)).updateRestaurante(restaurante);
    }

    @Test
    void testDeleteRestaurante() {
        Long idRestaurante = 1L;

        // Chama o método de deletar
        service.deleteRestaurante(idRestaurante);

        // Verifica se o método deleteRestaurante foi chamado no gateway
        verify(gatewayMock, times(1)).deleteRestaurante(idRestaurante);
    }

    @Test
    void testBuscaRestaurante() {
        // Mock da lista de restaurantes
        Restaurante restaurante1 = new Restaurante(1L, "Restaurante 1", LocalDate.now(), "Endereço 1", "Mexicana");
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante 2", LocalDate.now(), "Endereço 2", "Japonesa");
        List<Restaurante> listaRestaurantes = List.of(restaurante1, restaurante2);

        // Configura o comportamento do mock para retornar a lista de restaurantes
        when(gatewayMock.buscaListaRestaurante()).thenReturn(listaRestaurantes);

        // Chama o método de buscar
        List<Restaurante> restaurantes = service.buscaRestaurante();

        // Verifica se o método foi chamado e se a lista foi retornada corretamente
        verify(gatewayMock, times(1)).buscaListaRestaurante();
        assertNotNull(restaurantes);
        assertEquals(2, restaurantes.size());
    }
}
