package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoRestauranteDomainServiceTest {

    @Mock
    private AvaliacaoRestauranteGateway gateway;

    @InjectMocks
    private AvaliacaoRestauranteDomainService service;

    private AvaliacaoRestaurante avaliacaoRestaurante;
    private Pagination<AvaliacaoRestaurante> pagination;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação do objeto AvaliacaoRestaurante para ser usado nos testes
        avaliacaoRestaurante = new AvaliacaoRestaurante(
                LocalDate.of(2024, 12, 17),
                5L, // Nota 5
                "Excelente restaurante",
                "12345678901", // Documento fictício
                1L // Seq restaurante fictício
        );

        // Criando um exemplo de Paginação para ser usado no teste de busca
        List<AvaliacaoRestaurante> avaliacaoList = Arrays.asList(avaliacaoRestaurante);
        pagination = new Pagination<>(1, 10, 1, 1, avaliacaoList);
    }

    @Test
    void testAdicionaAvaliacaoRestaurante() {
        // Act: Chama o método para adicionar avaliação
        service.adicionaAvaliacaoRestaurante(avaliacaoRestaurante);

        // Assert: Verifica se o método 'createAvaliacaoRestaurante' foi chamado no gateway
        verify(gateway, times(1)).createAvaliacaoRestaurante(avaliacaoRestaurante);
    }

    @Test
    void testBuscaAvaliacaoPorRestaurante() {
        // Arrange: Simula o comportamento do método do gateway
        Long seqRestaurante = 1L;
        when(gateway.findAll(1, 10, seqRestaurante)).thenReturn(pagination);

        // Act: Chama o método para buscar as avaliações
        Pagination<AvaliacaoRestaurante> result = service.buscaAvaliacaoPorRestaurante(1, 10, seqRestaurante);

        // Assert: Verifica se o retorno é igual ao esperado e se o gateway foi chamado corretamente
        assertNotNull(result);
        assertEquals(1, result.items().size());
        assertEquals(avaliacaoRestaurante, result.items().get(0));

        verify(gateway, times(1)).findAll(1, 10, seqRestaurante);
    }

    @Test
    void testBuscaAvaliacaoPorRestaurante_ShouldReturnEmptyList_WhenNoEvaluationsFound() {
        // Arrange: Simula o comportamento do método do gateway quando não há avaliações
        Long seqRestaurante = 2L;
        when(gateway.findAll(1, 10, seqRestaurante)).thenReturn(new Pagination<>(1, 10, 0, 0, List.of()));

        // Act: Chama o método para buscar as avaliações
        Pagination<AvaliacaoRestaurante> result = service.buscaAvaliacaoPorRestaurante(1, 10, seqRestaurante);

        // Assert: Verifica que a lista de avaliações está vazia
        assertNotNull(result);
        assertTrue(result.items().isEmpty());

        verify(gateway, times(1)).findAll(1, 10, seqRestaurante);
    }
}

