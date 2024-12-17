package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllAvaliacaoRestauranteUseCaseTest {

    @Mock
    private AvaliacaoRestauranteDomainService service;

    @InjectMocks
    private GetAllAvaliacaoRestauranteUseCase useCase;

    private Long seqRestaurante;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Cria uma instância de seqRestaurante para passar nos testes
        seqRestaurante = 1L;
    }

    @Test
    void testExecute_ShouldReturnPaginationOfAvaliacaoRestaurante() {
        // Arrange: Prepara os dados de entrada e a resposta esperada
        int page = 1;
        int size = 10;

        AvaliacaoRestaurante avaliacao = new AvaliacaoRestaurante(
                LocalDate.of(2024, 12, 17), 5L, "Boa comida", "123456789", seqRestaurante);
        Pagination<AvaliacaoRestaurante> pagination = Pagination.fromAvaliacao(
                List.of(avaliacao), page, size, 1, 1); // 1 item, 10 por página, 1 página no total

        // Configura o comportamento esperado do mock
        when(service.buscaAvaliacaoPorRestaurante(page, size, seqRestaurante))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllAvaliacaoRestauranteUseCase.Output> result = useCase.execute(page, size, seqRestaurante);

        // Assert: Verifica se o serviço foi chamado corretamente e se a resposta foi mapeada corretamente
        verify(service).buscaAvaliacaoPorRestaurante(page, size, seqRestaurante);

        // Verifica a paginação retornada
        assertNotNull(result);
        assertEquals(1, result.total()); // 1 avaliação
        assertEquals(1, result.totalPages()); // 1 página
        assertEquals(1, result.items().size()); // 1 item na lista

        // Verifica se o conteúdo da página foi corretamente mapeado
        GetAllAvaliacaoRestauranteUseCase.Output output = result.items().get(0);
        assertEquals(avaliacao.getSeqAvaliacao(), output.seqAvaliacao());
        assertEquals(avaliacao.getDataAvaliacao(), output.dataAvaliacao());
        assertEquals(avaliacao.getNotaAvaliacao(), output.notaAvaliacao());
        assertEquals(avaliacao.getComentario(), output.comentario());
        assertEquals(avaliacao.getDocumentoCliente(), output.documentoCliente());
        assertEquals(avaliacao.getSeqRestaurante(), output.seqRestaurante());
    }

    @Test
    void testExecute_ShouldReturnEmptyPagination() {
        // Arrange: Configura a resposta vazia
        int page = 1;
        int size = 10;

        Pagination<AvaliacaoRestaurante> pagination = Pagination.fromAvaliacao(
                List.of(), page, size, 0, 0); // Nenhum item

        when(service.buscaAvaliacaoPorRestaurante(page, size, seqRestaurante))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllAvaliacaoRestauranteUseCase.Output> result = useCase.execute(page, size, seqRestaurante);

        // Assert: Verifica se a paginação retornada está vazia
        assertNotNull(result);
        assertEquals(0, result.total()); // Nenhum elemento
        assertEquals(0, result.totalPages()); // Nenhuma página
        assertTrue(result.items().isEmpty()); // Lista vazia
    }
}
