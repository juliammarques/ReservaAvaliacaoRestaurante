package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.exception.ReservaNaoEncontrada;
import com.restaurante.reservaAvaliacao.domain.gateway.ReservaMesaGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReservaMesaDomainServiceTest {

    @Mock
    private ReservaMesaGateway gateway;

    @InjectMocks
    private ReservaMesaDomainService service;

    private ReservaMesa reservaMesa;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação do objeto ReservaMesa para ser usado nos testes
        reservaMesa = new ReservaMesa(1L, LocalDate.now(), 1L, "João Silva", "12345678901", StatusReserva.ESPERANDO_CONFIRMACAO);
    }

    @Test
    void testCriaReservaRestaurante() {
        // Act: Chama o método para criar uma reserva
        service.criaReservaRestaurante(reservaMesa);

        // Assert: Verifica se o método 'createReservaMesa' foi chamado no gateway
        verify(gateway, times(1)).createReservaMesa(reservaMesa);
    }

    @Test
    void testAtualizaReserva() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar uma reserva válida
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.of(reservaMesa));

        // Act: Chama o método para atualizar a reserva
        service.atualizaReserva(reservaMesa);

        // Assert: Verifica se o método 'updateReserva' foi chamado no gateway
        verify(gateway, times(1)).updateReserva(reservaMesa);
    }

    @Test
    void testAtualizaReserva_ReservaNaoEncontrada() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar Optional.empty()
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.empty());

        // Act & Assert: Verifica se o método lança a exceção ReservaNaoEncontrada quando a reserva não é encontrada
        assertThrows(ReservaNaoEncontrada.class, () -> service.atualizaReserva(reservaMesa));

        // Verifica se o método 'updateReserva' não foi chamado no gateway
        verify(gateway, times(0)).updateReserva(reservaMesa);
    }

    @Test
    void testAtualizaStatusConfirmado() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar uma reserva válida
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.of(reservaMesa));

        // Act: Chama o método para atualizar o status para CONFIRMADA
        service.atualizaStatusConfirmado(reservaMesa);

        // Assert: Verifica se o método 'updateReserva' foi chamado no gateway
        verify(gateway, times(1)).updateReserva(reservaMesa);
    }

    @Test
    void testAtualizaStatusEncerrado() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar uma reserva válida
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.of(reservaMesa));

        // Act: Chama o método para atualizar o status para ENCERRADA
        service.atualizaStatusEncerrado(reservaMesa);

        // Assert: Verifica se o método 'updateReserva' foi chamado no gateway
        verify(gateway, times(1)).updateReserva(reservaMesa);
    }

    @Test
    void testBuscaReservaPorRestaurante() {
        List<ReservaMesa> reservas = List.of(
                new ReservaMesa(1L, LocalDate.now(), 1L, "Cliente A", "123456789", StatusReserva.ESPERANDO_CONFIRMACAO),
                new ReservaMesa(2L, LocalDate.now().plusDays(1), 1L, "Cliente B", "987654321", StatusReserva.CONFIRMADA)
        );
        // Arrange: Simula o comportamento do método findAll para retornar uma lista de reservas
        Pagination<ReservaMesa> pagination = new Pagination<>(1, 10, 100, reservas.size(), reservas);
        when(gateway.findAll(1, 10, reservaMesa.getSeqRestaurante())).thenReturn(pagination);

        // Act: Chama o método para buscar reservas por restaurante
        Pagination<ReservaMesa> result = service.buscaReservaPorRestaurante(1, 10, reservaMesa.getSeqRestaurante());

        // Assert: Verifica se a busca retornou o esperado
        assertNotNull(result);
        verify(gateway, times(1)).findAll(1, 10, reservaMesa.getSeqRestaurante());
    }

    @Test
    void testBuscaReservaPorId() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar uma reserva válida
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.of(reservaMesa));

        // Act: Chama o método para buscar uma reserva por ID
        ReservaMesa result = service.buscaReservaPorId(reservaMesa.getSeqReservaMesa());

        // Assert: Verifica se o resultado não é nulo
        assertNotNull(result);
        verify(gateway, times(1)).getReservaMesaById(reservaMesa.getSeqReservaMesa());
    }

    @Test
    void testBuscaReservaPorId_ReservaNaoEncontrada() {
        // Arrange: Simula o comportamento do método getReservaMesaById para retornar Optional.empty()
        when(gateway.getReservaMesaById(reservaMesa.getSeqReservaMesa()))
                .thenReturn(Optional.empty());

        // Act & Assert: Verifica se o método lança a exceção ReservaNaoEncontrada quando a reserva não é encontrada
        assertThrows(ReservaNaoEncontrada.class, () -> service.buscaReservaPorId(reservaMesa.getSeqReservaMesa()));

        // Verifica se o método 'getReservaMesaById' foi chamado corretamente
        verify(gateway, times(1)).getReservaMesaById(reservaMesa.getSeqReservaMesa());
    }
}
