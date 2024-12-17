package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllRestauranteUseCaseTest {

    @Mock
    private RestauranteDomainService restauranteDomainService;

    @InjectMocks
    private GetAllRestauranteUseCase useCase;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnPaginationOfRestaurante() {
        // Arrange: Prepara os dados de entrada e a resposta esperada
        int page = 1;
        int size = 10;

        // Cria uma instância de Restaurante usando o método "with"
        Restaurante restaurante = Restaurante.with(
                1L, // seqRestaurante
                "Restaurante A", // nomeRestaurante
                "Rua X, 123", // endereco
                "Italiana" // tipoCozinha
        );

        // Cria a lista de restaurantes
        List<Restaurante> restaurantes = List.of(restaurante);

        // Cria a paginação com um único restaurante
        Pagination<Restaurante> pagination = Pagination.from(
                page, size, 1, 1, restaurantes); // 1 restaurante, 10 por página, 1 página no total

        // Configura o comportamento esperado do mock
        when(restauranteDomainService.buscaRestaurante(page, size))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllRestauranteUseCase.Output> result = useCase.execute(page, size);

        // Assert: Verifica se o serviço foi chamado corretamente e se a resposta foi mapeada corretamente
        verify(restauranteDomainService).buscaRestaurante(page, size);

        // Verifica a paginação retornada
        assertNotNull(result);
        assertEquals(1, result.total()); // 1 restaurante
        assertEquals(1, result.totalPages()); // 1 página
        assertEquals(1, result.items().size()); // 1 item na lista

        // Verifica se o conteúdo da página foi corretamente mapeado
        GetAllRestauranteUseCase.Output output = result.items().get(0);
        assertEquals(restaurante.getNomeRestaurante(), output.nomeRestaurante());
        assertEquals(restaurante.getEndereco(), output.endereco());
        assertEquals(restaurante.getTipoCozinha(), output.tipoCozinha());
    }

    @Test
    void testExecute_ShouldReturnEmptyPagination() {
        // Arrange: Configura a resposta vazia
        int page = 1;
        int size = 10;

        // Cria a lista de restaurantes vazia
        List<Restaurante> restaurantes = List.of();

        // Cria a paginação com lista vazia
        Pagination<Restaurante> pagination = Pagination.from(
                page, size, 0, 0, restaurantes); // 0 itens, 10 por página, 0 páginas no total

        // Configura o comportamento esperado do mock
        when(restauranteDomainService.buscaRestaurante(page, size))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllRestauranteUseCase.Output> result = useCase.execute(page, size);

        // Assert: Verifica se o serviço foi chamado corretamente e se a resposta foi mapeada corretamente
        verify(restauranteDomainService).buscaRestaurante(page, size);

        // Verifica a paginação retornada
        assertNotNull(result);
        assertEquals(0, result.total()); // 0 restaurantes
        assertEquals(0, result.totalPages()); // 0 páginas
        assertEquals(0, result.items().size()); // 0 itens na lista
    }
}

