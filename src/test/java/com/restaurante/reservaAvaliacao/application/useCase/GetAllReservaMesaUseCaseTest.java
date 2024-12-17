package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllReservaMesaUseCaseTest {

    @Mock
    private ReservaMesaDomainService service;

    @InjectMocks
    private GetAllReservaMesaUseCase useCase;

    private Long seqRestaurante;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Cria uma instância de seqRestaurante para passar nos testes
        seqRestaurante = 1L;
    }

    @Test
    void testExecute_ShouldReturnPaginationOfReservaMesa() {
        // Arrange: Prepara os dados de entrada e a resposta esperada
        int page = 1;
        int size = 10;

        // Cria uma instância de ReservaMesa usando o construtor com parâmetros específicos
        ReservaMesa reservaMesa = ReservaMesa.with(
                1L, // seqReservaMesa
                LocalDate.of(2024, 12, 17), // dataReserva
                seqRestaurante, // seqRestaurante
                "João Silva", // nomeCliente
                "123456789", // documentoCliente
                StatusReserva.CONFIRMADA // status
        );

        // Cria a paginação com um único item
        Pagination<ReservaMesa> pagination = Pagination.from(
                List.of(reservaMesa), page, size, 1, 1); // 1 item, 10 por página, 1 página no total

        // Configura o comportamento esperado do mock
        when(service.buscaReservaPorRestaurante(page, size, seqRestaurante))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllReservaMesaUseCase.Output> result = useCase.execute(page, size, seqRestaurante);

        // Assert: Verifica se o serviço foi chamado corretamente e se a resposta foi mapeada corretamente
        verify(service).buscaReservaPorRestaurante(page, size, seqRestaurante);

        // Verifica a paginação retornada
        assertNotNull(result);
        assertEquals(1, result.total()); // 1 reserva
        assertEquals(1, result.totalPages()); // 1 página
        assertEquals(1, result.items().size()); // 1 item na lista

        // Verifica se o conteúdo da página foi corretamente mapeado
        GetAllReservaMesaUseCase.Output output = result.items().get(0);
        assertEquals(reservaMesa.getSeqReservaMesa(), output.seqReservaMesa());
        assertEquals(reservaMesa.getDataReserva(), output.dataReserva());
        assertEquals(reservaMesa.getSeqRestaurante(), output.seqRestaurante());
        assertEquals(reservaMesa.getNomeCliente(), output.nomeCliente());
        assertEquals(reservaMesa.getDocumentoCliente(), output.documentoCliente());
        assertEquals(reservaMesa.getStatus(), output.status());
    }

    @Test
    void testExecute_ShouldReturnEmptyPagination() {
        // Arrange: Configura a resposta vazia
        int page = 1;
        int size = 10;

        // Cria a paginação com lista vazia
        Pagination<ReservaMesa> pagination = Pagination.from(
                List.of(), page, size, 0, 0); // 0 itens, 10 por página, 0 páginas no total

        // Configura o comportamento esperado do mock
        when(service.buscaReservaPorRestaurante(page, size, seqRestaurante))
                .thenReturn(pagination);

        // Act: Executa o método do caso de uso
        Pagination<GetAllReservaMesaUseCase.Output> result = useCase.execute(page, size, seqRestaurante);

        // Assert: Verifica se o serviço foi chamado corretamente e se a resposta foi mapeada corretamente
        verify(service).buscaReservaPorRestaurante(page, size, seqRestaurante);

        // Verifica a paginação retornada
        assertNotNull(result);
        assertEquals(0, result.total()); // 0 reservas
        assertEquals(0, result.totalPages()); // 0 páginas
        assertEquals(0, result.items().size()); // 0 itens na lista
    }
}
