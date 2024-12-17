package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetReservaMesaByIdUseCaseTest {

    @Mock
    private ReservaMesaDomainService reservaMesaDomainService;

    @InjectMocks
    private GetReservaMesaByIdUseCase useCase;

    private ReservaMesa reservaMesa;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Prepara um objeto ReservaMesa para ser usado no teste
        reservaMesa = new ReservaMesa(
                1L, // seqReservaMesa
                LocalDate.of(2024, 12, 17), // dataReserva
                101L, // seqRestaurante
                "João Silva", // nomeCliente
                "12345678900", // documentoCliente
                StatusReserva.CONFIRMADA // status
        );
    }

    @Test
    public void testExecute_ShouldReturnReservaMesaDetails() {
        // Arrange: Configura o comportamento esperado do mock
        Long reservaId = 1L;
        when(reservaMesaDomainService.buscaReservaPorId(reservaId)).thenReturn(reservaMesa);

        // Act: Executa o método do caso de uso
        GetReservaMesaByIdUseCase.Output result = useCase.execute(reservaId);

        // Assert: Verifica se o serviço foi chamado corretamente
        verify(reservaMesaDomainService).buscaReservaPorId(reservaId);

        // Asserções: Verifica se o resultado está correto
        assertNotNull(result);
        assertEquals(reservaMesa.getSeqReservaMesa(), result.seqReservaMesa());
        assertEquals(reservaMesa.getDataReserva(), result.dataReserva());
        assertEquals(reservaMesa.getSeqRestaurante(), result.seqRestaurante());
        assertEquals(reservaMesa.getNomeCliente(), result.nomeCliente());
        assertEquals(reservaMesa.getDocumentoCliente(), result.documentoCliente());
        assertEquals(reservaMesa.getStatus(), result.status());
    }

    @Test
    void testExecute_ShouldReturnNullWhenReservaMesaNotFound() {
        // Arrange: Configura o comportamento do mock para retornar null
        Long reservaId = 2L;
        when(reservaMesaDomainService.buscaReservaPorId(reservaId)).thenReturn(null);

        // Act: Executa o método do caso de uso
        GetReservaMesaByIdUseCase.Output result = useCase.execute(reservaId);

        // Assert: Verifica que o resultado foi nulo
        verify(reservaMesaDomainService).buscaReservaPorId(reservaId);
        assertNull(result);
    }
}
