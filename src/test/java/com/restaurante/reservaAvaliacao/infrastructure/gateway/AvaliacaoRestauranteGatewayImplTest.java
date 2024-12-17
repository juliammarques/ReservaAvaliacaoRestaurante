package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

class AvaliacaoRestauranteGatewayTest {

    @Mock
    private AvaliacaoRestauranteGateway avaliacaoRestauranteGateway;

    private AvaliacaoRestaurante avaliacao1;
    private AvaliacaoRestaurante avaliacao2;
    private Pagination<AvaliacaoRestaurante> pagination;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Criação de instâncias de AvaliacaoRestaurante para os testes
        avaliacao1 = new AvaliacaoRestaurante(
                LocalDate.of(2024, 12, 17), 4L, "Boa experiência", "123456789", 1L
        );
        avaliacao2 = new AvaliacaoRestaurante(
                LocalDate.of(2024, 12, 18), 5L, "Excelente", "987654321", 1L
        );

        // Simulação de paginação com duas avaliações
        List<AvaliacaoRestaurante> listaAvaliacoes = Arrays.asList(avaliacao1, avaliacao2);
        pagination = Pagination.fromAvaliacao(listaAvaliacoes, 1, 2, 2, 1); // 2 itens, página 1, total 2, 1 página
    }

    @Test
    void testCreateAvaliacaoRestaurante() {
        // Dado que chamamos o método de criar avaliação
        doNothing().when(avaliacaoRestauranteGateway).createAvaliacaoRestaurante(any(AvaliacaoRestaurante.class));

        // Chamada ao método
        avaliacaoRestauranteGateway.createAvaliacaoRestaurante(avaliacao1);

        // Verificar se o método foi chamado uma vez
        verify(avaliacaoRestauranteGateway, times(1)).createAvaliacaoRestaurante(any(AvaliacaoRestaurante.class));
    }

    @Test
    void testFindAll() {
        // Dado que chamamos o método de encontrar avaliações
        when(avaliacaoRestauranteGateway.findAll(1, 2, 1L)).thenReturn(pagination);

        // Chamada ao método
        Pagination<AvaliacaoRestaurante> result = avaliacaoRestauranteGateway.findAll(1, 2, 1L);

        // Verificar se o resultado contém as duas avaliações esperadas
        assertNotNull(result);
        assertEquals(2, result.items().size());
        assertEquals(avaliacao1, result.items().get(0));
        assertEquals(avaliacao2, result.items().get(1));

        // Verificar se o método findAll foi chamado uma vez com os parâmetros corretos
        verify(avaliacaoRestauranteGateway, times(1)).findAll(1, 2, 1L);
    }

    @Test
    void testPaginationProperties() {
        // Verificar propriedades de paginação
        assertEquals(1, pagination.page());  // Página 1
        assertEquals(2, pagination.size());  // Tamanho da página 2
        assertEquals(2, pagination.total()); // Total de 2 itens
        assertEquals(1, pagination.totalPages()); // 1 página total
    }

    @Test
    void testMapPaginationItems() {
        // Mapeando as avaliações para um tipo diferente (exemplo: convertendo AvaliacaoRestaurante em String)
        Pagination<String> mappedPagination = pagination.map(avaliacao -> "Nota: " + avaliacao.getNotaAvaliacao());

        // Verificar se a paginação foi mapeada corretamente
        assertNotNull(mappedPagination);
        assertEquals(2, mappedPagination.items().size());
        assertEquals("Nota: 4", mappedPagination.items().get(0));
        assertEquals("Nota: 5", mappedPagination.items().get(1));
    }
}
